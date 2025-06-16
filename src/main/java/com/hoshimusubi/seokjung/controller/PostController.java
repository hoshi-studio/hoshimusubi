package com.hoshimusubi.seokjung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hoshimusubi.seokjung.dto.PostDTO;
import com.hoshimusubi.seokjung.service.PostService;

@Controller
public class PostController {
	
		@Autowired
		private PostService postService;
		
		@GetMapping("/zodiac/{zodiacId}")
		public String postsByZodiac(@PathVariable("zodiacId") Integer zodiacId, Model model) {
			
			List<PostDTO> posts = postService.getPostsByZodiacId(zodiacId);
			model.addAttribute("posts", posts);
			return "post_seokjung";
		}
		

}
