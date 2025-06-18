package com.hoshimusubi.seunga.security;

import com.hoshimusubi.seunga.model.UserVO;

public interface CustomPrincipal {
	UserVO getUser();
}
