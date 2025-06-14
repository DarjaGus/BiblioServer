package org.example.repository;

import org.example.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findByAuthorNameContainingIgnoreCase(String authorName);
}
