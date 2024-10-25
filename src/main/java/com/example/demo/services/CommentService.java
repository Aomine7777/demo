package com.example.demo.services;

import com.example.demo.dto.CommentDTO;
import com.example.demo.models.Comment;
import com.example.demo.models.MyUser;
import com.example.demo.models.Post;

public interface CommentService {

    Comment save(Comment comment);

    Comment createComment(CommentDTO commentDTO, Post post, MyUser author);
}