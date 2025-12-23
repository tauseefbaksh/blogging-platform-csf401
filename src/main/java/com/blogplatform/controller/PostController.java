package com.blogplatform.controller;

import com.blogplatform.entity.Post;
import com.blogplatform.entity.PostStatus;
import com.blogplatform.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    // ... existing endpoints ...

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Validated @RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @Validated @RequestBody Post post) {
        try {
            Post updatedPost = postService.updatePost(id, post);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Existing custom query endpoints
    @GetMapping("/tag/{tagName}")
    public ResponseEntity<List<Post>> getPostsByTag(@PathVariable String tagName) {
        return ResponseEntity.ok(postService.getPostsByTag(tagName));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Post>> getPostsByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(postService.getPostsByAuthor(authorId));
    }

    @GetMapping("/most-commented")
    public ResponseEntity<List<Post>> getMostCommentedPosts() {
        return ResponseEntity.ok(postService.getMostCommentedPosts());
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Post>> getPostsInDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(postService.getPostsInDateRange(start, end));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Post>> getRecentPosts() {
        return ResponseEntity.ok(postService.getRecentPosts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPostsByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(postService.searchPostsByKeyword(keyword));
    }

    @GetMapping("/untagged")
    public ResponseEntity<List<Post>> getUntaggedPosts() {
        return ResponseEntity.ok(postService.getUntaggedPosts());
    }

    // NEW ENDPOINTS - Using ENUM
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Post>> getPostsByStatus(@PathVariable PostStatus status) {
        return ResponseEntity.ok(postService.getPostsByStatus(status));
    }

    @GetMapping("/status/{status}/count")
    public ResponseEntity<Long> countPostsByStatus(@PathVariable PostStatus status) {
        return ResponseEntity.ok(postService.countPostsByStatus(status));
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<Post> publishPost(@PathVariable Long id) {
        try {
            Post publishedPost = postService.publishPost(id);
            return ResponseEntity.ok(publishedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Post> archivePost(@PathVariable Long id) {
        try {
            Post archivedPost = postService.archivePost(id);
            return ResponseEntity.ok(archivedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}