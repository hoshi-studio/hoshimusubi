package com.hoshimusubi.suhwa.service;

import com.hoshimusubi.suhwa.dto.PostsDTO;

public interface PostsService {
	PostsDTO getPostById(Long id);
	void savePost(PostsDTO post);
    void updatePost(PostsDTO post);
    void deletePost(Long id);
    void increaseViewCount(Long postId);
}
