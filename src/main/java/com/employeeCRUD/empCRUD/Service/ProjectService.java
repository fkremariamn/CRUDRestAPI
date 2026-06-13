package com.employeeCRUD.empCRUD.Service;

import com.employeeCRUD.empCRUD.DAO.ProjectDAOImpl;
import com.employeeCRUD.empCRUD.Entity.Project;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    ProjectDAOImpl projectImp;
@Autowired
    public ProjectService(ProjectDAOImpl projectImp) {
        this.projectImp = projectImp;
    }

    public List<Project> findAll() {
        return projectImp.findAll();
    }
    public Project findById(int id) {
        return projectImp.findById(id);
    }
    @Transactional
    public Project save(Project project) {
       return projectImp.save(project);
    }
    @Transactional
    public void deleteById(int id) {
         projectImp.deleteById(id);
    }
    @Transactional
    public Project update(Project project) {
       return projectImp.update(project);
    }
    @Transactional
    public Project patchProject(int id, Project incoming) {

        Project existing = projectImp.findById(id);

        if(existing == null){
            return null;
        }

        if(incoming.getProjectName() != null){
            existing.setProjectName(incoming.getProjectName());
        }

        if(incoming.getProjectDescription() != null){
            existing.setProjectDescription(
                    incoming.getProjectDescription());
        }

        if(incoming.getBudget() != null){
            existing.setBudget(incoming.getBudget());
        }
        if (incoming.getEmployee() != null &&
                incoming.getEmployee().getId() != null) {

            existing.setEmployee(incoming.getEmployee());
        }

        return projectImp.update(existing);
    }
}
