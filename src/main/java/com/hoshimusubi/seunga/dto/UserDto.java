package com.hoshimusubi.seunga.dto;

public class UserDto {
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
