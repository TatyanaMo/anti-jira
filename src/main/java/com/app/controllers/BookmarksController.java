package com.app.controllers;

import com.app.services.ProjectServices;
import com.app.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookmarksController {

    @Autowired
    private ProjectServices projectServices;

    @Autowired
    private UserServices userServices;

    @GetMapping("/bookmarks")
    public String getBookmarksPage(Model model) {
        model.addAttribute("projects", projectServices.getAllProjects());
        model.addAttribute("username", userServices.getCurrentUser().getFirstName());
        model.addAttribute("avatarUrl", userServices.getCurrentUser().getAvatarPath());
        return "bookmarks";
    }
}
