package com.example.exam.service.impl;

import com.example.exam.model.entity.Word;
import com.example.exam.model.entity.enums.LanguageName;
import com.example.exam.model.service.UserServiceModel;
import com.example.exam.model.service.WordServiceModel;
import com.example.exam.model.view.WordViewModel;
import com.example.exam.repository.UserRepository;
import com.example.exam.repository.WordRepository;
import com.example.exam.service.LanguageService;
import com.example.exam.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    private final ModelMapper modelMapper;
    private final WordRepository wordRepository;
    private final LanguageService languageService;
    private final UserRepository userRepository;

    public WordServiceImpl(ModelMapper modelMapper, WordRepository wordRepository, LanguageService languageService, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.wordRepository = wordRepository;
        this.languageService = languageService;
        this.userRepository = userRepository;
    }

    @Override
    public void add(WordServiceModel wordServiceModel, UserServiceModel userServiceModel) {
        Word word = new Word();

        word.setId(wordServiceModel.getId());
        word.setExample(wordServiceModel.getExample());
        word.setTerm(wordServiceModel.getTerm());
        word.setTranslation(wordServiceModel.getTranslation());
        word.setAddedBy(userRepository.findByUsernameAndPassword(userServiceModel.getUsername(), userServiceModel.getPassword()).orElse(null));
        word.setInputDate(wordServiceModel.getInputDate());
        word.setLanguage(languageService.findByName(wordServiceModel.getLanguage()));
        wordRepository.save(word);
    }

    @Override
    public List<WordViewModel> findByLanguageName(LanguageName languageName) {
        List<WordViewModel> wordViewModels = new ArrayList<>();
        List<Word> words = wordRepository.findAllByLanguage_LanguageName(languageName);

        for (Word word : words){
            WordViewModel wordViewModel = new WordViewModel();

            wordViewModel.setId(word.getId());
            wordViewModel.setExample(word.getExample());
            wordViewModel.setTerm(word.getTerm());
            wordViewModel.setTranslation(word.getTranslation());
            wordViewModel.setUser(word.getAddedBy().getUsername());
            wordViewModel.setInputDate(word.getInputDate().toString());

            wordViewModels.add(wordViewModel);
        }



        return wordViewModels;
    }

    @Override
    public String countAll() {
        return Long.toString(wordRepository.count());
    }

    @Override
    public String countGermanWords(LanguageName languageName) {
        List<Word> allByLanguageLanguageName = wordRepository.findAllByLanguage_LanguageName(languageName);
        return Integer.toString(allByLanguageLanguageName.size());
    }

    @Override
    public String countItalianWords(LanguageName languageName) {
        List<Word> allByLanguageLanguageName = wordRepository.findAllByLanguage_LanguageName(languageName);
        return Integer.toString(allByLanguageLanguageName.size());
    }

    @Override
    public String countFrenchWords(LanguageName languageName) {
        List<Word> allByLanguageLanguageName = wordRepository.findAllByLanguage_LanguageName(languageName);
        return Integer.toString(allByLanguageLanguageName.size());
    }

    @Override
    public String countSpanishWords(LanguageName languageName) {
        List<Word> allByLanguageLanguageName = wordRepository.findAllByLanguage_LanguageName(languageName);
        return Integer.toString(allByLanguageLanguageName.size());
    }

    @Override
    public void removeById(String id) {
        wordRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        wordRepository.deleteAll();
    }
}
