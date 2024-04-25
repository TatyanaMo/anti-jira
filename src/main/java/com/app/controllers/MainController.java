package com.app.controllers;

import com.app.services.ProjectServices;
import com.app.services.TicketServices;
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

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("projects", projectServices.getAllProjects());
        model.addAttribute("tickets", ticketServices.getLastTickets(30));
        return "homePage";
    }

}
