package com.example.exam.service;

import com.example.exam.model.entity.Language;
import com.example.exam.model.entity.enums.LanguageName;

public interface LanguageService {
    void initLanguages();

    Language findByName(LanguageName languageName);
}
