package com.hoshimusubi.seunga.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.hoshimusubi.seunga.model.UserVO;

public class CustomOAuth2User implements OAuth2User, CustomPrincipal {

    private final Map<String, Object> attributes;
    private final UserVO user; 

    public CustomOAuth2User(Map<String, Object> attributes, UserVO user) {
        this.attributes = attributes;
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user == null) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_GUEST"));
        } else {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getName() {
        return attributes.get("sub").toString();
    }

    public UserVO getUser() {
        return user;
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }
}