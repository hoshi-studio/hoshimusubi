package com.hoshimusubi.suhwa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsDTO {
	private Long id;
    private Long user_Id;              // FK: Users 테이블의 id
    private String title;
    private String content;
    private LocalDateTime created_at;  // TIMESTAMP -> LocalDateTime
    private Integer views;
    private Integer status; 
    private Integer likeCount;
    private Integer commentCount;
    private String imageUrl;
}
