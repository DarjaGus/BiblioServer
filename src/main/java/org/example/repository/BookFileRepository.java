package org.example.repository;

import org.example.model.BookFile;
import org.example.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookFileRepository extends JpaRepository<BookFile, Integer> {
    List<BookFile> findByBookFileNameContainingIgnoreCase(String bookFileName);
}
