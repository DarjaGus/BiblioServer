package org.example.service;

import org.example.config.ResourceNotFoundException;
import org.example.model.Author;
import org.example.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        logger.info("Fetching all authors");
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer id) {
        logger.info("Fetching author by id: {}", id);
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    @Transactional
    public Author saveAuthor(Author author) {
        logger.info("Saving author: {}", author.getAuthorName());
        return authorRepository.save(author);
    }

    @Transactional
    public Author updateAuthor(Integer id, Author authorDetails) {
        logger.info("Updating author with id: {}", id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));

        author.setAuthorName(authorDetails.getAuthorName());
        author.setBiography(authorDetails.getBiography());

        return authorRepository.save(author);
    }

    public void deleteAuthor(Integer id) {
        logger.info("Deleting author with id: {}", id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));

        authorRepository.delete(author);
    }

    public List<Author> findByAuthorName(String authorName) {
        logger.info("Searching authors by name: {}", authorName);
        return authorRepository.findByAuthorNameContainingIgnoreCase(authorName);
    }
}