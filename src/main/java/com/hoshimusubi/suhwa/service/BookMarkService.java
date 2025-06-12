package com.hoshimusubi.suhwa.service;

public interface BookMarkService {
	void addBookmark(Long postId, Long userId);
    void removeBookmark(Long postId, Long userId);
    boolean isPostBookmarkedByUser(Long postId, Long userId);

}
