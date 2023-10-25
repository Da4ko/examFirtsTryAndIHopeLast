package com.example.exam.web;

import com.example.exam.model.binding.UserLoginBindingModel;
import com.example.exam.model.binding.UserRegisterBindingModel;
import com.example.exam.model.binding.WordAddBindingModel;
import com.example.exam.model.entity.enums.LanguageName;
import com.example.exam.model.service.UserServiceModel;
import com.example.exam.model.service.WordServiceModel;
import com.example.exam.service.UserService;
import com.example.exam.service.WordService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@org.springframework.stereotype.Controller
public class Controller {
    UserServiceModel userServiceModel;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final WordService wordService;

    public Controller(UserService userService, ModelMapper modelMapper, WordService wordService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("user") == null){
            return "index";
        }
        model.addAttribute("germanWords", wordService.findByLanguageName(LanguageName.GERMAN));
        model.addAttribute("frenchWords", wordService.findByLanguageName(LanguageName.FRENCH));
        model.addAttribute("spanishWords", wordService.findByLanguageName(LanguageName.SPANISH));
        model.addAttribute("italianWords", wordService.findByLanguageName(LanguageName.ITALIAN));

        model.addAttribute("allWordsCount", wordService.countAll());
        model.addAttribute("currentUser", userService.findUserByName(userServiceModel));

        model.addAttribute("germanWordsCount", wordService.countGermanWords(LanguageName.GERMAN));
        model.addAttribute("frenchWordsCount", wordService.countFrenchWords(LanguageName.FRENCH));
        model.addAttribute("spanishWordsCount", wordService.countSpanishWords(LanguageName.SPANISH));
        model.addAttribute("italianWordsCount", wordService.countItalianWords(LanguageName.ITALIAN));

        return "home";
    }
    @GetMapping("/users/register")
    public String register(Model model){
        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/users/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        userService.register(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";
    }
    @GetMapping("users/login")
    public String login(Model model){
        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("NotFound", false);
        }
        return "login";
    }

    @PostMapping("users/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", new UserLoginBindingModel());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }
        userServiceModel = userService.findUserByNameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());
        if(userServiceModel == null){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", new UserLoginBindingModel());
            redirectAttributes.addFlashAttribute("NotFound", true);
            return "redirect:login";
        }

        httpSession.setAttribute("user", userServiceModel);

        return "redirect:/";

    }
    @GetMapping("users/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "index";
    }



    @GetMapping("/words/add")
    public String add(Model model){
        if(!model.containsAttribute("wordAddBindingModel")){
            model.addAttribute("wordAddBindingModel", new WordAddBindingModel());
        }
        return "word-add";
    }
    @PostMapping("/words/add")
    public String addConfirm(@Valid WordAddBindingModel wordAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordAddBindingModel", wordAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordAddBindingModel", bindingResult);
            return "redirect:add";
        }

        wordService.add(modelMapper.map(wordAddBindingModel, WordServiceModel.class), userServiceModel);

        return "redirect:/";

    }
    @GetMapping("word/remove/{id}")
    public String removeWord(@PathVariable String id){
       wordService.removeById(id);
        return "redirect:/";
    }
    @GetMapping("/home/remove-all")
    public String removeAllWords(){
        wordService.removeAll();
        return "redirect:/";
    }

}
