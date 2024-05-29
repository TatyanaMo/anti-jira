package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void storeNewFile(String fileName) {
        jdbcTemplate.update("INSERT INTO files (name) VALUES (?)", fileName);
    }

    public List<String> getAllFiles() {
        RowMapper<String> rowMapper = (rs, rowNumber) -> rs.getString("name");
        return jdbcTemplate.query("SELECT name FROM files", rowMapper);
    }

}
