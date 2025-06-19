package com.hoshimusubi.seokjung.service;

import java.util.List;

import com.hoshimusubi.seokjung.dto.MyPageContentDTO;
import com.hoshimusubi.seokjung.dto.MyPageDTO2;
import com.hoshimusubi.seokjung.dto.ReceiveMessageDTO;

public interface MyPageService2 {
		MyPageDTO2 getMyPageInfo(int userId);
	
		List<MyPageContentDTO> getMyPosts(int userId, String sort, int offset, int limit);
	    List<MyPageContentDTO> getMyComments(int userId);
	    List<MyPageContentDTO> getMyLikes(int userId);
	    List<MyPageContentDTO> getMyBookmarks(int userId);
	    
	    List<MyPageContentDTO> getPostsCommentedByUser(int userId, String sort, int offset, int limit);
	    List<MyPageContentDTO> getPostsLikedByUser(int userId, String sort, int offset, int limit);
	    
	    int countUserPosts(int userId);
	    int countCommentsByUser(int userId);
	    int countLikesByUser(int userId);

		int countBookMarksByUser(int userId);

		List<MyPageContentDTO> getPostsBookMarkedByUser(int userId, String sort, int offset, int limit);

		List<ReceiveMessageDTO> getReceivedMessages(int userId);
		
		void sendMessage(ReceiveMessageDTO message);

		void markAsRead(int messageId);
}
