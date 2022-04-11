module com.example.project_07 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_07 to javafx.fxml;
    exports com.example.project_07;
}