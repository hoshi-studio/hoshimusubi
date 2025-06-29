package com.hoshimusubi.seunga.controller;

import com.hoshimusubi.junwoo.dto.Dto;
import com.hoshimusubi.seunga.dto.UserDto;
import com.hoshimusubi.seunga.model.UserVO;
import com.hoshimusubi.seunga.security.CustomOAuth2User;
import com.hoshimusubi.seunga.security.CustomPrincipal;
import com.hoshimusubi.seunga.security.CustomUserDetails;
import com.hoshimusubi.seunga.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	    		@ModelAttribute Dto dto,
	            @RequestParam("birthDate") String birthDate,
	            @RequestParam("nickname") String nickname,
	            @RequestParam("gender") String gender,
	            @RequestParam("profileImage") MultipartFile file,
	            Authentication authentication,
	            RedirectAttributes redirectAttributes,
	            HttpServletRequest request
	    ) throws IOException { 
	    	
	    	CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
	        String userId = user.getEmail();
	        
	    	String password = "gmail";
	  
	        int zodiacId = userService.calculateZodiacId(birthDate);

	        String uploadDir = request.getServletContext().getRealPath("/resources/profile"); 
	        File dir = new File(uploadDir);

	        // 디렉토리가 없다면 생성
	        if (!dir.exists()) {
	            dir.mkdirs();  // 디렉토리가 없다면 생성
	            System.out.println("디렉토리 생성: " + uploadDir);
	        }

	        String originalFileName = file.getOriginalFilename();
	        String fileName;

	        if (originalFileName != null && !originalFileName.isEmpty()) {
	        	
	            String extension = "";
	            int dotIndex = originalFileName.lastIndexOf(".");
	            if (dotIndex > 0) {
	                extension = originalFileName.substring(dotIndex);
	            }

	            fileName =nickname + extension;
	            
	            String profilePicPath = uploadDir + File.separator + fileName;
	            System.out.println(profilePicPath);
	            try {
	                file.transferTo(new File(profilePicPath));
	            } catch (IOException e) {
	                return "registerFailpic";
	            }
	            fileName="/resources/profile/"+fileName;
	        } else {
	            fileName = "/resources/img/default.jpg";
	        }

	        UserVO newUser = new UserVO();
	        newUser.setUserId(userId);
	        newUser.setPassword(password);
	        newUser.setZodiacId(zodiacId);
	        newUser.setNickname(nickname);
	        newUser.setGender(gender);
	        newUser.setBirthDate(java.sql.Date.valueOf(birthDate));
	        newUser.setProfilePic(fileName);

	        userService.insertUser(newUser);
	        
	        
	        SecurityContextHolder.clearContext();
	        request.getSession().invalidate();
	    	
	        return "redirect:/";
	    }
	    
	    @GetMapping(value = "/checkNickname" , produces = "application/json")
	    @ResponseBody
	    public Map<String, Boolean> checkNickname(@RequestParam String nickname) {
	        boolean exists = userService.isNicknameExists(nickname);
	        Map<String, Boolean> result = new HashMap<>();
	        result.put("exists", exists);
	        return result;
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
