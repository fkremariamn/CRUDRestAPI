package com.employeeCRUD.empCRUD.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="emp_table")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="organization")
    private String organization;
    @Column(name="department")
    private String department;
    @Column(name="position")
    private String position;
    @Column(name="phone")
    private String phone;
    @Column(name="salary")
    private Double salary;
    public Employee() {
    }

    public Employee(Double salary, String phone, String position, String department, String organization, String email, String lastName, String firstName, Long id) {
        this.salary = salary;
        this.phone = phone;
        this.position = position;
        this.department = department;
        this.organization = organization;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
