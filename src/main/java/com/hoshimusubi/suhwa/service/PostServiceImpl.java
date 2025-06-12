package com.hoshimusubi.suhwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hoshimusubi.suhwa.dto.PostsDTO;
import com.hoshimusubi.suhwa.mapper.PostsMapper;

@Service
public class PostServiceImpl implements PostsService {

    @Autowired
    private PostsMapper PostsMapper;

    @Override
    public PostsDTO getPostById(Long id) {
        return PostsMapper.getPostById(id);
    }
    

    @Override
    public void savePost(PostsDTO post) {
    	PostsMapper.insertPost(post);
    }


	public void updatePost(PostsDTO post) {
		PostsMapper.updatePost(post);
		
	}


	public void deletePost(Long id) {
		PostsMapper.deletePost(id);
		
	}
    
    
}
