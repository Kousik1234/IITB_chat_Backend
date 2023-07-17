package com.IITB.chat.ServiceImpl;

import com.IITB.chat.Dto.ChatDto;
import com.IITB.chat.Entity.Chat;
import com.IITB.chat.Exception.MessageException;
import com.IITB.chat.Repositry.ChatRepository;
import com.IITB.chat.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat sendMessage(ChatDto chatDto) throws MessageException {

        Chat chat = new Chat();
        chat.setTimeStampMessage(LocalDateTime.now());
        chat.setMessage(chatDto.getMessage());
        chat.setSenderId(chatDto.getSenderId());
        chat.setReceiverId(chatDto.getReceiverId());
        chat.setAttachment(chatDto.getAttachment());
        chatRepository.save(chat);
        return chat;

    }

    @Override
    public List<Chat> getMessage(Long senderId, Long receiverId) throws MessageException {
        List<Chat> chats = chatRepository.findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimeStampMessageAsc(senderId, receiverId, receiverId, senderId);
        return chats;
    }
}
