package com.blogplatform.service;

import com.blogplatform.entity.Post;
import com.blogplatform.entity.PostStatus;
import com.blogplatform.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // ... existing methods ...

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public Post createPost(Post post) {
        post.setPublishedDate(LocalDateTime.now());
        // Set default status if not provided
        if (post.getStatus() == null) {
            post.setStatus(PostStatus.DRAFT);
        }
        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));

        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        if (postDetails.getStatus() != null) {
            post.setStatus(postDetails.getStatus());
        }

        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    // Existing custom query methods
    public List<Post> getPostsByTag(String tagName) {
        return postRepository.findByTagsName(tagName);
    }

    public List<Post> getPostsByAuthor(Long authorId) {
        return postRepository.findByAuthorId(authorId);
    }

    public List<Post> getMostCommentedPosts() {
        return postRepository.findMostCommentedPosts();
    }

    public List<Post> getPostsInDateRange(LocalDateTime start, LocalDateTime end) {
        return postRepository.findByPublishedDateBetween(start, end);
    }

    public List<Post> getRecentPosts() {
        return postRepository.findTop10ByOrderByPublishedDateDesc();
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    public List<Post> getUntaggedPosts() {
        return postRepository.findUntaggedPosts();
    }

    // NEW METHODS - Using ENUM
    public List<Post> getPostsByStatus(PostStatus status) {
        return postRepository.findByStatus(status);
    }

    public Long countPostsByStatus(PostStatus status) {
        return postRepository.countByStatus(status);
    }

    @Transactional
    public Post publishPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        post.setStatus(PostStatus.PUBLISHED);
        post.setPublishedDate(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Transactional
    public Post archivePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        post.setStatus(PostStatus.ARCHIVED);
        return postRepository.save(post);
    }
}