package org.example.repository;

import org.example.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    List<Language> findByLanguageNameContainingIgnoreCase(String languageName);
}
