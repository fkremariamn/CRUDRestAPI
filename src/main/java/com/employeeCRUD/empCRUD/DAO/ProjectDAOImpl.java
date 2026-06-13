package com.employeeCRUD.empCRUD.DAO;

import com.employeeCRUD.empCRUD.Entity.Project;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProjectDAOImpl implements ProjectDAO {
EntityManager entityManager;
    public ProjectDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Project> findAll() {
    return entityManager.createQuery("from Project", Project.class).getResultList();
    }

    @Override
    public Project findById(int id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public Project save(Project project) {
        entityManager.persist(project);
     return project;
    }
    @Override
    public void deleteById(int id) {

        Project project =
                entityManager.find(Project.class, id);

        if(project != null){
            entityManager.remove(project);
        }
    }

    @Override
    public Project update(Project project) {
        Project updated = entityManager.merge(project);
        return updated;
    }
}
