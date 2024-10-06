package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Entity
@Builder
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String email;
    @Size(min = 4, max = 100)
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comment;

    public MyUser(long id, String userName, String email, String password, String role, List<Comment> comment) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.comment = comment;
    }

    public MyUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @Size(min = 4, max = 100) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 4, max = 100) String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
}
