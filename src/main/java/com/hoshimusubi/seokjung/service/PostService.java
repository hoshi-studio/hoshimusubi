package com.hoshimusubi.seokjung.service;

import java.util.List;

import com.hoshimusubi.seokjung.dto.PostDTO;

public interface PostService {
	List<PostDTO> getPostsByZodiacIdSorted(Integer zodiacId, String sort, int offset, int limit);
	
	int countPostsByZodiacId(Integer zodiacId);
	
	List<PostDTO> getAllZodiac();
}