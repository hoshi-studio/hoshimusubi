package com.hoshimusubi.suhwa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Override
    public List<CommentsDTO> getCommentsPaged(Long postId, int page, int size) {
        int offset = (page - 1) * size;
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("offset", offset);
        params.put("limit", size);
        return CommentsMapper.getCommentsPaged(params);
    }
}
