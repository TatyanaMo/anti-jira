package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void storeNewFile(String fileName) {
        jdbcTemplate.update("INSERT INTO files (name) VALUES (?)", fileName);
    }
}
