package com.employeeCRUD.empCRUD.Controller;

import com.employeeCRUD.empCRUD.Dto.ApiResponse;
import com.employeeCRUD.empCRUD.Entity.Employee;
import com.employeeCRUD.empCRUD.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeController {
    private EmployeeService employeeService;

    public EmployeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployee(@PathVariable Long id) {
        Employee emp = employeeService.findEmpById(id);
        return ResponseEntity.ok(new ApiResponse<>("Success", emp));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Employee>>> getEmployees() {
        List<Employee> list = employeeService.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Success", list));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody Employee employee) {
        Employee savedEmp = employeeService.saveEmp(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Employee created successfully", savedEmp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmploye(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>("Employee deleted successfully", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        Employee updatedEmp = employeeService.updateEmp(employee);
        return ResponseEntity.ok(new ApiResponse<>("Employee updated successfully", updatedEmp));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> patchEmployee(@PathVariable Long id,
                                                               @RequestBody Employee employee) {
        Employee updatedEmp = employeeService.patchEmployee(id, employee);
        return ResponseEntity.ok(new ApiResponse<>("Employee partially updated successfully", updatedEmp));
    }
}