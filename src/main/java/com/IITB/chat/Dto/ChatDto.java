package com.IITB.chat.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {

    private String message;
    private Long senderId;
    private Long receiverId;
    private String attachment;

}
