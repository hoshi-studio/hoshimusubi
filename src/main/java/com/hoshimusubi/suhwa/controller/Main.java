package com.hoshimusubi.suhwa.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.hoshimusubi.suhwa.dto.UsersDTO;
import com.hoshimusubi.suhwa.service.BookMarkServiceImpl;
import com.hoshimusubi.suhwa.service.CommentsServiceImpl;
import com.hoshimusubi.suhwa.service.LikeServiceImpl;
import com.hoshimusubi.suhwa.service.PostServiceImpl;
import com.hoshimusubi.suhwa.service.PostsService;

@Controller
public class Main {

	@Autowired
    private PostServiceImpl postsService;
	@Autowired
    private CommentsServiceImpl commentssService;
	@Autowired
    private LikeServiceImpl likeService;
	@Autowired
    private BookMarkServiceImpl bookMarkeService;
	
	/*
	 * @GetMapping("/") public String home() {
	 * System.out.println(">>> home controller called"); return "home"; // →
	 * /WEB-INF/views/Home.jsp 를 의미함 }
	 */
    @GetMapping("/post_detail")
    public String postDetail(@RequestParam("id") Long id, Model model,HttpServletRequest request) {
    	Long userId = (Long) request.getSession().getAttribute("userId");
    	PostsDTO post = postsService.getPostById(id);
    	boolean liked = likeService.isPostLikedByUser(id, loginUserId);
    	postDTO.setLikedByCurrentUser(liked);

    	boolean bookmarked = bookmarkService.isPostBookmarkedByUser(postId, loginUserId);
    	postDTO.setBookmarkedByCurrentUser(bookmarked);
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
    
    @PostMapping("/post_create")
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
    
 // 게시글 수정 페이지 이동
    @GetMapping("/post_modify")
    public String editPostForm(@RequestParam("id") Long id, Model model, HttpSession session) {
        PostsDTO post = postsService.getPostById(id);

        // 로그인 사용자와 작성자 확인
        UsersDTO loginUser = (UsersDTO) session.getAttribute("loginUser");
        if (loginUser == null || !loginUser.getId().equals(post.getUser_Id())) {
            return "redirect:/"; // 권한 없으면 홈으로
        }

        model.addAttribute("post", post);
        return "post_modify"; // 수정 폼 JSP
    }

    // 게시글 수정 처리
    @PostMapping("/post_update")
    public String updatePost(@ModelAttribute PostsDTO post,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             @RequestParam(value = "removeImage", required = false) String removeImage,
                             HttpServletRequest request,
                             HttpSession session) {

        // 로그인 체크 및 권한 검사
        UsersDTO loginUser = (UsersDTO) session.getAttribute("loginUser");
        if (loginUser == null || !loginUser.getId().equals(post.getUser_Id())) {
            return "redirect:/";
        }

        // 파일 저장 경로 설정
        String uploadDir = request.getServletContext().getRealPath("/resources/upload/");
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) uploadFolder.mkdirs();

        // 기존 이미지 삭제 요청 처리
        if ("true".equals(removeImage)) {
            post.setImageUrl(null);
        }

        // 새 이미지 업로드 처리
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

    // 게시글 삭제
    @GetMapping("/post_delete")
    public String deletePost(@RequestParam("id") Long id, HttpSession session) {
        PostsDTO post = postsService.getPostById(id);
        UsersDTO loginUser = (UsersDTO) session.getAttribute("loginUser");

        if (loginUser == null || !loginUser.getId().equals(post.getUser_Id())) {
            return "redirect:/";
        }

        postsService.deletePost(id);
        return "redirect:/post/list";
    }
    
    //댓글 수정
    @PostMapping("/comment_update")
    @ResponseBody
    public String updateComment(@RequestParam Long id, @RequestParam String content) {
    	commentssService.updateComment(id, content);
        return "success";
    }

  //댓글 삭제
    @PostMapping("/comment_delete")
    @ResponseBody
    public String deleteComment(@RequestParam Long id) {
    	commentssService.deleteComment(id);
        return "success";
    }
    
    @PostMapping("/like")
    @ResponseBody
    public int like(@RequestParam Long postId, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        likeService.insertLike(postId, userId);
        return likeService.getLikeCount(postId);
    }

    @PostMapping("/unlike")
    @ResponseBody
    public int unlike(@RequestParam Long postId, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        likeService.deleteLike(postId, userId);
        return likeService.getLikeCount(postId);
    }

    
    
}
