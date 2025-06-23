package com.hoshimusubi.suhwa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
	private int id;
    private int senderId;
    private int receiverId;
    private String ninckname;
    private String content;
    private Integer isRead;             // 0 or 1
    private LocalDateTime sentAt;
}
