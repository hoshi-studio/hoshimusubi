package com.hoshimusubi.seokjung.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostDTO {
	//게시글
	private Integer id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private String formattedDate;
	private String formattedTime;
	
	//게시글 부가정보
	private int views;
	private int status;
	private int likeCount;
	private int commentCount;
	private boolean bookmarkedByCurrentUser;
	
	//작성자
	private Integer userId;
	private String nickname;
	private String profilePic;
	//별자리
	private Integer zodiacId;
	private String symbol;
	private String koname_ja;
	
}
