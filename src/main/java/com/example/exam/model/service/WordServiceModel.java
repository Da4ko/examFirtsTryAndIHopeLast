package com.example.exam.model.service;

import com.example.exam.model.entity.Language;
import com.example.exam.model.entity.User;
import com.example.exam.model.entity.enums.LanguageName;

import java.time.LocalDate;

public class WordServiceModel {
    private String id;
    private String term;
    private String translation;
    private String example;
    private LocalDate inputDate;
    private LanguageName language;
    private User addedBy;

    public WordServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageName getLanguage() {
        return language;
    }

    public void setLanguage(LanguageName language) {
        this.language = language;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
