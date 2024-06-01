package com.app.controllers;

import com.app.services.ProjectServices;
import com.app.services.TicketServices;
import com.app.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private ProjectServices projectServices;

    @Autowired
    private TicketServices ticketServices;

    @Autowired
    private UserServices userServices;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("projects", projectServices.getAllProjects());
        model.addAttribute("tickets", ticketServices.getLastTickets(30));
        model.addAttribute("username", userServices.getCurrentUser().getFirstName());
        model.addAttribute("avatarUrl", userServices.getCurrentUser().getAvatarPath());
        return "homePage";
    }

}
