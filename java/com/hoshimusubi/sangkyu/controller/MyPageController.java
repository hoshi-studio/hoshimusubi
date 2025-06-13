package com.hoshimusubi.sangkyu.controller;

// 필요한 모든 import 문을 여기에 정리합니다.
// MemberVO와 MemberService의 실제 패키지 경로를 다시 확인해주세요.
// 만약 MemberVO가 com.hoshimusubi.domain.MemberVO 에 있다면 해당 경로로 유지하고,
// MemberService가 com.hoshimusubi.service.MemberService 에 있다면 해당 경로로 유지하세요.
// 그러나 이전에 MemberService가 com.hoshimusubi.sangkyu.service 에 있다고 하셨으니,
// MemberVO도 com.hoshimusubi.sangkyu.domain 에 있을 가능성이 높습니다.
// 정확한 패키지 경로로 아래 import 문을 수정해주세요.

import com.hoshimusubi.sangkyu.domain.MemberVO; // <-- 이 경로가 MemberVO의 실제 경로인지 확인!
import com.hoshimusubi.sangkyu.service.MemberService; // <-- 이 경로가 MemberService의 실제 경로인지 확인!

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;       // <-- 추가
import org.springframework.http.ResponseEntity;   // <-- 추가
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // <-- 추가
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // <-- 추가
import org.springframework.web.bind.annotation.ResponseBody;  // <-- 추가
import org.springframework.web.multipart.MultipartFile; // <-- 추가

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    // @Autowired 필드는 클래스 선언 바로 아래에 위치시키는 것이 관례입니다. (92행 노란전구 해결)
    @Autowired
    private MemberService memberService;

    // 1. 중복된 @GetMapping 매핑 해결 (86행, 96행 노란전구 해결)
    // 마이페이지 메인 뷰 로딩: memberService를 통해 회원 정보 가져와 JSP로 전달
    @GetMapping // /mypage 요청을 처리
    public String myPage(Model model) { // 메서드 이름을 myPage로 통합하고 Model 사용
        System.out.println(">>> myPage controller called (main view)");

        // 실제로는 로그인 세션에서 memberId를 가져와야 합니다.
        // 현재는 예시로 memberId 1을 사용
        int currentMemberId = 1;

        // memberService를 통해 회원 정보를 가져옴 (프로필 이미지 경로 포함)
        MemberVO member = memberService.getMemberInfo(currentMemberId);
        model.addAttribute("member", member); // JSP에서 ${member.profileImagePath} 등으로 사용 가능

        return "mypage"; // mypage.jsp로 이동
    }

    // 게시물 목록 페이지
    @GetMapping("/posts")
    public String myPosts(Model model) {
        System.out.println(">>> myPosts controller called");
        return "mypage_posts";
    }

    // 댓글 목록 페이지
    @GetMapping("/comments")
    public String myComments(Model model) {
        System.out.println(">>> myComments controller called");
        return "mypage_comments";
    }

    // 좋아요 목록 페이지
    @GetMapping("/likes")
    public String myLikes(Model model) {
        System.out.println(">>> myLikes controller called");
        return "mypage_likes";
    }

    // 스크랩 목록 페이지
    @GetMapping("/scraps")
    public String myScraps(Model model) {
        System.out.println(">>> myScraps controller called");
        return "mypage_scraps";
    }

    // 메시지 목록 페이지
    @GetMapping("/messages")
    public String myMessages(Model model) {
        System.out.println(">>> myMessages controller called");
        return "mypage_messages";
    }

    // 프로필 이미지 업로드 요청 처리 (AJAX 호출에 대한 응답)
    @PostMapping("/uploadProfileImage") // <-- 76행 노란전구 해결 (PostMapping import 필요)
    @ResponseBody                      // <-- 77행 노란전구 해결 (ResponseBody import 필요)
    public ResponseEntity<Map<String, Object>> uploadProfileImage( // <-- 79, 80행 노란전구 해결 (ResponseEntity, HttpStatus import 필요)
            @RequestParam("profileImage") MultipartFile file, // <-- 78행 노란전구 해결 (RequestParam, MultipartFile import 필요)
            @RequestParam(value = "memberId", defaultValue = "1") int memberId) {

        Map<String, Object> response = new HashMap<>();

        try {
            String newImagePath = memberService.uploadProfileImage(memberId, file);

            if (newImagePath != null) {
                response.put("success", true);
                response.put("message", "프로필 사진이 성공적으로 변경되었습니다.");
                response.put("newImagePath", newImagePath);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("success", false);
                response.put("message", "선택된 파일이 없습니다.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
