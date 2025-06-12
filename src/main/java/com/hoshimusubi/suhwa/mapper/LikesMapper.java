package com.hoshimusubi.suhwa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
@MapperScan
public interface LikesMapper {
	void insertLike(@Param("postId") Long postId, @Param("userId") Long userId);
	void deleteLike(@Param("postId") Long postId, @Param("userId") Long userId);
	int countLikesByPostId(Long postId);
	int countUserLike(@Param("postId") Long postId, @Param("userId") Long userId);

}
