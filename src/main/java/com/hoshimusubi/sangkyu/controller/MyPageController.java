package com.hoshimusubi.sangkyu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import com.hoshimusubi.sangkyu.service.MyPageService;

@Controller
@RequestMapping("/sangkyu")
public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    // 마이페이지 메인
    @GetMapping("")
    public String main(HttpSession session, Model model) {
        // ★ 테스트용 userId 자동 세팅
        if (session.getAttribute("userId") == null) {
            session.setAttribute("userId", 1L); // 1번 유저 임시 로그인
        }
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("profile", myPageService.getMyProfile(userId));
        model.addAttribute("postCount", myPageService.getMyPosts(userId).size());
        model.addAttribute("commentCount", myPageService.getMyComments(userId).size());
        model.addAttribute("likeCount", myPageService.getMyLikes(userId).size());
        model.addAttribute("scrapCount", myPageService.getMyScraps(userId).size());
        return "sangkyu_mypage/sangkyuMain";
    }

    // 내가 쓴 글 목록
    @GetMapping("/posts")
    public String posts(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            session.setAttribute("userId", 1L);
        }
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("list", myPageService.getMyPosts(userId));
        return "sangkyu_mypage/sangkyuList";
    }

    // 내가 단 댓글 목록
    @GetMapping("/comments")
    public String comments(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            session.setAttribute("userId", 1L);
        }
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("list", myPageService.getMyComments(userId));
        return "sangkyu_mypage/sangkyuList";
    }

    // 내가 좋아요한 글 목록
    @GetMapping("/likes")
    public String likes(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            session.setAttribute("userId", 1L);
        }
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("list", myPageService.getMyLikes(userId));
        return "sangkyu_mypage/sangkyuList";
    }

    // 내가 스크랩한 글 목록
    @GetMapping("/scraps")
    public String scraps(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            session.setAttribute("userId", 1L);
        }
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("list", myPageService.getMyScraps(userId));
        return "sangkyu_mypage/sangkyuList";
    }
}



