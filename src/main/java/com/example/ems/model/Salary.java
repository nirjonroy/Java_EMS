package com.example.ems.model;

import java.time.LocalDate;

public class Salary {
    private int id;
    private int employeeId;
    private double amount;
    private LocalDate paymentDate;

    public Salary(int id, int employeeId, double amount, LocalDate paymentDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Salary(int employeeId, double amount) {
        this.employeeId = employeeId;
        this.amount = amount;
        this.paymentDate = LocalDate.now();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
