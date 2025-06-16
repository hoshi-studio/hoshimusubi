package com.hoshimusubi.seokjung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoshimusubi.seokjung.dto.PostDTO;
import com.hoshimusubi.seokjung.service.PostServiceImplYSJ;

@Controller

public class PostController {
	
		@Autowired
		private PostServiceImplYSJ postService;
		
		@GetMapping("/zodiac/{zodiacId}")
		public String postsByZodiac(
				@PathVariable("zodiacId") Integer zodiacId,
				@RequestParam(name = "sort", defaultValue = "recent") String sort,
				@RequestParam(name = "page", defaultValue = "1") int page,
				Model model) {
			
			int pageSize = 6;
			int offset = (page - 1) * pageSize;
			
			
			List<PostDTO> posts = postService.getPostsByZodiacIdSorted(zodiacId, sort, offset, pageSize);
			
			int totalCount = postService.countPostsByZodiacId(zodiacId);
			
			int totalPages = (int) Math.ceil((double) totalCount / pageSize);
			
			
			List<PostDTO> zodiacs = postService.getAllZodiac();
			
			model.addAttribute("posts", posts);
			model.addAttribute("zodiacs", zodiacs);
			model.addAttribute("selectedZodiacId", zodiacId);
			model.addAttribute("currentSort", sort);
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPages", totalPages);
			return "post_seokjung";
		}
		

}
