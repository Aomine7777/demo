package com.example.demo.services;

import com.example.demo.dto.PostDTO;
import com.example.demo.models.Post;
import com.example.demo.models.Tag;
import com.example.demo.repositories.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagService tagService;

    public Post updatePost(long id, PostDTO postDetails) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));

        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setLastUpdateDate(LocalDateTime.now());

        if (postDetails.getTags() != null) {
            Set<String> splitTags = new HashSet<>(Arrays.asList(postDetails.getTags().split(", ")));
            Set<Tag> tags = tagService.findOrCreateByNames(splitTags);

            post.getTags().forEach(tag -> tag.getPosts().remove(post));
            tags.forEach(tag -> tag.getPosts().add(post));
            post.setTags(tags);
        }

        return postRepository.save(post);
    }

    public Optional<Post> getPostById(long id) {
        return postRepository.findById(id);
    }


    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> searchPostsByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Post> getPostsByTag(String tagName) {
        return postRepository.findByTagsNameIgnoreCase(tagName);
    }
}
