package com.hoshimusubi.suhwa.service;

import com.hoshimusubi.seunga.model.UserVO;

public interface UsersService {

	void updateUser(UserVO user);
    void deleteUser(int id);
	boolean isNicknameTaken(String nickname, int userId);

}
