package com.employeeCRUD.empCRUD.DAO;

import com.employeeCRUD.empCRUD.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }


    @Override
    @Transactional
    public Employee save(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = entityManager.find(Employee.class, id);

        if(employee != null){
            entityManager.remove(employee);
        }
    }

    @Override
    public Employee findById(long id) {
        return entityManager.find(Employee.class, id);
    }
    @Override
    public List<Employee> findAll() {

        return entityManager
                .createQuery("FROM Employee", Employee.class)
                .getResultList();
    }
    }

