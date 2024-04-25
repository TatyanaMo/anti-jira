package com.app.controllers;

import com.app.model.User;
import com.app.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersRestController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userServices.getAllUsers();
    }

    @GetMapping("/api/users/{startsWith}")
    public List<User> getUsersStartsWith(@PathVariable("startsWith") String startsWith) {
     return userServices.getAllUsersStartingWith(startsWith);
    }
}
