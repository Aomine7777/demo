package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Post> latestPosts = postService.getAllPosts();
        model.addAttribute("posts", latestPosts);
        return "home";
    }
}