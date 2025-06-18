package com.hoshimusubi.suhwa.service;

import org.apache.ibatis.annotations.Param;

import com.hoshimusubi.suhwa.dto.UsersDTO;

public interface LikeService {
	boolean insertLike(int postId, @Param("userId")int userId);
	boolean deleteLike(int postId, @Param("userId")int userId);
    int getLikeCount(int postId);
    boolean isPostLikedByUser(int postId, @Param("userId")int userid);

}
