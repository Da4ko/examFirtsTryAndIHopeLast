package com.example.exam.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "words")
public class Word extends BaseEntity{
    private String term;
    private String translation;
    private String example;
    private LocalDate inputDate;
    private Language language;
    private User addedBy;

    public Word() {
    }

    @Column(name = "term", nullable = false)
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    @Column(name = "translation", nullable = false)
    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
    @Column(name = "example")
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
    @Column(name = "input-date", nullable = false)
    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }
    @ManyToOne
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
    @ManyToOne
    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
