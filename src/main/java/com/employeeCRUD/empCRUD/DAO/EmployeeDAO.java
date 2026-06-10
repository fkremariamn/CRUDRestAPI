package com.employeeCRUD.empCRUD.DAO;

import com.employeeCRUD.empCRUD.Entity.Employee;

import java.util.List;
public interface EmployeeDAO {

    Employee save(Employee employee);

    Employee update(Employee employee);

    void delete(Long id);

    Employee findById(long id);

    List<Employee> findAll();
}