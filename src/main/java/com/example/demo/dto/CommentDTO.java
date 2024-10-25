package com.example.demo.dto;

public class CommentDTO {
    private long id;
    private String content;
    private String createdDate;
    private MyUserDTO author;
    private PostDTO post;

    public CommentDTO(long id, String content, String createdDate, MyUserDTO author, PostDTO post) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.author = author;
        this.post = post;
    }

    public CommentDTO() {
    }

    public CommentDTO(long id, String content, String createdDate, MyUserDTO author) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.author = author;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public MyUserDTO getAuthor() {
        return author;
    }

    public void setAuthor(MyUserDTO author) {
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