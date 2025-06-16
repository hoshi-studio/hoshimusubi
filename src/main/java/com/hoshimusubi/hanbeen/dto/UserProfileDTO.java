package com.hoshimusubi.hanbeen.dto;

public class UserProfileDTO {
    private String nickname;
    private String gender;
    private String email;
    private String zodiacNameJa;   // 별자리 일본어 이름
    private String profilePic;
    private Long user_Id;// 프로필 사진 경로

    // 기본 생성자
    public UserProfileDTO() {}

    // 전체 생성자
    public UserProfileDTO(String nickname, String gender, String email, String zodiacNameJa, String profilePic,Long user_Id) {
        this.nickname = nickname;
        this.gender = gender;
        this.email = email;
        this.zodiacNameJa = zodiacNameJa;
        this.profilePic = profilePic;
        this.user_Id=user_Id;
    }

    // getter/setter
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZodiacNameJa() {
        return zodiacNameJa;
    }

    public void setZodiacNameJa(String zodiacNameJa) {
        this.zodiacNameJa = zodiacNameJa;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    
    public Long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }
}
