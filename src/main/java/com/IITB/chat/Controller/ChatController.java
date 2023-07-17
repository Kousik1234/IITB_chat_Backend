package com.IITB.chat.Controller;

import com.IITB.chat.Dto.ChatDto;
import com.IITB.chat.Entity.Chat;
import com.IITB.chat.Exception.MessageException;
import com.IITB.chat.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @PostMapping("/send")
    public ResponseEntity<?> sendMessageHandeller(@RequestBody ChatDto chatDto) {

        try {
            Chat chat = chatService.sendMessage(chatDto);
            messagingTemplate.convertAndSend("/topic/api/v1/chat", chat);
            return new ResponseEntity<>(chat , HttpStatus.CREATED);
        } catch (MessageException e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
//
//    @MessageMapping("/receive")
//    @SendToUser
//    public ResponseEntity<?> receiveMessageHandeller(@RequestParam("senderId") Long senderId, @RequestParam("receiverId") Long receiverId) {
//
//        try {
//            List<Chat> chats = chatService.getMessage(senderId, receiverId);
//            messagingTemplate.convertAndSend("/topic/api/v1/chat", chats);
//            return new ResponseEntity<>(chats , HttpStatus.CREATED);
//        } catch (MessageException e) {
//            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    @MessageMapping("/chat/receive")
    @SendToUser("/topic/api/v1/chat")
    public ResponseEntity<?> receiveMessageHandler(ChatDto chatDto) {
        try {
            // Process the received message and retrieve the chat history
            List<Chat> chats = chatService.getMessage(chatDto.getSenderId(), chatDto.getReceiverId());
            return new ResponseEntity<>(chats, HttpStatus.OK);
        } catch (MessageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
