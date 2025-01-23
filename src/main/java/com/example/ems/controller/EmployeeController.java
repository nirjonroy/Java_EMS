package com.example.ems.controller;

import com.example.ems.model.Employee;

import com.example.ems.model.DatabaseConnection;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> idColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;
    @FXML
    private TableColumn<Employee, String> joiningDateColumn;

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField salaryField;
    @FXML
    private DatePicker joiningDatePicker;

    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadEmployees();

        // Setting up table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        salaryColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSalary()).asObject());
        joiningDateColumn.setCellValueFactory(new PropertyValueFactory<>("joiningDate"));

        // Handling row selection
        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateForm(newSelection);
            }
        });
    }

    private void loadEmployees() {
        employeeList.clear();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT id, name, email, salary, joining_date FROM employees";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employeeList.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDouble("salary"),
                        rs.getDate("joining_date").toLocalDate()
                ));
            }
            employeeTable.setItems(employeeList);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load employees.");
        }
    }

    @FXML
    private void addEmployee() {
        String name = nameField.getText();
        String email = emailField.getText();
        Double salary = Double.parseDouble(salaryField.getText());
        String joiningDate = joiningDatePicker.getValue().toString();

        String query = "INSERT INTO employees (name, email, salary, joining_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setDouble(3, salary);
            stmt.setString(4, joiningDate);
            stmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Employee added successfully.");
            loadEmployees();
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add employee.");
        }
    }

    @FXML
    private void updateEmployee() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select an employee to update.");
            return;
        }

        String query = "UPDATE employees SET name=?, email=?, salary=?, joining_date=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nameField.getText());
            stmt.setString(2, emailField.getText());
            stmt.setDouble(3, Double.parseDouble(salaryField.getText()));
            stmt.setString(4, joiningDatePicker.getValue().toString());
            stmt.setInt(5, selected.getId());
            stmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Employee updated successfully.");
            loadEmployees();
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update employee.");
        }
    }

    @FXML
    private void deleteEmployee() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select an employee to delete.");
            return;
        }

        String query = "DELETE FROM employees WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, selected.getId());
            stmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Employee deleted successfully.");
            loadEmployees();
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete employee.");
        }
    }

    private void populateForm(Employee employee) {
        nameField.setText(employee.getName());
        emailField.setText(employee.getEmail());
        salaryField.setText(String.valueOf(employee.getSalary()));
        joiningDatePicker.setValue(employee.getJoiningDate());
    }

    private void clearForm() {
        nameField.clear();
        emailField.clear();
        salaryField.clear();
        joiningDatePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
