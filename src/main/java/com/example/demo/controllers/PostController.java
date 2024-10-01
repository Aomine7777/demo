package com.example.demo.controllers;

import com.example.demo.dto.PostDTO;
import com.example.demo.models.Post;
import com.example.demo.models.Tag;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.TagService;
import org.springframework.ui.Model;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String listPosts(Model model,
                            @RequestParam(value = "search", required = false) String search,
                            @RequestParam(value = "tag", required = false) String tag) {
        List<Post> posts;

        if (search != null && !search.isEmpty()) {
            posts = postService.searchPostsByTitle(search);
        } else if (tag != null && !tag.isEmpty()) {
            posts = postService.getPostsByTag(tag);
        } else {
            posts = postService.getAllPosts();
        }

        List<PostDTO> postsCollect = posts.stream().map(post -> new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getAuthor(),
                post.getContent(),
                post.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.joining(", "))
        )).collect(Collectors.toList());

        model.addAttribute("posts", postsCollect);
        return "posts/comment-list";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            PostDTO postDTO = new PostDTO(
                    post.get().getId(),
                    post.get().getTitle(),
                    post.get().getAuthor(),
                    post.get().getContent(),
                    post.get().getTags().toString(),
                    post.get().getCreateDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                    post.get().getLastUpdateDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                    post.get().getComments()
            );
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
        List<Post> postsByTitle = postService.searchPostsByTitle(query);
        List<Post> postsByTag = postService.getPostsByTag(query);

        Set<Post> uniquePosts = new HashSet<>(postsByTitle);
        uniquePosts.addAll(postsByTag);

        Set<PostDTO> collect = uniquePosts.stream().map(uniquePost -> new PostDTO(
                uniquePost.getId(),
                uniquePost.getTitle(),
                uniquePost.getAuthor(),
                uniquePost.getContent(),
                uniquePost.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.joining(", ")),
                uniquePost.getCreateDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                uniquePost.getLastUpdateDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                uniquePost.getComments()
        )).collect(Collectors.toSet());

        model.addAttribute("posts", new ArrayList<>(collect));
        return "posts/comment-list";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDTO postDetails, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/form";
        }

        Post post = new Post();
        post.setTitle(postDetails.getTitle());
        post.setAuthor(postDetails.getAuthor());
        post.setContent(postDetails.getContent());
        post.setLastUpdateDate(LocalDateTime.now());
        post.setCreateDate(LocalDateTime.now());
        Set<String> tagNames = new HashSet<>(Arrays.asList(postDetails.getTags().split("\\s*,\\s*")));
        Set<Tag> tags = tagService.findOrCreateByNames(tagNames);

        post.setTags(tags);

        postRepository.save(post);

        return "redirect:/posts";
    }
}
