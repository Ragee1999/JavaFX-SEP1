package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class IndustrialController {

    // ATTRIBUTES

    @FXML
    private TextField facilitySizeField;
    @FXML
    private TextField facilityUsageField;
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
    @FXML
    private Label exceptionLabel;

    // Declaration that References to the AppController
    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController; // This is where the reference is set
    }

    private void setDefaultValues3() {
        timelineField.setText("30");
        choiceBoxTrueFalse.setValue("False");
    }

    @FXML
    private void initialize() {
        choiceBoxTrueFalse.getItems().addAll("True", "False");
        setDefaultValues3();
    }

    // Cancel/close by clicking the cancel button
    @FXML
    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Cancel successful");
        stage.close();
    }

    @FXML
    public void createButtonOnAction() {

        String projectType = "Industrial";
        String projectName = projectNameField.getText();
        String price = priceField.getText();
        String hoursSpent = hoursSpentField.getText();
        String timeline = timelineField.getText();
        String facilitySize = facilitySizeField.getText();
        String facilityUsage = facilityUsageField.getText();
        String trueFalse = choiceBoxTrueFalse.getValue();

        try {
            // Convert String values to the right types
            int hoursSpentValue = Integer.parseInt(hoursSpent);
            int timelineValue = Integer.parseInt(timeline);
            double priceValue = Double.parseDouble(price);
            int facilitySizeValue = Integer.parseInt(facilitySize);

            if (projectName.isEmpty() || facilityUsage.isEmpty() || trueFalse.isEmpty()) { // these are exceptions for the strings/choice-box options
                throw new IllegalArgumentException();
            }

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
                    0,
                    0,
                    "",
                    facilityUsage,
                    facilitySizeValue,
                    0,
                    0,
                    0,
                    0,
                    "",
                    ""
            );

            appController.addProject(newProject); // This adds the newProject to the appController (Main UI)

            // Closes the ResidentialController after adding newProject
            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.close();
            System.out.println("Closed the pop-up window");

            // this exception handles number errors or general errors.
        } catch (NumberFormatException e) {
            exceptionLabel.setText("Please enter number values in the required fields.");
        } catch (Exception e) { // this exception specifically throws any other exception that's not NumberFormat
            exceptionLabel.setText("An error occurred, please check your inputs.");

            // since we only have 2 types of errors, either strings or numbers, we decided to make all string errors to general errors.
        }
    }
}