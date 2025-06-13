package com.hoshimusubi.suhwa.service;

import com.hoshimusubi.suhwa.dto.UsersDTO;

public interface BookMarkService {
	void addBookmark(Long postId, UsersDTO userId);
    void removeBookmark(Long postId, UsersDTO userId);
    boolean isPostBookmarkedByUser(Long postId, UsersDTO userId);

}
