package com.example.ems.controller;

import com.example.ems.model.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.ems.model.Department;


public class DepartmentController {

    @FXML
    private TableView<Department> departmentTable;

    @FXML
    private TableColumn<Department, Integer> colId;

    @FXML
    private TableColumn<Department, String> colName;

    @FXML
    private TextField nameField;

    private Connection conn;
    private ObservableList<Department> departmentList;

    @FXML
    public void initialize() {
        conn = DatabaseConnection.getConnection();
        departmentList = FXCollections.observableArrayList();

        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colName.setCellValueFactory(data -> data.getValue().nameProperty());

        loadDepartments();
    }

    private void loadDepartments() {
        departmentList.clear();
        try {
            String query = "SELECT * FROM departments";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                departmentList.add(new Department(rs.getInt("id"), rs.getString("name")));
            }
            departmentTable.setItems(departmentList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addDepartment() {
        if (nameField.getText().isEmpty()) {
            showAlert("Please enter department name");
            return;
        }

        try {
            String query = "INSERT INTO departments (name) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nameField.getText());
            stmt.executeUpdate();
            loadDepartments();
            clearFields();
        } catch (SQLException e) {
            showAlert("Error adding department: " + e.getMessage());
        }
    }

    @FXML
    private void updateDepartment() {
        Department selected = departmentTable.getSelectionModel().getSelectedItem();
        if (selected == null || nameField.getText().isEmpty()) {
            showAlert("Please select a department and enter new name");
            return;
        }

        try {
            String query = "UPDATE departments SET name = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nameField.getText());
            stmt.setInt(2, selected.getId());
            stmt.executeUpdate();
            loadDepartments();
            clearFields();
        } catch (SQLException e) {
            showAlert("Error updating department: " + e.getMessage());
        }
    }

    @FXML
    private void deleteDepartment() {
        Department selected = departmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a department to delete");
            return;
        }

        try {
            String query = "DELETE FROM departments WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, selected.getId());
            stmt.executeUpdate();
            loadDepartments();
            clearFields();
        } catch (SQLException e) {
            showAlert("Error deleting department: " + e.getMessage());
        }
    }

    @FXML
    private void clearFields() {
        nameField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
