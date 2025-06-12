package org.example.repository;

import org.example.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findByGenreNameContainingIgnoreCase(String genreName);
}