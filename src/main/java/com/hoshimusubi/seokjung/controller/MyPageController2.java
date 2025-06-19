package com.hoshimusubi.seokjung.controller;


import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoshimusubi.seokjung.dto.MyPageContentDTO;
import com.hoshimusubi.seokjung.dto.MyPageDTO2;
import com.hoshimusubi.seokjung.dto.ReceiveMessageDTO;

import com.hoshimusubi.seokjung.service.MyPageServiceImplYSJ;

import com.hoshimusubi.seunga.model.UserVO;
import com.hoshimusubi.seunga.security.CustomUserDetails;

@Controller
public class MyPageController2 {
	
		@Autowired
	    private MyPageServiceImplYSJ myPageService;
		
	    private UserVO getLoginUser() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
	        return userDetails.getUser();
	    }
	    
	    @GetMapping("/mypage2")
	    public String mypage( 
	    		@RequestParam(name = "postSort", defaultValue = "recent") String postSort,
	    		@RequestParam(name = "commentSort", defaultValue = "recent") String commentSort,
	    		@RequestParam(name = "likeSort", defaultValue = "recent") String likeSort,
	    		@RequestParam(name = "bookMarkSort", defaultValue = "recent") String bookMarkSort,
	    		
	            @RequestParam(name = "page", defaultValue = "1") int page,
	            @RequestParam(name = "commentPage", defaultValue = "1") int commentPage,
	            @RequestParam(name = "likePage", defaultValue = "1") int likePage,
	            @RequestParam(name = "bookMarkPage", defaultValue = "1") int bookMarkPage,
	            
	            Model model) {
	    	
	    	UserVO loginUser = getLoginUser();
	    	
	    	if (loginUser == null) return "redirect:/login";
	    	
	    	int userId = loginUser.getId();
	    	
	    	int pageSize = 6;
	    	
	        int offset = (page - 1) * pageSize;
	        int commentOffset = (commentPage - 1) * pageSize;
	        int likeOffset = (likePage - 1) * pageSize;
	        int bookMarkOffset = (bookMarkPage - 1) * pageSize;
	        
	        
	        int totalCount = myPageService.countUserPosts(userId);
	        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
	        
	        int commentTotal = myPageService.countCommentsByUser(userId);
	        int commentTotalPages = (int) Math.ceil((double) commentTotal / pageSize);

	        int likeTotal = myPageService.countLikesByUser(userId);
	        int likeTotalPages = (int) Math.ceil((double) likeTotal / pageSize);
	        
	        int bookMarkTotal = myPageService.countBookMarksByUser(userId);
	        int bookMarkTotalPages = (int) Math.ceil((double) bookMarkTotal / pageSize);
	        
	        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
	        
	        List<MyPageContentDTO> myPosts = myPageService.getMyPosts(userId, postSort, offset, pageSize);
	        
	        List<MyPageContentDTO> commentedPosts = myPageService.getPostsCommentedByUser(userId, commentSort, commentOffset, pageSize);
	        
	        List<MyPageContentDTO> likedPosts = myPageService.getPostsLikedByUser(userId, likeSort, likeOffset, pageSize);
	        
	        List<MyPageContentDTO> bookMarkedPosts = myPageService.getPostsBookMarkedByUser(userId, bookMarkSort, bookMarkOffset, pageSize);
	        
	        List<ReceiveMessageDTO> receivedMessages = myPageService.getReceivedMessages(userId);
	        
	        
	        for (MyPageContentDTO post : myPosts) {
	            if (post.getCreatedAt() != null) {
	                post.setFormattedDate(post.getCreatedAt().toLocalDate().format(dateFormat));
	                post.setFormattedTime(post.getCreatedAt().toLocalTime().format(timeFormat));
	            }
	        }
	        
	        for (MyPageContentDTO post : commentedPosts) {
	            if (post.getCreatedAt() != null) {
	                post.setFormattedDate(post.getCreatedAt().toLocalDate().format(dateFormat));
	                post.setFormattedTime(post.getCreatedAt().toLocalTime().format(timeFormat));
	            }
	        }
	        
	        for (MyPageContentDTO post : likedPosts) {
	            if (post.getCreatedAt() != null) {
	                post.setFormattedDate(post.getCreatedAt().toLocalDate().format(dateFormat));
	                post.setFormattedTime(post.getCreatedAt().toLocalTime().format(timeFormat));
	            }
	        }
	        
	        for (MyPageContentDTO post : bookMarkedPosts) {
	            if (post.getCreatedAt() != null) {
	                post.setFormattedDate(post.getCreatedAt().toLocalDate().format(dateFormat));
	                post.setFormattedTime(post.getCreatedAt().toLocalTime().format(timeFormat));
	            }
	        }
	        
	        
	        MyPageDTO2 myInfo = myPageService.getMyPageInfo(userId);
	        
	        
	        model.addAttribute("myInfo", myInfo);
	        model.addAttribute("myPosts", myPosts);
	        model.addAttribute("commentedPosts", commentedPosts);
	        model.addAttribute("likedPosts", likedPosts);
	        model.addAttribute("bookMarkedPosts", bookMarkedPosts);
	        
	        model.addAttribute("postSort", postSort);
	        model.addAttribute("commentSort", commentSort);
	        model.addAttribute("likeSort", likeSort);
	        model.addAttribute("bookMarkSort", bookMarkSort);
	        
	        model.addAttribute("currentPage", page);
	        model.addAttribute("commentCurrentPage", commentPage);
	        model.addAttribute("likeCurrentPage", likePage);
	        model.addAttribute("bookMarkCurrentPage", bookMarkPage);
	        
	        model.addAttribute("totalPages", totalPages);
	        model.addAttribute("commentTotalPages", commentTotalPages);
	        model.addAttribute("likeTotalPages", likeTotalPages);
	        model.addAttribute("bookMarkTotalPages", bookMarkTotalPages);
	        
	        model.addAttribute("receivedMessages", receivedMessages);
	        
	        return "mypage2";
	    }
	    
	    @PostMapping("/sendMessage2")
	    public String sendMessage(
	    		@RequestParam("receiverId") int receiverId,
	            @RequestParam("content") String content
	                              ){

	        UserVO loginUser = getLoginUser();
	        if (loginUser == null || receiverId <= 0 || content == null || content.trim().isEmpty()) {
	            return "redirect:/mypage?error=invalidInput";
	        }
	        int senderId = loginUser.getId();

	        // 메시지 객체 생성
	        ReceiveMessageDTO message = new ReceiveMessageDTO();
	        message.setSenderId(senderId);
	        message.setReceiverId(receiverId);
	        message.setContent(content);

	        myPageService.sendMessage(message);

	        // 마이페이지로 리다이렉트 (메시지 섹션 보여주기)
	        return "redirect:/mypage2?section=section-messages";
	    }
	    
	    @PostMapping("/markMessageAsRead")
	    @ResponseBody
	    public ResponseEntity<?> markAsRead(@RequestParam("id") int messageId) {
	        try {
	            myPageService.markAsRead(messageId);
	            return ResponseEntity.ok().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("失敗しました");
	        }
	    }
	    
}
