package com.example.demo.services.impl;

import com.example.demo.dto.PostDTO;
import com.example.demo.models.Post;
import com.example.demo.models.Tag;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.PostService;
import com.example.demo.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final TagService tagService;

    public Post updatePost(long id, PostDTO postDetails) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));

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

    public Set<PostDTO> searchPosts(String query) {
        // Отримуємо пости за заголовком та тегом одночасно з БД
        List<Post> posts = postRepository.findDistinctPostsByTitleOrTag(query);

        // Конвертуємо в DTO
        return posts.stream().map(post -> new PostDTO(post.getId(), post.getTitle(), post.getAuthor(), post.getContent(), post.getTags().stream().map(Tag::getName).collect(Collectors.joining(", ")), post.getCreateDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")), post.getLastUpdateDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")), post.getComments())).collect(Collectors.toSet());
    }

    public Post fromDTO(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setAuthor(postDTO.getAuthor());
        post.setContent(postDTO.getContent());
        post.setLastUpdateDate(LocalDateTime.now());
        post.setCreateDate(LocalDateTime.now());

        Set<String> tagNames = new HashSet<>(Arrays.asList(postDTO.getTags().split("\\s*,\\s*")));
        Set<Tag> tags = tagService.findOrCreateByNames(tagNames);
        post.setTags(tags);

        return post;
    }

    public PostDTO toDTO(Post post) {
        return new PostDTO(post.getId(), post.getTitle(), post.getAuthor(), post.getContent(), post.getTags().toString(), post.getCreateDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")), post.getLastUpdateDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")), post.getComments());
    }
}