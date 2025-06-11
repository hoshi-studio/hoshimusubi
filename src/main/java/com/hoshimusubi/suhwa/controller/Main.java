package com.hoshimusubi.suhwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoshimusubi.suhwa.dto.PostsDTO;
import com.hoshimusubi.suhwa.service.PostServiceImpl;
import com.hoshimusubi.suhwa.service.PostsService;

@Controller
public class Main {

	@Autowired
    private PostServiceImpl postsService;
	
    @GetMapping("/")
    public String home() {
    	System.out.println(">>> home controller called");
        return "home"; // → /WEB-INF/views/Home.jsp 를 의미함
    }
    @GetMapping("/post_detail")
    public String postDetail(@RequestParam("id") Long id, Model model) {
		/* System.out.println(">>> postDetail method called"); */
    	PostsDTO post = postsService.getPostById(id);
    	System.out.println(post);
        model.addAttribute("post", post); // JSP로 전달
        return "post_detail_suhwa";
    }
    
    
}
