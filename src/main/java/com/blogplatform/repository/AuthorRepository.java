package com.blogplatform.repository;

import com.blogplatform.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Derived query - Spring generates implementation automatically
    Optional<Author> findByEmail(String email);

    // Custom Query 9: Authors with most posts
    @Query("SELECT a FROM Author a LEFT JOIN a.posts p GROUP BY a ORDER BY COUNT(p) DESC")
    List<Author> findAuthorsWithMostPosts();
}