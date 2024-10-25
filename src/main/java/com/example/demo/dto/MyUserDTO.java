package com.example.demo.dto;

import java.util.List;

public class MyUserDTO {

    private long id;

    private String userName;

    private String email;

    private String password;

    private String role;

    private List<CommentDTO> comment;


    public MyUserDTO(long id, String userName, String email, String password, String role, List<CommentDTO> comment) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.comment = comment;
    }

    public MyUserDTO() {
    }

    public List<CommentDTO> getComment() {
        return comment;
    }

    public void setComment(List<CommentDTO> comment) {
        this.comment = comment;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}