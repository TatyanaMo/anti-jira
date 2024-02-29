package com.app.dao;

import com.app.model.Project;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProjectDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void storeNewProject(Project project) {
        jdbcTemplate.update("INSERT INTO projects (name, color) VALUES (?, ?)",
                project.getName(), project.getColor());
    }

    public List<Project> getAllProjects() {
        RowMapper<Project> rowMapper = (rs, rowNumber) -> mapProject(rs);
        return jdbcTemplate.query("SELECT * FROM projects", rowMapper);
    }

    private Project mapProject(ResultSet rs) throws SQLException {
        return new Project(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("color"));
    }
}
