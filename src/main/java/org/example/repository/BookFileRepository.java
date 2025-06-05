package org.example.repository;

import org.example.model.BookFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFileRepository extends JpaRepository<BookFiles, Long> {
}
