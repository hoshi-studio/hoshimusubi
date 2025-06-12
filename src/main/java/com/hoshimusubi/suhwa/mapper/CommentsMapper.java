package com.hoshimusubi.suhwa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.suhwa.dto.CommentsDTO;

@Mapper
@MapperScan
public interface CommentsMapper {
	
	List<CommentsDTO> getCommentById(Long postId);
	
	void insertComment(CommentsDTO comment);
	
	String getNicknameByUserId(Long userId);

}
