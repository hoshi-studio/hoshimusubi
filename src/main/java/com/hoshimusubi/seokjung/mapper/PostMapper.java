package com.hoshimusubi.seokjung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hoshimusubi.seokjung.dto.PostDTO;

@Mapper
public interface PostMapper {
   
		List<PostDTO> selectPostsByZodiacId(Integer zodiacId);

}
