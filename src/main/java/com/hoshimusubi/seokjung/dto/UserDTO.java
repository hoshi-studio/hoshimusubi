package com.hoshimusubi.seokjung.dto;

import lombok.Data;

@Data
public class UserDTO {
	
    private int id;                  // Users.id
    private String userId;          // Users.user_id (이메일)
    private String password;        // Users.password
    private int zodiacId;           // Users.zodiac_id
    private String nickname;        // Users.nickname
    private String gender;          // Users.gender
    private String birthDate;       // Users.birth_date
    private String profilePic;      // Users.profile_pic
    private String createdAt;       // Users.created_at
	
}
