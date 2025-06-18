package com.hoshimusubi.suhwa.service;

import java.util.List;

import com.hoshimusubi.suhwa.dto.CommentsDTO;

public interface CommentsService {
	List<CommentsDTO> getCommentById(int id);
	void saveComment(CommentsDTO comment);
	void updateComment(int id, String content);
	void deleteComment(int id);
	int getcommentCount(int id);
	List<CommentsDTO> getCommentsPaged(int postId, int page, int size);
}
