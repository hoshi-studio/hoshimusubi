package com.hoshimusubi.suhwa.service;

import org.apache.ibatis.annotations.Param;

import com.hoshimusubi.suhwa.dto.UsersDTO;

public interface BookMarkService {
	void addBookmark(Long postId, @Param("userId")Long loginUser);
    void removeBookmark(Long postId, @Param("userId")Long userId);
    boolean isPostBookmarkedByUser(Long postId, @Param("userId")Long long1);

}
