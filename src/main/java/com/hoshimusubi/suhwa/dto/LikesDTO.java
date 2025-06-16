package com.hoshimusubi.suhwa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesDTO {
	private Long id;
    private Long userId;
    private Long postId;
    private LocalDateTime createdAt;
}
