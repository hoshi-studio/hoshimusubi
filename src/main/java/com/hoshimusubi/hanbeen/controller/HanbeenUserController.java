package com.hoshimusubi.hanbeen.controller;

import com.hoshimusubi.hanbeen.dto.UserProfileDTO;
import com.hoshimusubi.hanbeen.service.HanbeenUserService;
import com.hoshimusubi.seunga.model.UserVO;
import com.hoshimusubi.seunga.security.CustomPrincipal;
import com.hoshimusubi.suhwa.dto.UsersDTO;
import com.hoshimusubi.suhwa.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HanbeenUserController {

    @Autowired
    @Qualifier("hanbeenUserServiceImpl")
    private HanbeenUserService userService;
    
    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService msgService;

    private UserVO getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        return  principal.getUser();
    }
    
    
    
    @GetMapping("/mypage")
    public String myPage(Model model) {
    	
    	UserVO loginUser = getLoginUser();
        int userid = loginUser.getId();
        // 테스트용으로 1번 사용자 ID 하드코딩
    	System.out.println("id값 : "+userid);
        
        // 서비스 호출
        UserProfileDTO userProfile = userService.getUserProfile(userid);
        // 모델에 담아서 JSP로
        System.out.println(userProfile);
        model.addAttribute("user", userProfile);
        return "mypage"; // → /WEB-INF/views/mypage.jsp
    }
}
