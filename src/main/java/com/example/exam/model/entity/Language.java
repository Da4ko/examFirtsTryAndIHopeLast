package com.example.exam.model.entity;

import com.example.exam.model.entity.enums.LanguageName;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity{
    private LanguageName languageName;
    private String description;
    private List<Word> words;

    public Language() {
    }

    public Language(LanguageName languageName, String description) {
        this.languageName = languageName;
        this.description = description;
    }
    @Column(name = "language-name", nullable = false)
    @Enumerated(EnumType.STRING)
    public LanguageName getLanguageName() {
        return languageName;
    }

    public void setLanguageName(LanguageName languageName) {
        this.languageName = languageName;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @OneToMany(fetch = FetchType.EAGER)
    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
