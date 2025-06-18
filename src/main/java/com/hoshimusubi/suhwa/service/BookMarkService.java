package com.hoshimusubi.suhwa.service;

import org.apache.ibatis.annotations.Param;

import com.hoshimusubi.suhwa.dto.UsersDTO;

public interface BookMarkService {
	void addBookmark(int postId, @Param("userId")int loginUser);
    void removeBookmark(int postId, @Param("userId")int userId);
    boolean isPostBookmarkedByUser(int postId, @Param("userId")int long1);

}
