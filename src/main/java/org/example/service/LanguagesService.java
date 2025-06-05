package org.example.service;

import org.example.model.Language;
import org.example.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguagesService {
    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> findAllLanguages() {
        return languageRepository.findAll();
    }

    public Language findLanguageById(Long id) {
        return languageRepository.findById(id).orElse(null);
    }

    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }
}
