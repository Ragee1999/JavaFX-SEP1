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

    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    public CreateController() {
    }
        @FXML
        public void createButtonOnAction() {
            String owner = ownerField.getText();
            String projectType = projectTypeField.getText();
            String completed = completedField.getText();
            String hoursSpent = hoursSpentField.getText();
            String price = priceField.getText();
            String months = monthsField.getText();

            // Convert String values to appropriate types
            boolean completedValue = Boolean.parseBoolean(completed);
            int hoursSpentValue = Integer.parseInt(hoursSpent);
            int monthsValue = Integer.parseInt(months);
            double priceValue = Double.parseDouble(price);

            // Create a new project
            ProjectList newProject = new ProjectList(owner, projectType, completedValue, hoursSpentValue, monthsValue, priceValue);

            // Assuming you have access to the AppController instance, you can add the new project
            appController.addProject(newProject);

            // Close the CreateController window
            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.close();
            System.out.println("testing close confirm + add project?");
    }


    // Cancel by clicking the cancel button and  the CreateController closes
    @FXML
    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Cancel successful");
        stage.close();
    }

}
