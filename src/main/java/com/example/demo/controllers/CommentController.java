package com.example.demo.controllers;

import com.example.demo.dto.CommentDTO;
import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.MyUser;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.CommentServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/comments")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentServiceImpl commentService;

    private final PostService postService;

    private final UserService userService;

    @GetMapping("/newComment/{postId}")
    public String newComment(@PathVariable Long postId, Model model) {
        model.addAttribute("commentDTO", new CommentDTO());
        model.addAttribute("postId", postId);
        return "comments/new-comment";
    }

    @PostMapping("/{postId}")
    public String addComment(@PathVariable Long postId, @Valid @ModelAttribute("commentDTO") CommentDTO commentDTO, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            logger.error("Validation errors for comment: {}", bindingResult.getAllErrors());
            return "comments/new-comment";
        }

        Post post = postService.getPostById(postId).orElseThrow(() -> {
            logger.error("Post with ID {} not found", postId);
            return new RuntimeException("Post not found");
        });

        MyUser author = userService.loadUserByUserName(principal.getName()).orElseThrow(() -> {
            logger.error("User {} not found", principal.getName());
            return new RuntimeException("User not found");
        });

        Comment comment = commentService.createComment(commentDTO, post, author);
        commentService.save(comment);
        logger.info("Comment successfully added for post ID {} by user {}", postId, principal.getName());

        return "redirect:/posts/" + postId;


    }
}