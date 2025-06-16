package com.hoshimusubi.seokjung.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.seokjung.dto.PostDTO;

@Mapper
@MapperScan
public interface PostMapper {
		
		List<PostDTO> selectPostsByZodiacIdSorted(Map<String, Object> paramMap);
	
		int countPostsByZodiacId(Integer zodiacId);
	
		List<PostDTO> selectAllZodiacs();
		
}
