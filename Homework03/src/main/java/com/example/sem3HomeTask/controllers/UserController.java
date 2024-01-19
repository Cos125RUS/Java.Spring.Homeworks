package com.example.sem3HomeTask.controllers;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")//localhost:8080/user
public class UserController {


    @Autowired
    private RegistrationService service;

    /**
     * Достаёт из базы лист юзеров
     * @return
     */
    @GetMapping
    public List<User> userList() {
        List<User> allUsers = service.getDataProcessingService().getRepository().findAll();
        for (User user : allUsers) {
            System.out.println(user);
        }
        return allUsers;
    }

    /**
     * Добавление юзера через Body
     * @param user
     * @return
     */
    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user) {
        service.processRegistration(user);
        return "User added from body!";
    }

    /**
     * Добавление юзера через параметры HTTP запроса
     * @param name
     * @param age
     * @param email
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String userAddFromParam(@RequestParam(value = "name", defaultValue = "unknown") String name,
                                   @RequestParam(value = "age", defaultValue = "0") int age,
                                   @RequestParam(value = "email", defaultValue = "unknown") String email) {
        service.processRegistration(name, age, email);
        return "User added from HTTP request!";
    }

}
