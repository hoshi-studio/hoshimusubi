package com.hoshimusubi.seunga.mapper;

import com.hoshimusubi.seunga.model.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserVO getUserById(String userId);
}
