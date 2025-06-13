// src/main/java/com/hoshimusubi/domain/MemberVO.java (예시 경로)
package com.hoshimusubi.sangkyu.domain;

import lombok.Data; // Lombok 사용 시

@Data // @Getter, @Setter, @EqualsAndHashCode, @ToString 등을 자동으로 생성 (Lombok)
public class MemberVO {
    private int memberId;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String gender;
    private java.sql.Date birthdate;
    private String profileImagePath; // 프로필 이미지 경로 필드 추가

    // Lombok을 사용하지 않는다면, 여기에 직접 Getter/Setter 및 생성자 등 추가
    // public int getMemberId() { return memberId; }
    // public void setMemberId(int memberId) { this.memberId = memberId; }
    // ...
}
