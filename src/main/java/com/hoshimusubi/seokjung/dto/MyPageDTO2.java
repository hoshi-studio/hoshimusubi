package com.hoshimusubi.seokjung.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyPageDTO2 {

	private String profilePic;
    private String nickname;
    private String gender;
    private String email;
    private String zodiacName;
    private Date birthDate;
    private int postCount;
    private int commentCount;
    private int likeCount;
    private int bookmarkCount;
    private int messageCount;
	
}
