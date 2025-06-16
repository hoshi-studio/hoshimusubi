package com.hoshimusubi.seokjung.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoshimusubi.seokjung.dto.PostDTO;
import com.hoshimusubi.seokjung.mapper.PostMapper;

@Service
public class PostServiceImplYSJ implements PostService {

    @Autowired
    private PostMapper postMapper;
    
    @Override
    public List<PostDTO> getPostsByZodiacIdSorted(Integer zodiacId, String sort, int offset, int limit){
    	Map<String, Object> paramMap = new HashMap<>();
    	paramMap.put("zodiacId", zodiacId);
    	paramMap.put("sort", sort);
    	paramMap.put("offset", offset);
    	paramMap.put("limit", limit);
    	return postMapper.selectPostsByZodiacIdSorted(paramMap);
    }
    
    @Override
    public int countPostsByZodiacId(Integer zodiacId) {
    	return postMapper.countPostsByZodiacId(zodiacId);
    }
    
    @Override
    public List<PostDTO> getAllZodiac(){
    	return postMapper.selectAllZodiacs();
    }
    
    
} 