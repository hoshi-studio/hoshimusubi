package com.hoshimusubi.suhwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoshimusubi.suhwa.mapper.LikesMapper;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
    private LikesMapper likesMapper;

    @Override
    public void insertLike(Long postId, Long userId) {
        likesMapper.insertLike(postId, userId);
    }

    @Override
    public void deleteLike(Long postId, Long userId) {
        likesMapper.deleteLike(postId, userId);
    }

    @Override
    public int getLikeCount(Long postId) {
        return likesMapper.countLikesByPostId(postId);
    }

    @Override
    public boolean isPostLikedByUser(Long postId, Long userId) {
        return likesMapper.countUserLike(postId, userId) > 0;
    }
}
