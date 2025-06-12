package org.example.repository;

import org.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // Пример: Поиск книг по названию (частичное совпадение)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Пример: Поиск книг по автору
    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.authorName LIKE %:authorName%")
    List<Book> findByAuthorName(@Param("authorName") String authorName);

    // Пример: Поиск книг по жанру
    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.genreName LIKE %:genreName%")
    List<Book> findByGenreName(@Param("genreName") String genreName);
}
