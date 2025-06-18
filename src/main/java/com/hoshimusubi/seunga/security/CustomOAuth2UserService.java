package com.hoshimusubi.seunga.security;

import com.hoshimusubi.seunga.mapper.UserMapper;
import com.hoshimusubi.seunga.model.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        String email = (String) oAuth2User.getAttributes().get("email");
        UserVO user = userMapper.getUserById(email);
        
        if (user == null) {
            
        	return new CustomOAuth2User(oAuth2User.getAttributes(), null);
        } else {

        return new CustomOAuth2User(oAuth2User.getAttributes(), user);
    }
}
    
}