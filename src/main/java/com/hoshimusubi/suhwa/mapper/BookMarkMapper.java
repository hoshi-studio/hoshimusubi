package com.hoshimusubi.suhwa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.suhwa.dto.UsersDTO;

@Mapper
@MapperScan
public interface BookMarkMapper {
	void insertBookmark(@Param("postId") Long postId, @Param("userId")Long userId);
    void deleteBookmark(@Param("postId") Long postId, @Param("userId")Long userId);
    boolean countBookmark(@Param("postId") Long postId, @Param("userId")Long userId);

}
