package ru.spring.boot.bootfinalapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.boot.bootfinalapp.model.User;
import ru.spring.boot.bootfinalapp.service.UserService;


import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {  // получим по id
        model.addAttribute("user", userService.getUser(id));
        return "show";
    }

    @GetMapping()
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, //добавляем нового юзера
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {            //проверяем на валидацию
            model.addAttribute("users", userService.getAllUsers());
            return "new";
        }
        userService.saveUser(user);
        return "redirect:/users"; //при добавлении возвращает нас на главную страницу со списком
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, //изменяем данные
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())     //проверяем на валидацию
            return "edit";
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
