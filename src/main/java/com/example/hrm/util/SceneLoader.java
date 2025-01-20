package com.example.hrm.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {

    public static void load(String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneLoader.class.getResource("/com/example/hrm/" + fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
