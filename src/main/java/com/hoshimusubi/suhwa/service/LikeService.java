package com.hoshimusubi.suhwa.service;

public interface LikeService {
	void insertLike(Long postId, Long userId);
    void deleteLike(Long postId, Long userId);
    int getLikeCount(Long postId);
    boolean isPostLikedByUser(Long postId, Long userId);

}
