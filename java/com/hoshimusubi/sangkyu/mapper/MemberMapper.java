// src/main/java/com/hoshimusubi/mapper/MemberMapper.java (예시 경로)
package com.hoshimusubi.sangkyu.mapper;

import org.apache.ibatis.annotations.Mapper; // MyBatis 3.4.0 이상
import org.apache.ibatis.annotations.Param; // 여러 파라미터 전달 시

import com.hoshimusubi.sangkyu.domain.MemberVO;

@Mapper // Spring과 MyBatis 연동 시 해당 인터페이스가 Mapper임을 알림
public interface MemberMapper {
    // memberId로 회원 정보를 가져오는 메서드 (프로필 이미지 경로 포함)
    MemberVO getMemberById(int memberId);

    // 프로필 이미지 경로를 업데이트하는 메서드
    int updateProfileImagePath(@Param("memberId") int memberId, @Param("profileImagePath") String profileImagePath);
}