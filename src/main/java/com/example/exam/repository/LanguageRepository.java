package com.example.exam.repository;

import com.example.exam.model.entity.Language;
import com.example.exam.model.entity.enums.LanguageName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {
    Optional<Language> findByLanguageName(LanguageName name);
}
