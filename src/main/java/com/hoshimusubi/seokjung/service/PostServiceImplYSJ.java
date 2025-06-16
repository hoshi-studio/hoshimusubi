package com.hoshimusubi.seokjung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoshimusubi.seokjung.dto.PostDTO;
import com.hoshimusubi.seokjung.mapper.PostMapper;

@Service
public class PostServiceImplYSJ implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<PostDTO> getPostsByZodiacId(Integer zodiacId) {
        return postMapper.selectPostsByZodiacId(zodiacId);
    }
}