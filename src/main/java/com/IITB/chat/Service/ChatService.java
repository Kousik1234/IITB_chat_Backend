package com.IITB.chat.Service;

import com.IITB.chat.Dto.ChatDto;
import com.IITB.chat.Entity.Chat;
import com.IITB.chat.Exception.MessageException;

import java.util.List;

public interface ChatService {

    public Chat sendMessage(ChatDto chatDto) throws MessageException;

    public List<Chat> getMessage(Long senderId , Long receiverId) throws MessageException;




}
