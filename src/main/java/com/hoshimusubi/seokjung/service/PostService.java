package com.hoshimusubi.seokjung.service;

import java.util.List;

import com.hoshimusubi.seokjung.dto.PostDTO;

public interface PostService {
	List<PostDTO> getPostsByZodiacId(Integer zodiacId);
}
