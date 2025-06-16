package com.hoshimusubi.suhwa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDTO {
	private Long id;
    private Long postId;              // 댓글이 속한 게시글 ID
    private Long userId;              // 작성자 ID
    private String content;
    private LocalDateTime created_at;
    private String nickname;
}
