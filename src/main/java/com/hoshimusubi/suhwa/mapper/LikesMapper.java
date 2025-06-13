package com.hoshimusubi.suhwa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.suhwa.dto.UsersDTO;

@Mapper
@MapperScan
public interface LikesMapper {
	void insertLike(@Param("postId") Long postId, UsersDTO userId);
	void deleteLike(@Param("postId") Long postId, UsersDTO userId);
	int countLikesByPostId(Long postId);
	int exists(@Param("postId")Long postId, UsersDTO userId);

}
