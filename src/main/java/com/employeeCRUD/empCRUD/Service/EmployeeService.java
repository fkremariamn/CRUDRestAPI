package com.employeeCRUD.empCRUD.Service;

import com.employeeCRUD.empCRUD.DAO.EmployeeDAO;
import com.employeeCRUD.empCRUD.Entity.Employee;
import com.employeeCRUD.empCRUD.Exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    public Employee findEmpById(Long id) {

        Employee employee = employeeDAO.findById(id);

        if (employee == null) {
            throw new ResourceNotFoundException(
                    "Employee with ID " + id + " not found");
        }

        return employee;
    }

    @Transactional
    public void deleteEmploye(long id) {
        employeeDAO.delete(id);
    }

    @Transactional
    public Employee saveEmp(Employee employee) {
        employeeDAO.save(employee);
        return employee;
    }

    @Transactional
    public Employee updateEmp(Employee employee) {
        employeeDAO.update(employee);
        return employee;
    }

    @Transactional
    public Employee patchEmployee(Long id, Employee patchData) {

        Employee existing = employeeDAO.findById(id);

        if (existing == null) {
            throw new ResourceNotFoundException(
                    "Employee with ID " + id + " not found");
        }

        if (patchData.getFirstName() != null)
            existing.setFirstName(patchData.getFirstName());

        if (patchData.getLastName() != null)
            existing.setLastName(patchData.getLastName());

        if (patchData.getEmail() != null)
            existing.setEmail(patchData.getEmail());

        if (patchData.getSalary() != null)
            existing.setSalary(patchData.getSalary());

        if (patchData.getDepartment() != null)
            existing.setDepartment(patchData.getDepartment());

        if (patchData.getOrganization() != null)
            existing.setOrganization(patchData.getOrganization());

        if (patchData.getPhone() != null)
            existing.setPhone(patchData.getPhone());

        if (patchData.getPosition() != null)
            existing.setPosition(patchData.getPosition());

        return employeeDAO.update(existing);
    }
}