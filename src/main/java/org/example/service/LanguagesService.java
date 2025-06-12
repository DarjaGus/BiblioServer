package org.example.service;

import org.example.model.Genre;
import org.example.model.Language;
import org.example.repository.LanguageRepository;
import org.example.config.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguagesService {

    private static final Logger logger = LoggerFactory.getLogger(LanguagesService.class);
    private final LanguageRepository languageRepository;

    @Autowired
    public  LanguagesService(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }
    public List<Language> getAllLanguages() {
        logger.info("Fetching all languages");
        return languageRepository.findAll();
    }

    public Language getLanguageById(Integer id) {
        logger.info("Fetching language by id: {}", id);
        return languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found with id: " + id));
    }

    @Transactional
    public Language saveLanguage(Language language) {
        logger.info("Saving language: {}", language.getLanguageName());
        return languageRepository.save(language);
    }

    @Transactional
    public Language updateLanguage(Integer id, Language languageDetails) {
        logger.info("Updating language with id: {}", id);
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found with id: " + id));

        language.setLanguageName(languageDetails.getLanguageName());

        return languageRepository.save(language);
    }

    public void deleteLanguage(Integer id) {
        logger.info("Deleting language with id: {}", id);
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found with id: " + id));

        languageRepository.delete(language);
    }

    public List<Language> findByLanguageName(String languageName) {
        logger.info("Searching languages by name: {}", languageName);
        return languageRepository.findByLanguageNameContainingIgnoreCase(languageName);
    }
}
