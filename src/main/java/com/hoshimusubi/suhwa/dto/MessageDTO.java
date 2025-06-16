package com.hoshimusubi.suhwa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
	private Long id;
    private Long senderId;
    private Long receiverId;
    private String content;
    private Integer isRead;             // 0 or 1
    private LocalDateTime sentAt;
}
