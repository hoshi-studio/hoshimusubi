package com.hoshimusubi.suhwa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoshimusubi.suhwa.dto.CommentsDTO;
import com.hoshimusubi.suhwa.mapper.CommentsMapper;
import com.hoshimusubi.suhwa.mapper.PostsMapper;

@Service("CommentsServiceImpl")
@Transactional
public class CommentsServiceImpl implements CommentsService{

	@Autowired
	private CommentsMapper CommentsMapper;
	
	@Override
    public List<CommentsDTO> getCommentById(Long id) {
        return CommentsMapper.getCommentById(id);
    }
	
	@Override
    public void saveComment(CommentsDTO comment) {
		CommentsMapper.insertComment(comment);
		
		String nickname = CommentsMapper.getNicknameByUserId(comment.getUserId());
	    comment.setNickname(nickname); 
    }
	
	@Override
	public void updateComment(Long id, String content) {
		CommentsMapper.updateComment(id, content);
	}

	@Override
	public void deleteComment(Long id) {
		CommentsMapper.deleteComment(id);
	}
	
	@Override
    public int getcommentCount(Long id) {
		int count = CommentsMapper.selectCount(id);
		return count;
	}
}
