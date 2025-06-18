package com.hoshimusubi.hanbeen.service;

import com.hoshimusubi.hanbeen.dto.UserProfileDTO;
import com.hoshimusubi.hanbeen.mapper.HanbeenUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link HanbeenUserService} 기본 구현체.
 */
@Service("hanbeenUserServiceImpl")
public class HanbeenUserServiceImpl implements HanbeenUserService {

    private final HanbeenUserMapper userMapper;

    @Autowired
    public HanbeenUserServiceImpl(HanbeenUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserProfileDTO getUserProfile(int userId) {
        return userMapper.getUserProfileById(userId);
    }
}

