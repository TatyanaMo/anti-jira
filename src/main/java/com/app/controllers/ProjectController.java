package com.app.controllers;

import com.app.model.Project;
import com.app.services.ProjectServices;
import com.app.services.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    @Autowired
    private ProjectServices projectServices;
    @Autowired
    private TicketServices ticketServices;

    @GetMapping("/new-project")
    public String getNewProjectPage(Model model) {
        model.addAttribute("newProject", new Project());
        return "newProject";
    }

    @PostMapping("/new-project")
    public String storeNewProject(@ModelAttribute Project project) {
        projectServices.storeNewProject(project);
        return "redirect:/";
    }

    @GetMapping("/project/{id}")
    public String openProject(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("tickets", ticketServices.getTicketsByProject(id));
        return "project";
    }
}
