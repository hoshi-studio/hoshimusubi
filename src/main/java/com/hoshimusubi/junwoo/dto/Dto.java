package com.hoshimusubi.junwoo.dto;

public class Dto {
    private String email;
    private String password;
    private String nickname;
    private String constellation;
    private String gender;
    private String birthdate;  // ✅ 이름 통일
    private String profileImagePath;

    // 기본 생성자
    public Dto() {}

    // 모든 필드를 포함한 생성자
    public Dto(String email, String password, String nickname,
               String constellation, String gender,
               String birthdate, String profileImagePath) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.constellation = constellation;
        this.gender = gender;
        this.birthdate = birthdate;
        this.profileImagePath = profileImagePath;
    }

    // Getter / Setter
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getConstellation() { return constellation; }
    public void setConstellation(String constellation) { this.constellation = constellation; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirthdate() { return birthdate; }  // ✅ getter 이름도 일치
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public String getProfileImagePath() { return profileImagePath; }
    public void setProfileImagePath(String profileImagePath) { this.profileImagePath = profileImagePath; }

    @Override
    public String toString() {
        return "Dto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", constellation='" + constellation + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", profileImagePath='" + profileImagePath + '\'' +
                '}';
    }
}