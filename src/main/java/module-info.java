module com.example.hrm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hrm to javafx.fxml;
    exports com.example.hrm;
}