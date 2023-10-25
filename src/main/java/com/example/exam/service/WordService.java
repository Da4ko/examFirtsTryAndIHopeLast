package com.example.exam.service;

import com.example.exam.model.entity.enums.LanguageName;
import com.example.exam.model.service.UserServiceModel;
import com.example.exam.model.service.WordServiceModel;
import com.example.exam.model.view.WordViewModel;

import java.util.List;

public interface WordService {
    void add(WordServiceModel wordServiceModel, UserServiceModel userServiceModel);

    List<WordViewModel> findByLanguageName(LanguageName languageName);

    String countAll();

    String countGermanWords(LanguageName languageName);

    String countItalianWords(LanguageName languageName);

    String countFrenchWords(LanguageName languageName);

    String countSpanishWords(LanguageName languageName);

    void removeById(String id);

    void removeAll();
}
