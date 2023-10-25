package com.example.exam.model.binding;

import com.example.exam.model.entity.Language;
import com.example.exam.model.entity.User;
import com.example.exam.model.entity.enums.LanguageName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class WordAddBindingModel {
    private String term;
    private String translation;
    private String example;
    private LocalDate inputDate;
    private LanguageName language;

    public WordAddBindingModel() {
    }
    @NotBlank(message = "Term cannot be null")
    @Size(min = 2, max=40, message = "The term length must be between 2 and 40 characters")
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    @NotBlank(message = "Translation cannot be null")
    @Size(min = 2, max=80, message = "The translation length must be between 2 and 80 characters")
    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Size(min = 2, max=200, message = "The translation length must be between 2 and 200 characters")
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
    @PastOrPresent(message = "The input date must be in the past or present")
    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }
    @NotNull(message = "You must select a language")
    public LanguageName getLanguage() {
        return language;
    }

    public void setLanguage(LanguageName language) {
        this.language = language;
    }
}
