package com.example.exam.service.impl;

import com.example.exam.model.entity.Language;
import com.example.exam.model.entity.enums.LanguageName;
import com.example.exam.repository.LanguageRepository;
import com.example.exam.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void initLanguages() {
        if(languageRepository.count() == 0){
            Language German = new Language(LanguageName.GERMAN, "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.");
            Language French = new Language(LanguageName.FRENCH, "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.");
            Language Spanish = new Language(LanguageName.SPANISH, "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.");
            Language Italian = new Language(LanguageName.ITALIAN, "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.");
            languageRepository.save(German);
            languageRepository.save(French);
            languageRepository.save(Spanish);
            languageRepository.save(Italian);
        }
    }

    @Override
    public Language findByName(LanguageName languageName) {
        return languageRepository.findByLanguageName(languageName).orElse(null);
    }
}
