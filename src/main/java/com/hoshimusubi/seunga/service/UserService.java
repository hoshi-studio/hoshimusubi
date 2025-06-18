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
    
    public void insertUser(UserVO user) {
        userMapper.insertUser(user);
    }
    
    public int calculateZodiacId(String birthDate) {
        String[] parts = birthDate.split("[-/]");
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) return 1; // 물병자리
        if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) return 2; // 물고기자리
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return 3; // 양자리
        if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) return 4; // 황소자리
        if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) return 5; // 쌍둥이자리
        if ((month == 6 && day >= 22) || (month == 7 && day <= 22)) return 6; // 게자리
        if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return 7; // 사자자리
        if ((month == 8 && day >= 23) || (month == 9 && day <= 23)) return 8; // 처녀자리
        if ((month == 9 && day >= 24) || (month == 10 && day <= 22)) return 9; // 천칭자리
        if ((month == 10 && day >= 23) || (month == 11 && day <= 22)) return 10; // 전갈자리
        if ((month == 11 && day >= 23) || (month == 12 && day <= 24)) return 11; // 사수자리
        return 12; // 염소자리
    }

}
