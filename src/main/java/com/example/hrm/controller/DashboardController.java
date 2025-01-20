package com.example.hrm.controller;

import com.example.hrm.util.SceneLoader;
import javafx.fxml.FXML;

public class DashboardController {

    @FXML
    public void handleManageEmployees() {
        SceneLoader.load("employee-management-view.fxml", "HRM - Manage Employees");
    }

    @FXML
    public void handleManageDepartments() {
        SceneLoader.load("department-management-view.fxml", "HRM - Manage Departments");
    }

    @FXML
    public void handleManageRoles() {
        SceneLoader.load("role-management-view.fxml", "HRM - Manage Roles");
    }

    @FXML
    public void handleLogout() {
        SceneLoader.load("login-view.fxml", "HRM System - Login");
    }
}
