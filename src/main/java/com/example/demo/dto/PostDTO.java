package com.example.demo.dto;

import com.example.demo.models.Comment;
import lombok.Builder;

import java.util.Set;

@Builder
public class PostDTO {
    private long id;
    private String title;
    private String author;
    private String content;
    private String tags;
    private String createDate;
    private String lastUpdateDate;
    private Set<Comment> comments;

    public PostDTO(long id, String title, String author, String content, String tags, String createDate, String lastUpdateDate, Set <Comment> comment) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.tags = tags;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.comments = comment;
    }

    public PostDTO(long id, String title, String author, String content, String tags) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.tags = tags;
    }

    public PostDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
