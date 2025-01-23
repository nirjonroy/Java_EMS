package com.example.ems.view;

import com.example.ems.controller.SalaryController;
import com.example.ems.model.Salary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class SalaryView {

    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField amountField;

    @FXML
    private TableView<Salary> salaryTable;

    @FXML
    private TableColumn<Salary, Integer> idColumn;

    @FXML
    private TableColumn<Salary, Double> amountColumn;

    @FXML
    private TableColumn<Salary, String> paymentDateColumn;

    private SalaryController salaryController;

    @FXML
    public void initialize() {
        salaryController = new SalaryController();

        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getId()));
        amountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getAmount()));
        paymentDateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPaymentDate().toString()));
    }

    @FXML
    private void onAddSalary() {
        try {
            int employeeId = Integer.parseInt(employeeIdField.getText());
            double amount = Double.parseDouble(amountField.getText());

            boolean success = salaryController.addSalary(employeeId, amount);
            if (success) {
                loadSalaryLogs(employeeId);
                clearFields();
            } else {
                showAlert("Error", "Failed to add salary.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input. Please enter valid numbers.");
        }
    }

    private void loadSalaryLogs(int employeeId) {
        List<Salary> salaries = salaryController.getSalaryLogs(employeeId);
        ObservableList<Salary> salaryData = FXCollections.observableArrayList(salaries);
        salaryTable.setItems(salaryData);
    }

    private void clearFields() {
        employeeIdField.clear();
        amountField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
