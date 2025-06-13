package com.hoshimusubi.suhwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoshimusubi.suhwa.dto.UsersDTO;
import com.hoshimusubi.suhwa.mapper.LikesMapper;

@Service("LikeServiceImpl")
@Transactional
public class LikeServiceImpl implements LikeService {

	@Autowired
    private LikesMapper likesMapper;

	/** 좋아요 등록 (중복 방지) */
    @Override
    public boolean insertLike(Long postId, UsersDTO userId) {
    	likesMapper.insertLike(postId, userId);
        return likesMapper.exists(postId,userId)>0;
    }

    /** 좋아요 취소 */
    @Override
    public boolean deleteLike(Long postId, UsersDTO userId) {
    	likesMapper.deleteLike(postId, userId);
        return likesMapper.exists(postId,userId)>0;
    }

    /** 현재 게시글의 좋아요 수 반환 */
    @Override
    public int getLikeCount(Long postId) {
        return likesMapper.countLikesByPostId(postId);
    }

    /** 사용자가 좋아요를 눌렀는지 확인 */
    @Override
    public boolean isPostLikedByUser(Long postId, UsersDTO userId) {
        return likesMapper.exists(postId, userId)>0;
    }
    
}
