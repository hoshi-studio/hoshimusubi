package com.hoshimusubi.suhwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoshimusubi.seunga.model.UserVO;
import com.hoshimusubi.suhwa.mapper.BookMarkMapper;
import com.hoshimusubi.suhwa.mapper.UsersMapper;

@Service("UsersServiceImpl")
@Transactional
public class UsersServiceImpl implements UsersService{
	
	@Autowired private UsersMapper UsersMapper;
	
	@Override
    public void deleteUser(int userId) {
		UsersMapper.deleteUser( userId);
    }

	@Override
    public void updateUser(UserVO user) {
		UsersMapper.updateUser(user);
    }
	
	@Override
    public boolean isNicknameTaken(String nickname, int userId) {
        return UsersMapper.isNicknameTaken(nickname, userId)>0;
    }
}
