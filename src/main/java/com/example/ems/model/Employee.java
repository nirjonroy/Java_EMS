package com.example.ems.model;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private String email;
    private double salary;
    private LocalDate joiningDate;

    public Employee(int id, String name, String email, double salary, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    public LocalDate getJoiningDate() { return joiningDate; }
}
