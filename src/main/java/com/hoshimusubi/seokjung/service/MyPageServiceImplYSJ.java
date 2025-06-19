package com.hoshimusubi.seokjung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoshimusubi.seokjung.dto.MyPageContentDTO;
import com.hoshimusubi.seokjung.dto.MyPageDTO2;
import com.hoshimusubi.seokjung.dto.ReceiveMessageDTO;
import com.hoshimusubi.seokjung.mapper.MyPageMapper2;

@Service
public class MyPageServiceImplYSJ implements MyPageService2 {
	
    @Autowired
    private MyPageMapper2 myPageMapper;

    @Override
    public MyPageDTO2 getMyPageInfo(int userId) {
        MyPageDTO2 dto = myPageMapper.getUserProfile(userId);
        dto.setPostCount(myPageMapper.countUserPosts(userId));
        dto.setCommentCount(myPageMapper.countUserComments(userId));
        dto.setLikeCount(myPageMapper.countUserLikes(userId));
        dto.setBookmarkCount(myPageMapper.countUserBookmarks(userId));
        dto.setMessageCount(myPageMapper.countUserMessages(userId));
        return dto;
    }
    
    @Override
    public List<MyPageContentDTO> getMyPosts(int userId, String sort, int offset, int limit) {
        return myPageMapper.getMyPosts(userId, sort, offset, limit);
    }
    
    
    @Override
    public List<MyPageContentDTO> getPostsCommentedByUser(int userId, String sort, int offset, int limit) {
        return myPageMapper.getPostsCommentedByUser(userId, sort, offset, limit);
    }

    @Override
    public List<MyPageContentDTO> getPostsLikedByUser(int userId, String sort, int offset, int limit) {
        return myPageMapper.getPostsLikedByUser(userId, sort, offset, limit);
    }

    @Override
	public List<MyPageContentDTO> getPostsBookMarkedByUser(int userId, String sort, int offset,
			int limit) {
		return myPageMapper.getPostsBookMarkedByUser(userId, sort, offset, limit);
	}
    
    
    @Override
    public int countUserPosts(int userId) {
        return myPageMapper.countUserPosts(userId);
    }

    
    @Override
    public List<MyPageContentDTO> getMyComments(int userId) {
        return myPageMapper.getMyComments(userId);
    }

    @Override
    public List<MyPageContentDTO> getMyLikes(int userId) {
        return myPageMapper.getMyLikes(userId);
    }

    @Override
    public List<MyPageContentDTO> getMyBookmarks(int userId) {
        return myPageMapper.getMyBookmarks(userId);
    }
    
    @Override
	public int countCommentsByUser(int userId) {
		return myPageMapper.countCommentsByUser(userId);
	}
    
    @Override
	public int countLikesByUser(int userId) {
		return myPageMapper.countLikesByUser(userId);
	}
    
    @Override
	public int countBookMarksByUser(int userId) {
		return myPageMapper.countBookMarksByUser(userId);
	}

    @Override
    public List<ReceiveMessageDTO> getReceivedMessages(int userId) {
        return myPageMapper.getReceivedMessagesByUserId(userId);
    }
    
    @Override
    public void sendMessage(ReceiveMessageDTO message) {
        myPageMapper.insertMessage(message);
    }
    
    @Override
    public void markAsRead(int messageId) {
        myPageMapper.markAsRead(messageId);
    }
    
}
