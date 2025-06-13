package com.hoshimusubi.seunga.service;

import com.hoshimusubi.seunga.dto.UserDto;
import com.hoshimusubi.seunga.mapper.UserMapper;
import com.hoshimusubi.seunga.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean authenticateUser(UserDto userDto) {

        String id = userDto.getId();
        String password = userDto.getPassword();

        UserVO user = userMapper.getUserById(id);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true; 
        }

        return false; 
    }

}
