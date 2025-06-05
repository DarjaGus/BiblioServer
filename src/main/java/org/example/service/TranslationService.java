package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    // Пример метода для перевода текста (в реальном приложении можно использовать API перевода)
    public String translate(String text, String targetLanguage) {
        // Здесь можно использовать API перевода, например, Google Translate API
        // Для примера просто возвращаем текст без изменений
        return text;
    }
}
