package com.app.controllers;

import com.app.model.Ticket;
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
public class TicketsController {
    @Autowired
    private ProjectServices projectServices;

    @Autowired
    private TicketServices ticketServices;

    @GetMapping("/new-ticket")
    public String getNewTicketPage(Model model) {
        model.addAttribute("newTicket", new Ticket());
        model.addAttribute("projects", projectServices.getAllProjects());
        return "newTicket";
    }

    @PostMapping("/new-ticket")
    public String storeNewTicket(@ModelAttribute Ticket ticket) {
        ticketServices.storeNewTicket(ticket);
        return "redirect:/new-ticket";
    }

    @GetMapping("/ticket/edit/{id}")
    public String showEditTicketForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("ticket", ticketServices.getTicketById(id));
        return "editTicket";

    }

    @PostMapping("/ticket/edit/{id}")
    public String editTicket(@ModelAttribute Ticket ticket, @PathVariable("id") long id) {
        ticket.setId(id);
        ticketServices.updateTicket(ticket);
        return "redirect:/";
    }

    @GetMapping("/ticket/{id}")
    public String showTicket(@PathVariable("id") long id, Model model) {
        model.addAttribute("ticket", ticketServices.getTicketById(id));
        return "ticket";
    }

}
