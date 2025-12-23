package com.blogplatform.service;

import com.blogplatform.entity.Author;
import com.blogplatform.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // READ - Get all authors
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // READ - Get author by ID
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    // CREATE - Create new author
    @Transactional
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // UPDATE - Update existing author
    @Transactional
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        author.setName(authorDetails.getName());
        author.setEmail(authorDetails.getEmail());

        return authorRepository.save(author);
    }

    // DELETE - Delete author
    @Transactional
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }

    // CUSTOM QUERY - Get authors with most posts
    public List<Author> getAuthorsWithMostPosts() {
        return authorRepository.findAuthorsWithMostPosts();
    }
}