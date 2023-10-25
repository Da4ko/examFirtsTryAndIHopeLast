package com.example.exam.init;

import com.example.exam.service.LanguageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInitializer implements CommandLineRunner {
    private final LanguageService languageService;

    public DataBaseInitializer(LanguageService languageService) {
        this.languageService = languageService;
    }


    @Override
    public void run(String...args) throws Exception{
       languageService.initLanguages();
    }
}