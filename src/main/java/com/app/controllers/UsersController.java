package com.app.controllers;

import com.app.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/allUsers")
    public String getAllUsersPage(Model model) {
        model.addAttribute("users", userServices.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/avatar-upload")
    public String uploadAvatar() {
        return "avatarUpload";
    }

}
