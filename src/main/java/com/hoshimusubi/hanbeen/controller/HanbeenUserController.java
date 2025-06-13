package com.hoshimusubi.hanbeen.controller;

import com.hoshimusubi.hanbeen.dto.UserProfileDTO;
import com.hoshimusubi.hanbeen.service.HanbeenUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HanbeenUserController {

    @Autowired
    @Qualifier("hanbeenUserServiceImpl")
    private HanbeenUserService userService;

    @GetMapping("/mypage")
    public String myPage(Model model) {
        // 테스트용으로 1번 사용자 ID 하드코딩
        Long userId = 1L;

        // 서비스 호출
        UserProfileDTO userProfile = userService.getUserProfile(userId);

        // 모델에 담아서 JSP로
        model.addAttribute("user", userProfile);
        return "mypage"; // → /WEB-INF/views/mypage.jsp
    }
}
