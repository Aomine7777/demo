package com.example.demo.services.impl;

import com.example.demo.dto.CommentDTO;
import com.example.demo.models.Comment;
import com.example.demo.models.MyUser;
import com.example.demo.models.Post;
import com.example.demo.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@AllArgsConstructor
@Service
@Slf4j
public class CommentServiceImpl {
    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    public Comment createComment(CommentDTO commentDTO, Post post, MyUser author) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setPost(post);
        comment.setAuthor(author);
        comment.setCreatedDate(LocalDateTime.now());
        return save(comment);
    }
}