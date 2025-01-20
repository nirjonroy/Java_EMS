module com.example.hrm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.hrm to javafx.fxml;
    exports com.example.hrm;
    exports com.example.hrm.controller;
    opens com.example.hrm.controller to javafx.fxml;
}
