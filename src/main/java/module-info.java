module com.example.javafxSEP {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.javafxSEP to javafx.fxml;
    exports com.example.javafxSEP;
    exports com.example.javafxSEP.TestClasses;
    opens com.example.javafxSEP.TestClasses to javafx.fxml;
}