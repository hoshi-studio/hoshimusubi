package com.hoshimusubi.junwoo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoshimusubi.junwoo.dto.Dto;
import com.hoshimusubi.junwoo.mapper.MemberMapper;
import com.hoshimusubi.junwoo.model.Postvo;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;

	// BCryptPasswordEncoder 객체 생성
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public void register(Postvo member) {

		String encodedPassword = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodedPassword);

		memberMapper.insertMember(member);
	}

	// 이메일 중복 확인
	public boolean isEmailDuplicate(String email) {
		return memberMapper.countByEmail(email) > 0;
	}

	// 비밀번호 특수문자포함 8글자 이상인지 확인
	public boolean isValidPassword(String password) {
		
		// 비밀번호가 null이거나 8자 미만이면 false
		if (password == null || password.length() < 8) {
			return true;
		}

		// 특수문자 포함 여부 확인 (정규식 사용)
		String specialCharPattern = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*";
		boolean check = password.matches(specialCharPattern);;
		
		return !password.matches(specialCharPattern);
	}

	// 닉네임 중복 확인
	public boolean isNicknameDuplicate(String nickname) {
		return memberMapper.countByNickname(nickname) > 0;
	}

	// 생년월일을 바탕으로 별자리 ID 반환
	public int calculateZodiacId(String birthDate) {
		// 생년월일 형식: yyyy-MM-dd 또는 yyyy/MM/dd
		String[] parts = birthDate.split("[-/]");
		int month = Integer.parseInt(parts[1]);
		int day = Integer.parseInt(parts[2]);

		// 별자리 날짜 기준 (1 ~ 12로 리턴)
		if ((month == 1 && day >= 20) || (month == 2 && day <= 18))
			return 1; // 물병자리
		if ((month == 2 && day >= 19) || (month == 3 && day <= 20))
			return 2; // 물고기자리
		if ((month == 3 && day >= 21) || (month == 4 && day <= 19))
			return 3; // 양자리
		if ((month == 4 && day >= 20) || (month == 5 && day <= 20))
			return 4; // 황소자리
		if ((month == 5 && day >= 21) || (month == 6 && day <= 21))
			return 5; // 쌍둥이자리
		if ((month == 6 && day >= 22) || (month == 7 && day <= 22))
			return 6; // 게자리
		if ((month == 7 && day >= 23) || (month == 8 && day <= 22))
			return 7; // 사자자리
		if ((month == 8 && day >= 23) || (month == 9 && day <= 23))
			return 8; // 처녀자리
		if ((month == 9 && day >= 24) || (month == 10 && day <= 22))
			return 9; // 천칭자리
		if ((month == 10 && day >= 23) || (month == 11 && day <= 22))
			return 10; // 전갈자리
		if ((month == 11 && day >= 23) || (month == 12 && day <= 24))
			return 11; // 사수자리
		return 12; // 염소자리 (12월 25일 ~ 1월 19일 포함)
	}

	public void registerCommit(Postvo member) {
		memberMapper.insertMember(member); // 이 시점에 DB에 바로 커밋됨 (별도 commit 없어도)
	}

}