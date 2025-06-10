package com.hoshimusubi.hanbeen.model;
//(사용자 상태)
public class UserStatusVO {
    
    private boolean isLoggedIn;           // 로그인 여부
    private boolean canAccessBoard;       // 게시판 접근 가능 여부
    private String userId;                // 사용자 ID (로그인 시)
    private String userNickname;          // 사용자 닉네임
    
    public UserStatusVO() {}
    
    public UserStatusVO(boolean isLoggedIn, boolean canAccessBoard, String userId, String userNickname) {
        this.isLoggedIn = isLoggedIn;
        this.canAccessBoard = canAccessBoard;
        this.userId = userId;
        this.userNickname = userNickname;
    }
    
    // Getters & Setters
    public boolean isLoggedIn() { return isLoggedIn; }
    public void setLoggedIn(boolean loggedIn) { isLoggedIn = loggedIn; }
    
    public boolean isCanAccessBoard() { return canAccessBoard; }
    public void setCanAccessBoard(boolean canAccessBoard) { this.canAccessBoard = canAccessBoard; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getUserNickname() { return userNickname; }
    public void setUserNickname(String userNickname) { this.userNickname = userNickname; }
}
