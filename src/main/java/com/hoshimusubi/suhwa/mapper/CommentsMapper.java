package com.hoshimusubi.suhwa.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.suhwa.dto.CommentsDTO;

@Mapper
@MapperScan
public interface CommentsMapper {
	
	List<CommentsDTO> getCommentById(int postId);
	
	void insertComment(CommentsDTO comment);
	
	String getNicknameByUserId(int userId);
	
	void updateComment(@Param("id") int id, @Param("content") String content);
	void deleteComment(int id);

	int selectCount(int id);

	List<CommentsDTO> getCommentsPaged(Map<String, Object> params);


}
