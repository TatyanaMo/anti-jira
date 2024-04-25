package com.app.dao;

import com.app.model.Ticket;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TicketsDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void storeNewTicket(Ticket ticket) {
      jdbcTemplate.update("INSERT INTO tickets (summary, description, reporter_id, project_id) VALUES (?, ?, ?, ?)",
              ticket.getSummary(), ticket.getDescription(), ticket.getUser().getId(), ticket.getProjectId());
  }

  public void updateTicket(Ticket ticket) {
      jdbcTemplate.update("UPDATE tickets SET summary = ?, description = ? " +
              "WHERE id = ?", ticket.getSummary(), ticket.getDescription(), ticket.getId());
  }
  public List<Ticket> getTicketsByProject(long projectId) {
      RowMapper<Ticket> rowMapper = (rs, rowNumber) -> mapTicket(rs);
      return jdbcTemplate.query("SELECT u.id AS uid, u.first_name, u.last_name, t.id AS tid, t.summary, t.description, t.project_id " +
              "FROM tickets t " +
              "INNER JOIN users u ON t.reporter_id = u.id " +
              "WHERE project_id = ?", rowMapper, projectId);
  }

  public List<Ticket> getLastTickets(int count) {
      RowMapper<Ticket> rowMapper = (rs, rowNumber) -> mapTicket(rs);
      return jdbcTemplate.query("SELECT u.id AS uid, u.first_name, u.last_name, t.id AS tid, t.summary, t.description, t.project_id " +
              "FROM tickets t " +
              "INNER JOIN users u ON t.reporter_id = u.id " +
              "ORDER BY t.id DESC " +
              "LIMIT ?", rowMapper, count);

  }

  public Ticket getTicketById(long id) {
      RowMapper<Ticket> rowMapper = (rs, rowNumber) -> mapTicket(rs);
      return jdbcTemplate.query("SELECT u.id AS uid, u.first_name, u.last_name, t.id AS tid, t.summary, t.description, t.project_id " +
              "FROM tickets t " +
              "INNER JOIN users u ON t.reporter_id = u.id " +
              "WHERE t.id = ? " +
              "ORDER BY t.id DESC ", rowMapper, id).get(0);
  }


  private Ticket mapTicket(ResultSet rs) throws SQLException {
      User user = new User();
      user.setId(rs.getLong("uid"));
      user.setFirstName(rs.getString("first_name"));
      user.setLastName(rs.getString("last_name"));

      Ticket ticket = new Ticket();
      ticket.setId(rs.getLong("tid"));
      ticket.setSummary(rs.getString("summary"));
      ticket.setDescription(rs.getString("description"));
//      ticket.setReporterId(rs.getLong("reporter_id"));
      ticket.setUser(user);
      ticket.setProjectId(rs.getLong("project_id"));
      return ticket;
  }
}
