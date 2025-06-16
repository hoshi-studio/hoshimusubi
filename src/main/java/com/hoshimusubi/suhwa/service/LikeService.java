package com.hoshimusubi.suhwa.service;

import com.hoshimusubi.suhwa.dto.UsersDTO;

public interface LikeService {
	boolean insertLike(Long postId, UsersDTO userId);
	boolean deleteLike(Long postId, UsersDTO userId);
    int getLikeCount(Long postId);
    boolean isPostLikedByUser(Long postId, UsersDTO userId);

}
