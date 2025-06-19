package com.hoshimusubi.suhwa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.hoshimusubi.seunga.model.UserVO;

@Mapper
@MapperScan
public interface UsersMapper {

	void updateUser(UserVO user);
    void deleteUser(int userid);

}
