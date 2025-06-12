package com.hoshimusubi.suhwa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.suhwa.dto.PostsDTO;

@Mapper
@MapperScan
public interface PostsMapper {
    PostsDTO getPostById(Long id);
    void insertPost(PostsDTO post);
    void updatePost(PostsDTO post);
    void deletePost(Long id);
}
