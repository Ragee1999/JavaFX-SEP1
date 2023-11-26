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

    private AppController appController; //reference to AppController

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    public void createButtonOnAction() {
        // NEED TO ADD SOME CRAZY ERRORHANDLING - CURRENTLY ONLY THE CORRECT VALUE TYPES WILL WORK, String String int int int boolean

        String owner = ownerField.getText();
        String projectType = projectTypeField.getText();
        String price = priceField.getText();
        String hoursSpent = hoursSpentField.getText();
        String months = monthsField.getText();
        String completed = completedField.getText();

        // Convert String values to right types
        boolean completedValue = Boolean.parseBoolean(completed);
        int hoursSpentValue = Integer.parseInt(hoursSpent);
        int monthsValue = Integer.parseInt(months);
        double priceValue = Double.parseDouble(price);


        ProjectList newProject = new ProjectList(owner, projectType, completedValue, hoursSpentValue, monthsValue, priceValue);
        appController.addProject(newProject);

        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
        System.out.println("Projected added and closed popup");
    }


    // Cancel by clicking the cancel button and  the CreateController closes
    @FXML
    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Cancel successful");
        stage.close();
    }

}
