package com.employeeCRUD.empCRUD.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
   private String title;
    @Column(name="description")
   private String description;
   @Column(name="min_salary")
   private Long  minimumSalary;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;
    public Job() {
    }

    public Job(Integer id, Long minimumSalary, String title, String description) {
        this.id = id;
        this.minimumSalary = minimumSalary;
        this.title = title;
        this.description = description;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(Long minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
