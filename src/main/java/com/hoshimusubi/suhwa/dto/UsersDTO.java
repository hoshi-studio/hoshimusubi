package com.hoshimusubi.suhwa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
	private Long id;
    private String userId;           // 이메일
    private String password;
    private Long zodiacId;          // 별자리 id
    private String nickname;
    private String gender;
    private LocalDate birthDate;
    private String profilePic;
    private LocalDateTime createdAt;
}
