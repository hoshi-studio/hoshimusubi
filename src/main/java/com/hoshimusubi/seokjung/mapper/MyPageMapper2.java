package com.hoshimusubi.seokjung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.seokjung.dto.MyPageContentDTO;
import com.hoshimusubi.seokjung.dto.MyPageDTO2;
import com.hoshimusubi.seokjung.dto.ReceiveMessageDTO;

@Mapper
@MapperScan
public interface MyPageMapper2 {
	
	  	MyPageDTO2 getUserProfile(int userId);

	    int countUserPosts(int userId);
	    int countUserComments(int userId);
	    int countUserLikes(int userId);
	    int countUserBookmarks(int userId);
	    int countUserMessages(int userId);
	    
	    List<MyPageContentDTO> getMyPosts(
	    	    @Param("userId") int userId,
	    	    @Param("sort") String sort,
	    	    @Param("offset") int offset,
	    	    @Param("limit") int limit
	    	);
	    
	    List<MyPageContentDTO> getPostsCommentedByUser( @Param("userId") int userId,
	    	    @Param("sort") String sort,
	    	    @Param("offset") int offset,
	    	    @Param("limit") int limit);
	    
	    List<MyPageContentDTO> getPostsLikedByUser( @Param("userId") int userId,
	    	    @Param("sort") String sort,
	    	    @Param("offset") int offset,
	    	    @Param("limit") int limitd);
	    
	    List<MyPageContentDTO> getPostsBookMarkedByUser(@Param("userId") int userId,
	    	    @Param("sort") String sort,
	    	    @Param("offset") int offset,
	    	    @Param("limit") int limitd);
	    
	    List<MyPageContentDTO> getMyComments(@Param("userId") int userId);
	    List<MyPageContentDTO> getMyLikes(@Param("userId") int userId);
	    List<MyPageContentDTO> getMyBookmarks(@Param("userId") int userId);
	    
		int countCommentsByUser(int userId);
		int countLikesByUser(int userId);
		int countBookMarksByUser(int userId);
		
		List<ReceiveMessageDTO> getReceivedMessagesByUserId(@Param("userId") int userId);
	    
		void insertMessage(ReceiveMessageDTO message);
		
		void markAsRead(@Param("messageId") int messageId);
}
