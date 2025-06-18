package com.hoshimusubi.suhwa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.suhwa.dto.PostsDTO;

@Mapper
public interface PostsMapper {
    PostsDTO getPostById(int id);
    void insertPost(PostsDTO post);
    void updatePost(PostsDTO post);
    void deletePost(int id);
    void increaseViewCount(int id);

}
