package com.example.javafxSEP;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateController {

    @FXML
    private TextField ownerField;
    @FXML
    private TextField projectTypeField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField hoursSpentField;
    @FXML
    private TextField monthsField;
    @FXML
    private TextField completedField;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;


    // Cancel by clicking the cancel button and  the CreateController closes
    @FXML
    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Test click");
        stage.close();
    }

}
