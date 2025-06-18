package com.hoshimusubi.seunga.controller;

import com.hoshimusubi.seunga.dto.UserDto;
import com.hoshimusubi.seunga.model.UserVO;
import com.hoshimusubi.seunga.security.CustomOAuth2User;
import com.hoshimusubi.seunga.security.CustomPrincipal;
import com.hoshimusubi.seunga.security.CustomUserDetails;
import com.hoshimusubi.seunga.service.UserService;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	 	@Autowired
	    private UserService userService;

	    @GetMapping("/login")
	    public String login() {
	        return "login"; 
	    }
	    
	    @GetMapping("/signupExtra")
	    public String signupExtra(Authentication authentication, Model model) {
	    	CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
	        model.addAttribute("oauth2_email", user.getEmail());
	        return "signupExtra"; 
	    }
	    
	    @GetMapping("/oauth2Redirect")
	    public String oauth2Redirect(Authentication authentication) {
	        CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
	        
	        if (user.getUser() == null) {
	        	
	            return "redirect:/signupExtra";
	        }
	        return "redirect:/";
	    }
	    
	    @PostMapping("/dosignupExtra")
	    public String signupExtraSubmit(
	            @RequestParam("birthDate") String birthDate,
	            @RequestParam("nickname") String nickname,
	            @RequestParam("gender") String gender,
	            @RequestParam("profileImage") MultipartFile profileImage,
	            Authentication authentication
	    ) throws IOException { 
	    	
	    	CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
	        String userId = user.getEmail();
	        
	    	String password = "gmail";

	        int zodiacId = userService.calculateZodiacId(birthDate);

	        String profilePic = null;
	        if (!profileImage.isEmpty()) {
	            String uploadDir = "C:/upload/profile/";
	            File uploadFolder = new File(uploadDir);
	            if (!uploadFolder.exists()) {
	                uploadFolder.mkdirs();
	            }
	            String saveName = System.currentTimeMillis() + "_" + profileImage.getOriginalFilename();
	            File saveFile = new File(uploadDir, saveName);
	            profileImage.transferTo(saveFile);
	            profilePic = saveName;
	        }

	        UserVO newUser = new UserVO();
	        newUser.setUserId(userId);
	        newUser.setPassword(password);
	        newUser.setZodiacId(zodiacId);
	        newUser.setNickname(nickname);
	        newUser.setGender(gender);
	        newUser.setBirthDate(java.sql.Date.valueOf(birthDate));
	        newUser.setProfilePic(profilePic);

	        userService.insertUser(newUser);
	    	
	        return "redirect:/";
	    }
	    
	    
		/*
		 * @PostMapping("/login") public String authenticate(@RequestParam("id") String
		 * id,
		 * 
		 * @RequestParam("password") String password, RedirectAttributes
		 * redirectAttributes) {
		 * 
		 * UserDto userDto = new UserDto(); userDto.setId(id);
		 * userDto.setPassword(password);
		 * 
		 * // 인증 처리 boolean authenticated = userService.authenticateUser(userDto);
		 * 
		 * if (authenticated) { 
		 * request.getSession().setAttribute("loginUser", id); 
		 * redirectAttributes.addFlashAttribute("successMessage",
		 * "ログインに成功しました。"); return "redirect:/"; }
		 * 
		 * redirectAttributes.addFlashAttribute("loginError", "ログインに失敗しました。"); return
		 * "redirect:/login";
		 * 
		 * }
		 */
	    
	    @GetMapping("/checkUser")
	    public String checkUser(Authentication authentication) {
	        // SecurityContextHolder에서 자동으로 주입됨
	        System.out.println("=== Principal: " + authentication.getPrincipal());

	        CustomPrincipal user = (CustomPrincipal) authentication.getPrincipal();
	        UserVO userVO = user.getUser();
	        
	        System.out.println("=== 현재 사용자: " + userVO.getUserId());

	        return "home";
	    }
	    
}
