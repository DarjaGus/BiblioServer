package org.example.api;

import org.example.model.Language;
import org.example.service.LanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {

    @Autowired
    private LanguagesService languagesService;

    @GetMapping
    public List<Language> getAllLanguages() {
        return languagesService.findAllLanguages();
    }

    @GetMapping("/{id}")
    public Language getLanguageById(@PathVariable Long id) {
        return languagesService.findLanguageById(id);
    }

    @PostMapping
    public Language createLanguage(@RequestBody Language language) {
        return languagesService.saveLanguage(language);
    }

    @PutMapping("/{id}")
    public Language updateLanguage(@PathVariable Long id, @RequestBody Language language) {
        language.setId(id);
        return languagesService.saveLanguage(language);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable Long id) {
        languagesService.deleteLanguage(id);
    }
}
