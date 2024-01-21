package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Контроллер html-запросов
 */
@Controller
@Log
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Домашняя страница
     * @return домашнюю страницу... Уж кто бы мог подумать
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Список пользователей
     * @param model сюда мы и запихаем список пользователей
     * @return страница со списком пользователей
     */
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    /**
     * Отправляет форма для создания нового пользователя
     * @param user здесь могла бы быть ваша реклама
     * @return форма ввода данных пользователя
     */
    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    /**
     * Принимает данные нового пользователя и заносит их в базу
     * @param user данные пользователя
     * @return перенаправляет на страницу со списком юзеров
     */
    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Удаление пользователя по id
     * @param id пользователя
     * @return перенаправляет на страницу со списком юзеров
     */
    @GetMapping("user-delete/{id}")
    public String deleteUserCheck(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * Отправляет форму для обновления данных указанного пользователя
     * @param user для обновления
     * @return форма обновления
     */
    @GetMapping("/user-update/{id}")
    public String updateUserForm(User user){
        return "user-update";
    }

    /**
     * Обновляет данные пользователя
     * @param user обновлённый пользователь
     * @return перенаправляет на страницу со списком юзеров
     */
    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/users";
    }
}
