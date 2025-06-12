package org.example.service;

import org.example.model.Genre;
import org.example.repository.GenreRepository;
import org.example.config.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        logger.info("Fetching all genres");
        return genreRepository.findAll();
    }

    public Genre getGenreById(Integer id) {
        logger.info("Fetching genre by id: {}", id);
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));
    }

    @Transactional
    public Genre saveGenre(Genre genre) {
        logger.info("Saving genre: {}", genre.getGenreName());
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre updateGenre(Integer id, Genre genreDetails) {
        logger.info("Updating genre with id: {}", id);
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));

        genre.setGenreName(genreDetails.getGenreName());

        return genreRepository.save(genre);
    }

    public void deleteGenre(Integer id) {
        logger.info("Deleting genre with id: {}", id);
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));

        genreRepository.delete(genre);
    }

    public List<Genre> findByGenreName(String genreName) {
        logger.info("Searching genres by name: {}", genreName);
        return genreRepository.findByGenreNameContainingIgnoreCase(genreName);
    }
}