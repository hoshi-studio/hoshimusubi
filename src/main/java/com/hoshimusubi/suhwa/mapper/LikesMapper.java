package com.hoshimusubi.suhwa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.suhwa.dto.UsersDTO;

@Mapper
@MapperScan
public interface LikesMapper {
	void insertLike(@Param("postId") int postId, @Param("userId")int userId);
	void deleteLike(@Param("postId") int postId, @Param("userId")int userId);
	int countLikesByPostId(int postId);
	int exists(@Param("postId")int postId, @Param("userId")int userId);

}
