package com.hoshimusubi.junwoo.controller;

import com.hoshimusubi.junwoo.dto.Dto;
import com.hoshimusubi.junwoo.model.Postvo;
import com.hoshimusubi.junwoo.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class Register {

    @Autowired
    private MemberService memberService;
    
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Dto dto,
                           @RequestParam("profileImage") MultipartFile file,
                           HttpServletRequest request,
                           Model model) {
    	
        //0. 이메일 형식 확인
    	String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!dto.getEmail().matches(emailRegex)) {
            model.addAttribute("error", "이메일 형식이 올바르지 않습니다.");
            return "registerFailid";
        }

        // 1. 중복 확인
        if (memberService.isEmailDuplicate(dto.getEmail())) {
            return "registerFailemail";
        }

        if (memberService.isNicknameDuplicate(dto.getNickname())) {
            return "registerFailnickname";
        }

        // 2. 프로필 사진 저장 경로 설정
        String uploadDir = request.getServletContext().getRealPath("/uploads/");
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String fileName = file.getOriginalFilename();
        String profilePicPath = uploadDir + File.separator + fileName;

        try {
            file.transferTo(new File(profilePicPath));
        } catch (IOException e) {
            return "registerFailpic";
        }

     // 3. 별자리 계산
        int zodiacId = memberService.calculateZodiacId(dto.getBirthdate());

        // 4. VO로 변환
        Postvo member = new Postvo(
            dto.getEmail(),                      // userId
            dto.getPassword(),
            zodiacId,
            dto.getNickname(),
            dto.getGender(),
            dto.getBirthdate(),
            "/uploads/" + fileName               // Web 경로
        );

        // 5. 회원 저장
        memberService.register(member);

        return "registerSuccess";
    }
    

}