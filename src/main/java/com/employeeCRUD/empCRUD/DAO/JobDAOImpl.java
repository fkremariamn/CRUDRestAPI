package com.employeeCRUD.empCRUD.DAO;

import com.employeeCRUD.empCRUD.Entity.Employee;
import com.employeeCRUD.empCRUD.Entity.Job;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JobDAOImpl implements JobDAO {
    EntityManager entityManager;
    public JobDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Job> findAll() {

        return entityManager
                .createQuery("FROM Job", Job.class)
                .getResultList();
    }

    @Override
    public Job findById(Integer id) {
        return entityManager.find(Job.class, id);
    }



    @Override
    public Job save(Job job) {
        entityManager.persist(job);
        return job;

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Job update(Job job) {
        return entityManager.merge(job);
    }
}
