package com.hoshimusubi.suhwa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
@MapperScan
public interface BookMarkMapper {
	void insertBookmark(@Param("postId") Long postId, @Param("userId") Long userId);
    void deleteBookmark(@Param("postId") Long postId, @Param("userId") Long userId);
    int countBookmark(@Param("postId") Long postId, @Param("userId") Long userId);

}
