package com.hoshimusubi.seokjung.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReceiveMessageDTO {
	
    private int messageId;
    private int senderId;
    private int receiverId;
    private String senderNickname;
    private String content;
    private LocalDateTime sentAt;
    private int isRead;
	
}
