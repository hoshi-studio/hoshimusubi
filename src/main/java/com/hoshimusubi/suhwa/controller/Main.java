package com.hoshimusubi.suhwa.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.hoshimusubi.suhwa.dto.CommentsDTO;
import com.hoshimusubi.suhwa.dto.MessageDTO;
import com.hoshimusubi.suhwa.dto.PostsDTO;
import com.hoshimusubi.suhwa.dto.UsersDTO;
import com.hoshimusubi.seunga.model.UserVO;
import com.hoshimusubi.seunga.security.CustomUserDetails;
import com.hoshimusubi.suhwa.service.BookMarkService;
import com.hoshimusubi.suhwa.service.CommentsService;
import com.hoshimusubi.suhwa.service.LikeService;
import com.hoshimusubi.suhwa.service.MessageService;
import com.hoshimusubi.suhwa.service.PostsService;

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

    private UserVO getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getUser();
    }

    @GetMapping("/")
    public String home() {
        System.out.println(">>> home controller called");
        return "home";
    }

    @GetMapping("/post_detail")
    public String postDetail(@RequestParam("id") Long id, Model model) {
        UserVO loginUser = getLoginUser();
        Long userid = loginUser.getId();
        PostsDTO post = postsService.getPostById(id);
        
        postsService.increaseViewCount(id);
        
        model.addAttribute("loginUserId", userid);

        boolean liked = likeService.isPostLikedByUser(id, userid);
        int likecount = likeService.getLikeCount(id);
        post.setLikeCount(likecount);
        post.setLikedByCurrentUser(liked);

        boolean bookmarked = bmService.isPostBookmarkedByUser(id, loginUser.getId());
        post.setBookmarkedByCurrentUser(bookmarked);

        List<CommentsDTO> comments = commentsService.getCommentById(id);
        int commentCount = commentsService.getcommentCount(id);
        post.setCommentCount(commentCount);

        model.addAttribute("comments", comments);
        model.addAttribute("post", post);
        return "post_detail_suhwa";
    }

    @GetMapping("/post_write")
    public String postWrite() {
        return "post_write_suhwa";
    }

    @PostMapping("/post_create")
    public String createPost(@ModelAttribute PostsDTO post,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             HttpServletRequest request) {

        UserVO loginUser = getLoginUser();
        post.setUser_Id(loginUser.getId());

        if (!imageFile.isEmpty()) {
            String uploadDir = request.getServletContext().getRealPath("/resources/upload/");
            String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
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
        return "redirect:/post/list";
    }

    @PostMapping("/addComment")
    @ResponseBody
    public Map<String, Object> addComment(@ModelAttribute CommentsDTO comment) {
        UserVO loginUser = getLoginUser();
        comment.setUserId(loginUser.getId());
        commentsService.saveComment(comment);

        int commentCount = commentsService.getcommentCount(comment.getPostId());

     // 댓글 HTML 생성
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='comment' id='comment-").append(comment.getId()).append("'>");
        sb.append("<div class='comment-header'><strong>").append(comment.getNickname()).append("</strong></div>");
        sb.append("<div class='comment-content'>").append(comment.getContent()).append("</div>");

        // 로그인 유저와 작성자가 같으면 수정/삭제 버튼 추가
        if (loginUser.getId().equals(comment.getUserId())) {
            sb.append("<div class='comment-actions'>")
              .append("<a href='javascript:void(0);' class='edit-btn' data-id='").append(comment.getId()).append("'>수정</a> ")
              .append("<a href='javascript:void(0);' class='delete-btn' data-id='").append(comment.getId()).append("'>삭제</a>")
              .append("</div>");
        }

        sb.append("</div>");

        Map<String, Object> result = new HashMap<>();
        result.put("commentHtml", sb.toString());
        result.put("commentCount", commentCount);
        return result;
    }

    @GetMapping("/post_modify")
    public String editPostForm(@RequestParam("id") Long id, Model model) {
        PostsDTO post = postsService.getPostById(id);
        UserVO loginUser = getLoginUser();
        if (loginUser == null || !loginUser.getId().equals(post.getUser_Id())) {
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

        UserVO loginUser = getLoginUser();
        if (loginUser == null || !loginUser.getId().equals(post.getUser_Id())) {
            return "redirect:/";
        }

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
    public String deletePost(@RequestParam("id") Long id) {
        postsService.deletePost(id);
        return "redirect:/";
    }

    @PostMapping("/comment_update")
    @ResponseBody
    public String updateComment(@RequestParam Long id, @RequestParam String content) {
        commentsService.updateComment(id, content);
        return "success";
    }

    @PostMapping("/comment_delete")
    @ResponseBody
    public Map<String, Object> deleteComment(@RequestParam Long id, @RequestParam Long postId) {
        commentsService.deleteComment(id);
        int commentCount = commentsService.getcommentCount(postId);

        Map<String, Object> result = new HashMap<>();
        result.put("commentCount", commentCount);
        return result;
    }

    @PostMapping("/like")
    @ResponseBody
    public Map<String, Object> like(@RequestParam Long postId) {
        UserVO loginUser = getLoginUser();
        Long userId = loginUser.getId();
        likeService.insertLike(postId, userId);
        int newCount = likeService.getLikeCount(postId);

        Map<String, Object> result = new HashMap<>();
        result.put("liked", true);
        result.put("likeCount", newCount);
        return result;
    }

    @PostMapping("/unlike")
    @ResponseBody
    public Map<String, Object> unlike(@RequestParam Long postId) {
        UserVO loginUser = getLoginUser();
        Long userId = loginUser.getId();
        likeService.deleteLike(postId, userId);
        int newCount = likeService.getLikeCount(postId);

        Map<String, Object> result = new HashMap<>();
        result.put("liked", false);
        result.put("likeCount", newCount);
        return result;
    }

    @PostMapping("/bookmark")
    @ResponseBody
    public Map<String, Object> bookmark(@RequestParam Long postId) {
        UserVO loginUser1 = getLoginUser();
        Long loginUser= loginUser1.getId();
        bmService.addBookmark(postId, loginUser);

        Map<String, Object> result = new HashMap<>();
        result.put("liked", true);
        return result;
    }

    @PostMapping("/unbookmark")
    @ResponseBody
    public Map<String, Object> dontsave(@RequestParam Long postId) {
    	UserVO loginUser1 = getLoginUser();
        Long loginUser= loginUser1.getId();
        bmService.removeBookmark(postId, loginUser);

        Map<String, Object> result = new HashMap<>();
        result.put("liked", false);
        return result;
    }
    
    @GetMapping("/messageWrite")
    public String messageWrite(@RequestParam("userId") Long userId, Model model) {
        model.addAttribute("receiver", userId);
        System.out.println(userId);
        return "message_write_suhwa";
    }
    
    @PostMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(@ModelAttribute MessageDTO msg) {
        UserVO loginUser = getLoginUser();
        msg.setSenderId(loginUser.getId());
        msgService.sendMessage(msg);

        
        return "redirect:/mypage?id=" + msg.getId();
    }
}
