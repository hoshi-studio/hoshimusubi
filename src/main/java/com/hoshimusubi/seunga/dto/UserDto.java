package com.hoshimusubi.seunga.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hoshimusubi.suhwa.dto.UsersDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserDto {
	
	//내가 추가
	private Long num;
	
	private String id;
    private String password;

    // 기본 생성자
    public UserDto() {}

    // 전체 생성자
    public UserDto(String id, String password) {
        this.id = id;
        this.password = password;
    }

    // Getter / Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
