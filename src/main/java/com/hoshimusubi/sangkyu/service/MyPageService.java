package com.hoshimusubi.sangkyu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hoshimusubi.sangkyu.mapper.MyPageMapper;
import com.hoshimusubi.sangkyu.dto.*;

@Service
public class MyPageService {
    @Autowired
    private MyPageMapper myPageMapper;

    public MyPageUserDto getMyProfile(Long userId) {
        return myPageMapper.selectMyProfile(userId);
    }

    public List<MyPagePostDto> getMyPosts(Long userId) {
        return myPageMapper.selectMyPosts(userId);
    }

    public List<MyPagePostDto> getMyComments(Long userId) {
        return myPageMapper.selectMyComments(userId);
    }

    public List<MyPagePostDto> getMyLikes(Long userId) {
        return myPageMapper.selectMyLikes(userId);
    }

    public List<MyPagePostDto> getMyScraps(Long userId) {
        return myPageMapper.selectMyScraps(userId);
    }
}
