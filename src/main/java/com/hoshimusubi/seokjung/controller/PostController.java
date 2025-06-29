package com.hoshimusubi.seokjung.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoshimusubi.seokjung.dto.PostDTO;
import com.hoshimusubi.seokjung.service.PostServiceImplYSJ;
import com.hoshimusubi.seunga.model.UserVO;
import com.hoshimusubi.seunga.security.CustomPrincipal;
import com.hoshimusubi.seunga.security.CustomUserDetails;

@Controller

public class PostController {
	
		@Autowired
		private PostServiceImplYSJ postService;
		
	    private UserVO getLoginUser() {
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
	        return  principal.getUser();
	    }
		
		
		@GetMapping("/zodiac/{zodiacId}")
		public String postsByZodiac(
				@PathVariable("zodiacId") Integer zodiacId,
				@RequestParam(name = "sort", defaultValue = "recent") String sort,
				@RequestParam(name = "page", defaultValue = "1") int page,
				Model model,
				HttpSession session
				) {
			
			int pageSize = 6;
			int offset = (page - 1) * pageSize;
			
			
			List<PostDTO> posts = postService.getPostsByZodiacIdSorted(zodiacId, sort, offset, pageSize);
			
			int totalCount = postService.countPostsByZodiacId(zodiacId);
			
			int totalPages = (int) Math.ceil((double) totalCount / pageSize);
			
			List<PostDTO> zodiacs = postService.getAllZodiac();
			
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
			
			String today = LocalDate.now().format(dateFormat);
			
			for (PostDTO post : posts) {
			    String postDate = post.getCreatedAt().toLocalDate().format(dateFormat);
			    String postTime = post.getCreatedAt().toLocalTime().format(timeFormat);
			    post.setFormattedDate(postDate); 
			    post.setFormattedTime(postTime); 
			}
			
			UserVO loginUser = getLoginUser();
			
			if (loginUser != null) {
				model.addAttribute("myZodiacId", loginUser.getZodiacId());
			}
			
			
			model.addAttribute("today", today);
			model.addAttribute("posts", posts);
			model.addAttribute("zodiacs", zodiacs);
			model.addAttribute("selectedZodiacId", zodiacId);
			model.addAttribute("currentSort", sort);
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPages", totalPages);
			return "post_seokjung";
		}
		

}
