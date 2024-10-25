package com.example.demo.services;

import com.example.demo.dto.PostDTO;
import com.example.demo.models.Post;

import java.util.*;


public interface PostService {

    Post updatePost(long id, PostDTO postDetails);

    Optional<Post> getPostById(long id);

    void deletePost(long id);

    List<Post> getAllPosts();

    List<Post> searchPostsByTitle(String title);

    List<Post> getPostsByTag(String tagName);

    Set<PostDTO> searchPosts(String query);
}