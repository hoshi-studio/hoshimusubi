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
            return "registerFailid";
        }

        // 1. 중복 확인
        if (memberService.isEmailDuplicate(dto.getEmail())) {
            return "registerFailemail";
        }

        if (memberService.isNicknameDuplicate(dto.getNickname())) {
            return "registerFailnickname";
        }
        
        if (memberService.isValidPassword(dto.getPassword())) {
            return "registerFailpwd";
        }
        
        

     // 2. 프로필 사진 저장 경로 설정
        String uploadDir = request.getServletContext().getRealPath("/resources/profile/");
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String originalFileName = file.getOriginalFilename();
        String fileName;

        if (originalFileName != null && !originalFileName.isEmpty()) {
            // 확장자 추출
            String extension = "";
            int dotIndex = originalFileName.lastIndexOf(".");
            if (dotIndex > 0) {
                extension = originalFileName.substring(dotIndex);
            }

            // 닉네임으로 파일명 설정 (특수문자 제거 등 안전하게 처리 가능)
            String nickname = dto.getNickname(); // 닉네임 가져오기
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
            fileName               // Web 경로
        );

        // 5. 회원 저장
        memberService.register(member);

        return "registerSuccess";
    }
    

}