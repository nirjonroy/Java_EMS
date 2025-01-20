package com.example.hrm;

import com.example.hrm.util.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        SceneLoader.load("login-view.fxml", "HRM System - Login");
    }

    public static void main(String[] args) {
        launch();
    }
}
