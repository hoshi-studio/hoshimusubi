package com.hoshimusubi.suhwa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDTO {
	private int id;
    private int postId;              // 댓글이 속한 게시글 ID
    private int userId;              // 작성자 ID
    private String content;
    private LocalDateTime created_at;
    private String nickname;
}
