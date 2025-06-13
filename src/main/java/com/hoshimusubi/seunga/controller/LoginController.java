package com.hoshimusubi.seunga.controller;

import com.hoshimusubi.seunga.dto.UserDto;
import com.hoshimusubi.seunga.service.UserService;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	 	@Autowired
	    private UserService userService;

	    @GetMapping("/login")
	    public String login() {
	        return "login"; 
	    }

	    @PostMapping("/login")
	    public String authenticate(@RequestParam("id") String id, 
	                               @RequestParam("password") String password,
	                               RedirectAttributes redirectAttributes) {
	    	
	        UserDto userDto = new UserDto();
	        userDto.setId(id);
	        userDto.setPassword(password);

	        // 인증 처리
	        boolean authenticated = userService.authenticateUser(userDto);

	        if (authenticated) {
	        	 redirectAttributes.addFlashAttribute("successMessage", "ログインに成功しました。");
	             return "redirect:/";
	        }
	        
	        redirectAttributes.addFlashAttribute("loginError", "ログインに失敗しました。");
	        return "redirect:/login";
	        
	    }

}
