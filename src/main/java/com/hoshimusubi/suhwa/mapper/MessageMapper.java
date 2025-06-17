package com.hoshimusubi.suhwa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.suhwa.dto.MessageDTO;
import com.hoshimusubi.suhwa.dto.UsersDTO;

@Mapper
@MapperScan
public interface MessageMapper {

	void insertMessage(MessageDTO message);

	UsersDTO findUserById(Long userId);

}
