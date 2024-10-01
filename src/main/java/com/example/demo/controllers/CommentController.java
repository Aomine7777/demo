package com.example.demo.controllers;

import com.example.demo.dto.CommentDTO;
import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.MyUser;
import com.example.demo.services.CommentService;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("comment-list/new/{postId}")
    public String newComment(@PathVariable Long postId, Model model) {
        model.addAttribute("commentDTO", new CommentDTO());
        model.addAttribute("postId", postId);
        return "comments/new-comment";
    }

    @PostMapping("/{postId}")
    public String addComment(@PathVariable Long postId, @Valid @ModelAttribute("commentDTO") CommentDTO commentDTO, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "comments/new-comment";
        }

        Post post = postService.getPostById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Optional<MyUser> author = userService.loadUserByUserName(principal.getName());
        if (author.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setPost(post);
        comment.setAuthor(author.get());
        comment.setCreatedDate(LocalDateTime.now());
        commentService.save(comment);

        return "redirect:/posts/" + postId;
    }
}

