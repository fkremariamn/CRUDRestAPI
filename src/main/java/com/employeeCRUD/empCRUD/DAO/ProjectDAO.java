package com.employeeCRUD.empCRUD.DAO;

import com.employeeCRUD.empCRUD.Entity.Project;

import java.util.List;

public interface ProjectDAO {

    List<Project> findAll();

    Project findById(int id);

    Project save(Project project);

    void deleteById(int id);
    Project update(Project project);

}
