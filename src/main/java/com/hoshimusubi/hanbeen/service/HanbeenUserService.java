package com.hoshimusubi.hanbeen.service;

import com.hoshimusubi.hanbeen.dto.UserProfileDTO;

/**
 * 한빈 사용자 관련 비즈니스 로직 인터페이스
 */
public interface HanbeenUserService {

    /**
     * 주어진 사용자 ID로부터 마이페이지에 보여줄 사용자 프로필 정보를 조회한다.
     *
     * @param userId 조회할 사용자의 고유 ID
     * @return 닉네임, 성별, 이메일, 별자리(일본어), 프로필 사진 경로 등이 담긴 {@link UserProfileDTO}
     */
    UserProfileDTO getUserProfile(Long userId);

}
