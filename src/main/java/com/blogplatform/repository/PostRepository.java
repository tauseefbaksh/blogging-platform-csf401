package com.blogplatform.repository;

import com.blogplatform.entity.Post;
import com.blogplatform.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Existing queries...
    List<Post> findByTagsName(String tagName);
    List<Post> findByAuthorId(Long authorId);

    @Query("SELECT p FROM Post p LEFT JOIN p.comments c GROUP BY p ORDER BY COUNT(c) DESC")
    List<Post> findMostCommentedPosts();

    List<Post> findByPublishedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT DISTINCT t FROM Tag t JOIN t.posts p WHERE p.author.id = :authorId")
    List<Object> findTagsByAuthorId(@Param("authorId") Long authorId);

    List<Post> findTop10ByOrderByPublishedDateDesc();
    List<Post> findByTitleContaining(String keyword);

    @Query("SELECT p FROM Post p WHERE p.tags IS EMPTY")
    List<Post> findUntaggedPosts();

    // NEW QUERY - Using ENUM
    List<Post> findByStatus(PostStatus status);

    // NEW QUERY - Count posts by status
    @Query("SELECT COUNT(p) FROM Post p WHERE p.status = :status")
    Long countByStatus(@Param("status") PostStatus status);
}