package com.example.demo.controllers;

import com.example.demo.dto.PostDTO;
import com.example.demo.models.Post;
import com.example.demo.models.Tag;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.TagService;
import com.example.demo.services.impl.PostServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import com.example.demo.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    private final TagService tagService;

    private final PostRepository postRepository;
    private final PostServiceImpl postServiceImpl;

    @GetMapping
    public String listPosts(Model model, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "tag", required = false) String tag) {
        List<Post> posts;

        if (search != null && !search.isEmpty()) {
            posts = postService.searchPostsByTitle(search);
        } else if (tag != null && !tag.isEmpty()) {
            posts = postService.getPostsByTag(tag);
        } else {
            posts = postService.getAllPosts();
        }

        List<PostDTO> postsCollect = posts.stream().map(post -> new PostDTO(post.getId(), post.getTitle(), post.getAuthor(), post.getContent(), post.getTags().stream().map(Tag::getName).collect(Collectors.joining(", ")))).collect(Collectors.toList());

        model.addAttribute("posts", postsCollect);
        return "posts/comment-list";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            PostDTO postDTO = postServiceImpl.toDTO(post.get());
            model.addAttribute("post", postDTO);
            return "posts/detail";
        } else {
            return "redirect:/posts";
        }
    }

    @GetMapping("/new")
    public String newPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/form";
    }

    @PostMapping("/{id}/edit")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostDTO post) {
        postService.updatePost(id, post);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/{id}/editForm")
    public String openEditForm(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "posts/edit";
        } else {
            return "redirect:/posts";
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

    @GetMapping("/search")
    public String searchPosts(@RequestParam("query") String query, Model model) {
        Set<PostDTO> posts = postService.searchPosts(query);
        model.addAttribute("posts", new ArrayList<>(posts));
        return "posts/comment-list";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDTO postDetails, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/form";
        }

        Post post = postServiceImpl.fromDTO(postDetails);

        postRepository.save(post);

        return "redirect:/posts";
    }
}