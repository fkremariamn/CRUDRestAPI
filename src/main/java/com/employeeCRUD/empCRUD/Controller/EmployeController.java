package com.employeeCRUD.empCRUD.Controller;

import com.employeeCRUD.empCRUD.Dto.ApiResponse;
import com.employeeCRUD.empCRUD.Entity.Employee;
import com.employeeCRUD.empCRUD.Service.EmployeeService;
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

//    public Employee getEmployee(@PathVariable Long id) {
//
//        return employeeService.findEmpById(id);
//    }
@GetMapping("/{id}")
public ResponseEntity<ApiResponse<Employee>> getEmployee(@PathVariable Long id) {
    Employee emp = employeeService.findEmpById(id);
    if (emp == null) {
        return ResponseEntity.status(404).body(new ApiResponse<>("Employee not found", null));
    }
    return ResponseEntity.ok(new ApiResponse<>("Success", emp));
}

//    @GetMapping("/all")
//    public List<Employee> getEmployees() {
//        return employeeService.findAll();
//    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Employee>>> getEmployees() {
        List<Employee> list = employeeService.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Success", list));
    }
//    @PostMapping("/Create")
//    public Employee createEmployee(@RequestBody Employee employee) {
//         employeeService.saveEmp(employee);
//        return employee;
//    }
@PostMapping("/create")
public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody Employee employee) {
    Employee savedEmp = employeeService.saveEmp(employee);
    return ResponseEntity.status(201).body(new ApiResponse<>("Employee created", savedEmp));
}

//    @DeleteMapping("/{id}")
//    public void deleteEmployee(@PathVariable Long id) {
//
//        employeeService.deleteEmploye(id);
//    }
@DeleteMapping("/{id}")
public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmploye(id);
    return ResponseEntity.status(204).body(new ApiResponse<>("Employee deleted", null));
}
//    @PutMapping("/{id}")
//    public Employee updateEmployee(@PathVariable Long id,
//                                   @RequestBody Employee employee) {
//        employee.setId(id);
//        employeeService.updateEmp(employee);
//        return employee;
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        Employee updatedEmp = employeeService.updateEmp(employee);
        return ResponseEntity.ok(new ApiResponse<>("Employee updated", updatedEmp));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> patchEmployee(@PathVariable Long id,
                                                               @RequestBody Employee employee) {
        Employee updatedEmp = employeeService.patchEmployee(id, employee);

        if (updatedEmp == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("Employee not found for patch", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("Employee partially updated", updatedEmp));
    }
}
