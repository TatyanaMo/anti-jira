package com.app.dao;

import com.app.model.Bookmark;
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
public class BookmarkDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void storeBookmark(Bookmark bookmark) {
        jdbcTemplate.update("INSERT INTO bookmarks (ticket_id, user_id) VALUES (?, ?)",
                bookmark.getTicketId(), bookmark.getUserId());
    }

    public void deleteBookmark(long bookmarkId) {
        jdbcTemplate.update("DELETE FROM bookmarks WHERE id = ?", bookmarkId);
    }

    public List<Bookmark> getUserBookmarks(long userId) {
        RowMapper<Bookmark> rowMapper = (rs, rowNumber) -> mapBookmark(rs);
        return jdbcTemplate.query("SELECT b.id AS bookmark_id, t.id AS ticket_id, t.summary, u.id AS user_id, u.first_name, u.last_name " +
                "FROM bookmarks b " +
                "INNER JOIN tickets t ON b.ticket_id = t.id " +
                "INNER JOIN users u ON t.reporter_id = u.id " +
                "WHERE user_id = ?", rowMapper, userId);
    }

    public Integer getBookmarksCount(long userId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(id) FROM bookmarks WHERE user_id = ?", Integer.class, userId);
    }

    private Bookmark mapBookmark(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setId(rs.getLong("ticket_id"));
        ticket.setSummary(rs.getString("summary"));

        Bookmark bookmark = new Bookmark();

        bookmark.setId(rs.getLong("bookmark_id"));
        bookmark.setTicket(ticket);

        return bookmark;
    }
}
