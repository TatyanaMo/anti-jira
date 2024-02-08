package com.app.dao;

import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void storeNewUser(User user) {
        jdbcTemplate.update("INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    }

    public List<User> getAllUsers() {
        RowMapper<User> rowMapper = (rs, rowNumber) -> mapUser(rs);
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    public List<User> getUserByEmail(String email) {
        RowMapper<User> rowMapper = (rs, rowNumber) -> mapUser(rs);
        return jdbcTemplate.query("SELECT * FROM users WHERE email = ?", rowMapper, email);
    }

    private User mapUser(ResultSet rs) throws SQLException {
       User user = new User();

       user.setId(rs.getLong("id"));
       user.setFirstName(rs.getString("first_name"));
       user.setLastName(rs.getString("last_name"));
       user.setEmail(rs.getString("email"));
       user.setPassword(rs.getString("password"));

       return user;
    }
}
