module com.example.javafxSEP {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    exports com.example.javafxSEP;
    opens com.example.javafxSEP to javafx.fxml, com.fasterxml.jackson.databind;
}