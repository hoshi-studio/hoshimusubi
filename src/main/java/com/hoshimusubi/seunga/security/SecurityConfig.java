package com.hoshimusubi.seunga.security;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoshimusubi.seunga.mapper.UserMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private CustomUserDetailsService customUserDetailsService;
	private CustomOAuth2UserService customOAuth2UserService;
	
	@Autowired
	private UserMapper userMapper;


    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, 
                          CustomOAuth2UserService customOAuth2UserService) {
        this.customUserDetailsService = customUserDetailsService;
        this.customOAuth2UserService = customOAuth2UserService;
    }
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
            .authorizeRequests()
            .antMatchers("/signupExtra", "/dosignupExtra").hasAuthority("ROLE_GUEST")
            	.antMatchers("/.well-known/**", "/", "/login", "/register", "/oauth2Redirect","/checkNickname", "/resources/**", "/css/**", "/js/**").permitAll()
            	.anyRequest().hasRole("USER")
            .and()
            .oauth2Login()
            	.loginPage("/login")
            	.userInfoEndpoint()
                	.userService(customOAuth2UserService)
            .and()
            .defaultSuccessUrl("/oauth2Redirect")
            .and() 
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/dologin")
                .defaultSuccessUrl("/",true)
                .failureUrl("/login?error=true") // 실패 시 이동
                .permitAll()
                .and()
                .logout()
                	.logoutUrl("/logout")
                	.logoutSuccessUrl("/") // 로그아웃 후 홈으로 이동
                	.permitAll()
                	.permitAll();
    }
	
	
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(customUserDetailsService)
	            .passwordEncoder(passwordEncoder());
	    }
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() throws IOException {
    	
    	ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("oauth2.json");
        JsonNode root = mapper.readTree(is);
        JsonNode googleNode = root.path("google");

        String clientId = googleNode.path("clientId").asText();
        String clientSecret = googleNode.path("clientSecret").asText();
    	
    	
        ClientRegistration google = ClientRegistration.withRegistrationId("google")
            .clientId(clientId)
            .clientSecret(clientSecret)
            .scope("profile", "email")
            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
            .tokenUri("https://oauth2.googleapis.com/token")
            .userInfoUri("https://openidconnect.googleapis.com/v1/userinfo")
            .userNameAttributeName("sub")
            .clientName("Google")
            .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .build();

        return new InMemoryClientRegistrationRepository(google);
    }
   

}
