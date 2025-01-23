package com.example.ems.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class DashboardController {

    @FXML
    private Button employeeButton;

    @FXML
    private Button departmentButton;

    @FXML
    private Button salaryButton;

    @FXML
    private void handleEmployeeButtonAction() {
        showDialog("/com/example/ems/employee-view.fxml", "Manage Employees");
    }

    @FXML
    private void handleDepartmentButtonAction() {
        showDialog("/com/example/ems/department-view.fxml", "Manage Departments");
    }

    @FXML
    private void handleSalaryButtonAction() {
        showDialog("/com/example/ems/salary-view.fxml", "Manage Salaries");
    }

    private void showDialog(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
