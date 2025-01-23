package com.example.ems.controller;

import com.example.ems.model.DatabaseConnection;
import com.example.ems.model.Salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryController {

    public boolean addSalary(int employeeId, double amount) {
        String query = "INSERT INTO salaries (employee_id, amount, payment_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, employeeId);
            pstmt.setDouble(2, amount);
            pstmt.setDate(3, Date.valueOf(java.time.LocalDate.now()));

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Salary> getSalaryLogs(int employeeId) {
        List<Salary> salaries = new ArrayList<>();
        String query = "SELECT * FROM salaries WHERE employee_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                salaries.add(new Salary(
                        rs.getInt("id"),
                        rs.getInt("employee_id"),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaries;
    }
}
