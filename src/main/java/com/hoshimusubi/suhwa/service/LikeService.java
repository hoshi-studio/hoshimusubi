package com.hoshimusubi.suhwa.service;

import org.apache.ibatis.annotations.Param;

import com.hoshimusubi.suhwa.dto.UsersDTO;

public interface LikeService {
	boolean insertLike(Long postId, @Param("userId")Long userId);
	boolean deleteLike(Long postId, @Param("userId")Long userId);
    int getLikeCount(Long postId);
    boolean isPostLikedByUser(Long postId, @Param("userId")Long userid);

}
