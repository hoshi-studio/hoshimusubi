package com.hoshimusubi.junwoo.model;

public class Postvo {
    private String userId;
    private String password;
    private int zodiacId;
    private String nickname;
    private String gender;
    private String birthDate;
    private String profilePic;

    // 기본 생성자
    public Postvo() {}

    // 모든 필드를 포함한 생성자
    public Postvo(String userId, String password, int zodiacId, String nickname, String gender, String birthDate, String profilePic) {
        this.userId = userId;
        this.password = password;
        this.zodiacId = zodiacId;
        this.nickname = nickname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.profilePic = profilePic;
    }

    // Getter / Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getZodiacId() {
        return zodiacId;
    }

    public void setZodiacId(int zodiacId) {
        this.zodiacId = zodiacId;
    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}