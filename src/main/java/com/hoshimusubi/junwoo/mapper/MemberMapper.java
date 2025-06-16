package com.hoshimusubi.junwoo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.junwoo.model.Postvo;

@Mapper
@MapperScan
public interface MemberMapper {
    // 회원가입 (INSERT)
    void insertMember(Postvo member);

    // 이메일 중복 체크
    int countByEmail(String email);

    // 닉네임 중복 체크
    int countByNickname(String nickname);
}