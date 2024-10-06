package com.example.demo.repositories;


import com.example.demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingIgnoreCase(String title);

    List<Post> findByTagsNameIgnoreCase(String tagName);

    List<Post> findAll();

    Optional<Post> findById(Long id);
}
