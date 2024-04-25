package com.app.services;

import com.app.dao.TicketsDAO;
import com.app.model.Ticket;
import com.app.model.User;
import com.app.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServices {
    @Autowired
    private TicketsDAO ticketsDAO;

    public void storeNewTicket(Ticket ticket) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        User user = new User();
        user.setId(customUserDetails.getUser().getId());
//        ticket.setReporterId(customUserDetails.getUser().getId());
        ticket.setUser(user);
        ticketsDAO.storeNewTicket(ticket);
    }

    public List<Ticket> getTicketsByProject(long projectId) {
        return ticketsDAO.getTicketsByProject(projectId);
    }

    public List<Ticket> getLastTickets(int count) {
        return ticketsDAO.getLastTickets(count);
    }

    public Ticket getTicketById(long id) {
        return ticketsDAO.getTicketById(id);
    }

    public void updateTicket(Ticket ticket) {
        ticketsDAO.updateTicket(ticket);
    }
}
