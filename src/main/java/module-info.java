module com.example.librarymanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.librarymanager to javafx.fxml;
    exports com.example.librarymanager;
}
