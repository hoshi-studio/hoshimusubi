package com.hoshimusubi.suhwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.hoshimusubi.suhwa.dto.PostsDTO;
import com.hoshimusubi.suhwa.mapper.PostsMapper;

@Service("PostServiceImpl")
@Transactional
public class PostServiceImpl implements PostsService {

    @Autowired
    private PostsMapper PostsMapper;

    @Override
    public PostsDTO getPostById(int id) {
        return PostsMapper.getPostById(id);
    }
    

    @Override
    public void savePost(PostsDTO post) {
    	PostsMapper.insertPost(post);
    }

    @Override
	public void updatePost(PostsDTO post) {
		PostsMapper.updatePost(post);
		
	}

	@Override
	public void deletePost(int id) {
		PostsMapper.deletePost(id);
		
	}
	
	@Override
	public void increaseViewCount(int postId) {
		PostsMapper.increaseViewCount(postId);
	}
    
    
}
