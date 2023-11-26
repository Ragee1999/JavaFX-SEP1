package com.example.javafxSEP;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateController {
    // ATTRIBUTES
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

    //Declaration that References to the AppController
    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController; // This is where the reference is set
    }

    @FXML
    public void createButtonOnAction() {
        // NOTE TO ME: NEED TO ADD SOME CRAZY ERRORHANDLING/CODE - CURRENTLY ONLY THE CORRECT VALUE TYPES WILL WORK, String String int int int boolean

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


        ProjectList newProject = new ProjectList(owner, projectType, completedValue, hoursSpentValue, monthsValue, priceValue); // creates a ProjectList object with the new values
        appController.addProject(newProject); // This adds the newProject to the appController (Main UI)

        //Closes the CreateController after adding newProject
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
        System.out.println("Closed the pop-up window");
    }


    // Cancel by clicking the cancel button and  the CreateController closes
    @FXML
    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Cancel successful");
        stage.close();
    }
}
