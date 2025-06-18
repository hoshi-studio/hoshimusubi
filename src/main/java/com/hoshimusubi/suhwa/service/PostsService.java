package com.hoshimusubi.suhwa.service;

import com.hoshimusubi.suhwa.dto.PostsDTO;

public interface PostsService {
	PostsDTO getPostById(int id);
	void savePost(PostsDTO post);
    void updatePost(PostsDTO post);
    void deletePost(int id);
    void increaseViewCount(int postId);
	boolean isImageNameExists(String imageName);
}
