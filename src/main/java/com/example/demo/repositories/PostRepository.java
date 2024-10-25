package com.example.demo.repositories;


import com.example.demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingIgnoreCase(String title);

    List<Post> findByTagsNameIgnoreCase(String tagName);

    List<Post> findAll();

    Optional<Post> findById(Long id);

    @Query("SELECT DISTINCT p FROM Post p LEFT JOIN p.tags t WHERE p.title LIKE %:query% OR t.name LIKE %:query%")
    List<Post> findDistinctPostsByTitleOrTag(@Param("query") String query);
}