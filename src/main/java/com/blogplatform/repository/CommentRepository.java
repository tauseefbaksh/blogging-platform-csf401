package com.blogplatform.repository;

import com.blogplatform.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Custom Query 2: Comments on a specific post
    List<Comment> findByPostId(Long postId);

    // Additional useful query
    List<Comment> findByAuthorName(String authorName);
}