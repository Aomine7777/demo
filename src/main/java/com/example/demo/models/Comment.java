package com.example.demo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDateTime createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private MyUser author;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    public Comment(long id, String content, LocalDateTime createdDate, MyUser author, Post post) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.author = author;
        this.post = post;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}


