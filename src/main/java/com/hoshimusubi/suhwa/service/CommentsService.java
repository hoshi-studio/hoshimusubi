package com.hoshimusubi.suhwa.service;

import java.util.List;

import com.hoshimusubi.suhwa.dto.CommentsDTO;

public interface CommentsService {
	List<CommentsDTO> getCommentById(Long id);
	void saveComment(CommentsDTO comment);
	void updateComment(Long id, String content);
	void deleteComment(Long id);
	int getcommentCount(Long id);
}
