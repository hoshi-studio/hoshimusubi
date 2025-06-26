package com.hoshimusubi.suhwa.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hoshimusubi.suhwa.dto.CommentsDTO;
import com.hoshimusubi.suhwa.dto.MessageDTO;
import com.hoshimusubi.suhwa.dto.PostsDTO;
import com.hoshimusubi.suhwa.dto.UsersDTO;
import com.hoshimusubi.junwoo.dto.Dto;
import com.hoshimusubi.junwoo.model.Postvo;
import com.hoshimusubi.junwoo.service.MemberService;
import com.hoshimusubi.seunga.model.UserVO;
import com.hoshimusubi.seunga.security.CustomPrincipal;
import com.hoshimusubi.seunga.security.CustomUserDetails;
import com.hoshimusubi.suhwa.service.BookMarkService;
import com.hoshimusubi.suhwa.service.CommentsService;
import com.hoshimusubi.suhwa.service.LikeService;
import com.hoshimusubi.suhwa.service.MessageService;
import com.hoshimusubi.suhwa.service.PostsService;
import com.hoshimusubi.suhwa.service.UsersService;

@Controller
public class Main {

    @Autowired
    @Qualifier("PostServiceImpl")
    private PostsService postsService;

    @Autowired
    @Qualifier("CommentsServiceImpl")
    private CommentsService commentsService;

    @Autowired
    @Qualifier("LikeServiceImpl")
    private LikeService likeService;

    @Autowired
    @Qualifier("BookMarkServiceImpl")
    private BookMarkService bmService;
    
    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService msgService;
    
    @Autowired
    @Qualifier("UsersServiceImpl")
    private UsersService usersService;
    
    @Autowired
    private MemberService memberService;

    private UserVO getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        return  principal.getUser();
    }

    @GetMapping("/")
    public String home() {
        System.out.println(">>> home controller called");
        return "home";
    }

    @GetMapping("/post_detail")
    public String postDetail(@RequestParam("id") int id,Model model,HttpSession session) {
        UserVO loginUser = getLoginUser();
        int userid = loginUser.getId();
        PostsDTO post = postsService.getPostById(id);
        
     // ✅ 세션에서 조회한 게시글 목록 가져오기
        @SuppressWarnings("unchecked")
        Set<Integer> viewedPostIds = (Set<Integer>) session.getAttribute("viewedPostIds");
        if (viewedPostIds == null) {
            viewedPostIds = new HashSet<>();
            session.setAttribute("viewedPostIds", viewedPostIds);
        }

        // ✅ 중복 조회 방지 로직
        if (!viewedPostIds.contains(id)) {
            postsService.increaseViewCount(id);  // 조회수 증가
            viewedPostIds.add(id);               // 세션에 저장
        }
        
        model.addAttribute("loginUserId", userid);

        boolean liked = likeService.isPostLikedByUser(id, userid);
        int likecount = likeService.getLikeCount(id);
        post.setLikeCount(likecount);
        post.setLikedByCurrentUser(liked);

        boolean bookmarked = bmService.isPostBookmarkedByUser(id, loginUser.getId());
        post.setBookmarkedByCurrentUser(bookmarked);
        
        int commentCount = commentsService.getcommentCount(id);
        post.setCommentCount(commentCount);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        String formattedCreatedAt = post.getCreated_at().format(formatter);
        
        model.addAttribute("formattedCreatedAt", formattedCreatedAt);
        model.addAttribute("post", post);
        return "post_detail_suhwa";
    }

    @GetMapping("/post_write")
    public String postWrite(@RequestParam("zodiacId") Integer zodiacId,Model model) {
    	System.out.println(zodiacId);
    	model.addAttribute("zodiacId",zodiacId);
        return "post_write_suhwa";
    }

    @PostMapping("/post_create")
    public String createPost(@ModelAttribute PostsDTO post,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             HttpServletRequest request,Integer zodiacId) {
        UserVO loginUser = getLoginUser();
        post.setUser_Id(loginUser.getId());
        post.setZodiacId(zodiacId);

        if (!imageFile.isEmpty()) {
            String uploadDir = request.getServletContext().getRealPath("/resources/upload/");

            // 원본 확장자 추출
            String originalFileName = imageFile.getOriginalFilename();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

            // 유저이름 + 시간 + UUID 일부로 파일명 생성
            String username = loginUser.getNickname(); // 또는 getNickname() 등 사용
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String uuidPart = UUID.randomUUID().toString().substring(0, 8);
            String fileName = username + "_" + timestamp + "_" + uuidPart + extension;

            File saveFile = new File(uploadDir, fileName);

            try {
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }
                imageFile.transferTo(saveFile);
                post.setImageUrl("/resources/upload/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        postsService.savePost(post);
        return "redirect:/zodiac/" + zodiacId;
    }

    @PostMapping("/addComment")
    @ResponseBody
    public Map<String, Object> addComment(@ModelAttribute CommentsDTO comment) {
        UserVO loginUser = getLoginUser();
        comment.setUserId(loginUser.getId());
        commentsService.saveComment(comment);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }
    
    @GetMapping("/comments")
    public String getPagedComments(@RequestParam("postId") int postId,
                                   @RequestParam(defaultValue = "1") int page,
                                   Model model,
                                   HttpSession session) {

    	UserVO loginUser = getLoginUser();
    	
        int pageSize = 5; // 한 페이지당 댓글 수
        int totalCount = commentsService.getcommentCount(postId);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);	

        List<CommentsDTO> pagedComments = commentsService.getCommentsPaged(postId, page, pageSize);
        
        model.addAttribute("comments", pagedComments);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("loginUserId", loginUser.getId());

        return "fragments/comments_fragment"; // /WEB-INF/views/fragments/comments_fragment.jsp
    }
    
    @GetMapping("/commentCount")
    @ResponseBody
    public Map<String, Object> getCommentCount(@RequestParam("postId") int postId) {
        int count = commentsService.getcommentCount(postId);
        Map<String, Object> result = new HashMap<>();
        result.put("count", count);
        return result;
    }

    @GetMapping("/post_modify")
    public String editPostForm(@RequestParam("id") int id, Model model) {
        PostsDTO post = postsService.getPostById(id);
        UserVO loginUser = getLoginUser();
        if (loginUser == null || !(loginUser.getId()==post.getUser_Id())) {
            return "redirect:/post_detail";
        }
        model.addAttribute("post", post);
        return "post_modify";
    }

    @PostMapping("/post_update")
    public String updatePost(@ModelAttribute PostsDTO post,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             @RequestParam(value = "removeImage", required = false) String removeImage,
                             HttpServletRequest request) {
        String uploadDir = request.getServletContext().getRealPath("/resources/upload/");
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) uploadFolder.mkdirs();

        if ("true".equals(removeImage)) {
            post.setImageUrl(null);
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            File dest = new File(uploadDir, fileName);
            try {
                imageFile.transferTo(dest);
                post.setImageUrl("/resources/upload/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        postsService.updatePost(post);
        return "redirect:/post_detail?id=" + post.getId();
    }

    @GetMapping("/post_delete")
    public String deletePost(@RequestParam("id") int id) {
        postsService.deletePost(id);
        return "redirect:/";
    }

    @PostMapping("/comment_update")
    @ResponseBody
    public String updateComment(@RequestParam int id, @RequestParam String content) {
        commentsService.updateComment(id, content);
        return "success";
    }

    @PostMapping("/comment_delete")
    @ResponseBody
    public Map<String, Object> deleteComment(@RequestParam int id, @RequestParam int postId) {
        commentsService.deleteComment(id);
        int commentCount = commentsService.getcommentCount(postId);

        Map<String, Object> result = new HashMap<>();
        result.put("commentCount", commentCount);
        return result;
    }

    @PostMapping("/like")
    @ResponseBody
    public Map<String, Object> like(@RequestParam int postId) {
        UserVO loginUser = getLoginUser();
        int userId = loginUser.getId();
        likeService.insertLike(postId, userId);
        int newCount = likeService.getLikeCount(postId);

        Map<String, Object> result = new HashMap<>();
        result.put("liked", true);
        result.put("likeCount", newCount);
        return result;
    }

    @PostMapping("/unlike")
    @ResponseBody
    public Map<String, Object> unlike(@RequestParam int postId) {
        UserVO loginUser = getLoginUser();
        int userId = loginUser.getId();
        likeService.deleteLike(postId, userId);
        int newCount = likeService.getLikeCount(postId);

        Map<String, Object> result = new HashMap<>();
        result.put("liked", false);
        result.put("likeCount", newCount);
        return result;
    }

    @PostMapping("/bookmark")
    @ResponseBody
    public Map<String, Object> bookmark(@RequestParam int postId) {
        UserVO loginUser1 = getLoginUser();
        int loginUser= loginUser1.getId();
        bmService.addBookmark(postId, loginUser);

        Map<String, Object> result = new HashMap<>();
        result.put("liked", true);
        return result;
    }

    @PostMapping("/unbookmark")
    @ResponseBody
    public Map<String, Object> dontsave(@RequestParam int postId) {
    	UserVO loginUser1 = getLoginUser();
    	int loginUser= loginUser1.getId();
        bmService.removeBookmark(postId, loginUser);

        Map<String, Object> result = new HashMap<>();
        result.put("liked", false);
        return result;
    }
    
    @GetMapping("/messageWrite")
    public String messageWrite(@RequestParam("userId") int userId, Model model) {
    	UsersDTO receiver = msgService.getUserById(userId);  // ← userId로 사용자 조회
        model.addAttribute("receiver", receiver);
        return "message_write_suhwa";
    }
    

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute MessageDTO msg) {
        UserVO loginUser = getLoginUser();
        msg.setSenderId(loginUser.getId());
        msgService.sendMessage(msg);
        return "redirect:/mypage2";
    }
    
    @GetMapping(value = "/checkNick", produces = "application/json")
    @ResponseBody
    public Map<String, Object> checkNickname(@RequestParam("nickname") String nickname) {
        UserVO loginUser = getLoginUser(); // 로그인 사용자 정보
        Map<String, Object> result = new HashMap<>();

        boolean isTaken = usersService.isNicknameTaken(nickname, loginUser.getId()); // 본인 제외
        result.put("available", !isTaken);
        return result;
    }
    
    @PostMapping(value = "/updateMem", produces = "application/json")
    @ResponseBody
    public Map<String, Object> updateUser(@RequestParam("birthDate") String birthDate,
					            @RequestParam("nickname") String nickname,
					            @RequestParam("gender") String gender,
					            @RequestParam("profileImage") MultipartFile file,
					            HttpServletRequest request,
					            Model model) {
    	UserVO loginUser1 = getLoginUser();
    	Map<String, Object> result = new HashMap<>();
 
    	
    	
    	try {
    		boolean isTaken = usersService.isNicknameTaken(nickname, loginUser1.getId());
            if (isTaken) {
                result.put("success", false);
                result.put("message", "既に使用中のニックネームです。");
                return result;  // ✅ 중복이면 더 이상 진행하지 않음
            }
    		
            // 1. 프로필 사진 저장 경로 설정
            String uploadDir = request.getServletContext().getRealPath("/resources/profile/");
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String originalFileName = (file != null) ? file.getOriginalFilename() : "";
            String fileName;

            if (file != null && originalFileName != null && !originalFileName.isEmpty()) {
                // 확장자 추출
                String extension = "";
                int dotIndex = originalFileName.lastIndexOf(".");
                if (dotIndex > 0) {
                    extension = originalFileName.substring(dotIndex);
                }

                // 닉네임 기반 파일명
                fileName = nickname + extension;
                String profilePicPath = uploadDir + File.separator + fileName;
                file.transferTo(new File(profilePicPath));
                fileName = "/resources/profile/" + fileName;
            } else {
                fileName = "/resources/img/default.png"; // 기본 이미지
            }

            // 2. 별자리 계산
            int zodiacId = memberService.calculateZodiacId(birthDate);

            // 3. VO 세팅
            UserVO member = new UserVO();
            try {
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                member.setBirthDate(sqlDate);
            } catch (ParseException e) {
                throw new RuntimeException("생년월일 형식 오류", e);
            }

            member.setGender(gender);
            member.setNickname(nickname);
            member.setZodiacId(zodiacId);
            member.setProfilePic(fileName);
            member.setId(getLoginUser().getId());

            usersService.updateUser(member);

            result.put("success", true);
            result.put("message", "회원정보가 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "수정 실패: " + e.getMessage());
        }

        return result;
    }

    @PostMapping("/deleteMem")
    public String deleteUser(HttpSession session) {
    	UserVO loginUser1 = getLoginUser();
        usersService.deleteUser(loginUser1.getId());
        session.invalidate(); // 로그아웃 처리
        return "redirect:/";
    }
    
    
  
}

