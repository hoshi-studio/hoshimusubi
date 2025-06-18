package com.hoshimusubi.suhwa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.suhwa.dto.UsersDTO;

@Mapper
@MapperScan
public interface BookMarkMapper {
	void insertBookmark(@Param("postId") int postId, @Param("userId")int userId);
    void deleteBookmark(@Param("postId") int postId, @Param("userId")int userId);
    boolean countBookmark(@Param("postId") int postId, @Param("userId")int userId);

}
