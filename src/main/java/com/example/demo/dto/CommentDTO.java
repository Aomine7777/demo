package com.example.demo.dto;

import com.example.demo.models.MyUser;
import com.example.demo.models.Post;


public class CommentDTO {
    private long id;
    private String content;
    private String createdDate;
    private MyUser author;
    private Post post;

    public CommentDTO(long id, String content, String createdDate, MyUser author, Post post) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.author = author;
        this.post = post;
    }

    public CommentDTO() {
    }

    public CommentDTO(long id, String content, String createdDate, MyUser author) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
