    package com.example.demo.services;

    import com.example.demo.dto.CommentDTO;
    import com.example.demo.models.Comment;
    import com.example.demo.models.MyUser;
    import com.example.demo.models.Post;
    import com.example.demo.repositories.CommentRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;

    @Service
    public class CommentService {
        @Autowired
        private CommentRepository commentRepository;

        public void save(Comment comment) {
            commentRepository.save(comment);
        }

        public Comment createComment(CommentDTO commentDTO, Post post, MyUser author) {
            Comment comment = new Comment();
            comment.setContent(commentDTO.getContent());
            comment.setPost(post);
            comment.setAuthor(author);
            comment.setCreatedDate(LocalDateTime.now());
            return comment;
        }
    }
