package com.employeeCRUD.empCRUD.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "project_table")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "budget")
    private Double budget;

//    @ManyToOne
//    @JoinColumn(name = "employee_id")
//    private Employee employee;
@ManyToOne
@JoinColumn(name = "employee_id")
@JsonBackReference
private Employee employee;
    public Project() {
    }

    public Project(Integer id, Employee employee, Double budget, String projectDescription, String projectName) {
        this.id = id;
        this.employee = employee;
        this.budget = budget;
        this.projectDescription = projectDescription;
        this.projectName = projectName;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
