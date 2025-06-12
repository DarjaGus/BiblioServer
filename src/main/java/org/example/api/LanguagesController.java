package org.example.api;

import org.example.model.Genre;
import org.example.model.Language;
import org.example.service.GenreService;
import org.example.service.LanguagesService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {

    private static final Logger logger = LoggerFactory.getLogger(LanguagesController.class);

    private final LanguagesService languagesService;

    @Autowired
    public LanguagesController(LanguagesService languagesService) {
        this.languagesService = languagesService;
    }

    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguades() {
        logger.info("GET request to fetch all languages");
        List<Language> languages = languagesService.getAllLanguages();
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Integer id) {
        logger.info("GET request to fetch language with id: {}", id);
        Language language = languagesService.getLanguageById(id);
        return new ResponseEntity<>(language, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Language> createLanguage(@Valid @RequestBody Language language) {
        logger.info("POST request to create language: {}", language.getLanguageName());
        Language savedLanguage = languagesService.saveLanguage(language);
        return new ResponseEntity<>(savedLanguage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable Integer id, @Valid @RequestBody Language languageDetails) {
        logger.info("PUT request to update language with id: {}", id);
        Language updatedLanguage = languagesService.updateLanguage(id, languageDetails);
        return new ResponseEntity<>(updatedLanguage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Integer id) {
        logger.info("DELETE request to delete language with id: {}", id);
        languagesService.deleteLanguage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Language>> searchGenres(@RequestParam String name) {
        logger.info("GET request to search genres by name: {}", name);
        List<Language> languages = languagesService.findByLanguageName(name);
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }
}