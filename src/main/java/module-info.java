module com.example.ems {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.ems.controller to javafx.fxml;
    opens com.example.ems.model to javafx.base;  // Add this line to fix the access issue
    opens com.example.ems.view to javafx.fxml;

    exports com.example.ems;
    exports com.example.ems.controller;
}
