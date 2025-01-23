package com.example.ems.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DashboardView {

    public void showDashboard(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ems/dashboard-view.fxml"));
            Parent root = loader.load();

            // Set the scene and show the stage
            stage.setTitle("HRM Dashboard");
            stage.setScene(new Scene(root, 800, 600)); // Set window size
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the dashboard.");
        }
    }
}
