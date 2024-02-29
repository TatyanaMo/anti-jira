package com.app.services;

import com.app.dao.ProjectDAO;
import com.app.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServices {
    @Autowired
    private ProjectDAO projectDAO;

    public void storeNewProject(Project project) {
        projectDAO.storeNewProject(project);
    }

    public List<Project> getAllProjects() {
       return projectDAO.getAllProjects();
    }
}
