package com.hoshimusubi.seunga.security;

import com.hoshimusubi.seunga.mapper.UserMapper;
import com.hoshimusubi.seunga.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserMapper userMapper;

    @Autowired
    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	UserVO user = userMapper.getUserById(username);
        if (user == null) {
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다: " + username); // 모달창때문에 숨겨집니당
        }
        return new CustomUserDetails(user);
    }

}
