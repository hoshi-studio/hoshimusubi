package com.hoshimusubi.suhwa.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hoshimusubi.suhwa.dto.CommentsDTO;
import com.hoshimusubi.suhwa.dto.PostsDTO;
import com.hoshimusubi.suhwa.service.CommentsServiceImpl;
import com.hoshimusubi.suhwa.service.PostServiceImpl;
import com.hoshimusubi.suhwa.service.PostsService;

@Controller
public class Main {

	@Autowired
    private PostServiceImpl postsService;
	@Autowired
    private CommentsServiceImpl commentssService;
	
	/*
	 * @GetMapping("/") public String home() {
	 * System.out.println(">>> home controller called"); return "home"; // →
	 * /WEB-INF/views/Home.jsp 를 의미함 }
	 */
    @GetMapping("/post_detail")
    public String postDetail(@RequestParam("id") Long id, Model model) {
		/* System.out.println(">>> postDetail method called"); */
    	PostsDTO post = postsService.getPostById(id);
    	List<CommentsDTO> comments = commentssService.getCommentById(id);
    	System.out.println(comments);
    	model.addAttribute("comments", comments);
        model.addAttribute("post", post); // JSP로 전달
        return "post_detail_suhwa";
    }
    
    @GetMapping("/post_write")
    public String postWrite() {
		/* System.out.println(">>> postDetail method called"); */
        return "post_write_suhwa";
    }
    
    @PostMapping("/post/create")
    public String createPost(@ModelAttribute PostsDTO post,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             HttpServletRequest request) {
    	
	
		
		 Long userId = (Long) request.getSession().getAttribute("userId");
		 post.setUser_Id(userId);
		 
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
    
    @PostMapping(value = "/addComment", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String addComment(@ModelAttribute CommentsDTO comment,
                             HttpServletRequest request) {
		
		 Long userId = (Long) request.getSession().getAttribute("userId");
		 comment.setUserId(userId);
		 
		 commentssService.saveComment(comment);
		 
        return "<div class='comment'><strong>" + comment.getNickname() + "</strong>: " + comment.getContent() + "</div>";
    }
    
    
}
