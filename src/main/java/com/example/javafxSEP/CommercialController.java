package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CommercialController {

    // ATTRIBUTES

    @FXML
    private TextField floorsField;
    @FXML
    private TextField buildingUsageField;
    @FXML
    private TextField projectSizeField;
    @FXML
    private TextField projectNameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField hoursSpentField;
    @FXML
    private TextField timelineField;
    @FXML
    private ChoiceBox<String> choiceBoxTrueFalse;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;

    // Declaration that References to the AppController
    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController; // This is where the reference is set
    }

    private void setDefaultValues2() {
        timelineField.setText("18");
        floorsField.setText("1");
        choiceBoxTrueFalse.setValue("False");
    }

    @FXML
    private void initialize() {
        choiceBoxTrueFalse.getItems().addAll("True", "False");
        setDefaultValues2();
    }

    @FXML
    public void createButtonOnAction() {

        String projectType = "Commercial";
        String projectName = projectNameField.getText();
        String price = priceField.getText();
        String hoursSpent = hoursSpentField.getText();
        String timeline = timelineField.getText();
        String floors = floorsField.getText();
        String projectSize = projectSizeField.getText();
        String buildingUsage = buildingUsageField.getText();
        String trueFalse = choiceBoxTrueFalse.getValue();

        // Convert String values to the right types
        int hoursSpentValue = Integer.parseInt(hoursSpent);
        int timelineValue = Integer.parseInt(timeline);
        double priceValue = Double.parseDouble(price);
        int projectSizeValue = Integer.parseInt(projectSize);
        int floorsValue = Integer.parseInt(floors);

        ProjectList newProject = new ProjectList(
                projectName,
                projectType,
                trueFalse,
                hoursSpentValue,
                timelineValue,
                priceValue,
                0,
                0,
                0,
                "",
                projectSizeValue,
                floorsValue,
                buildingUsage
        );

        appController.addProject(newProject); // This adds the newProject to the appController (Main UI)
        // Closes the ResidentialController after adding newProject
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
        System.out.println("Closed the pop-up window");
    }

    // Cancel/close by clicking the cancel button
    @FXML
    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Cancel successful");
        stage.close();
    }
}