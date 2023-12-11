package com.example.javafxSEP;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ViewIndustrial {

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
    private ChoiceBox<String> choiceBoxcompleted;
    @FXML
    private Button cancelButton;
    @FXML
    private Button editButton;
    @FXML
    private Label exceptionLabel;


    // Declaration that References to the AppController
    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController; // This is where the reference is set
    }

    public void loadProjectData(ProjectList selectedProject) {
        projectNameField.setText(selectedProject.getProjectName());
        priceField.setText(String.valueOf(selectedProject.getPrice()));
        facilitySizeField.setText(String.valueOf(selectedProject.getFacilitySize()));
        facilityUsageField.setText(String.valueOf(selectedProject.getFacilityUsage()));
        hoursSpentField.setText(String.valueOf(selectedProject.getHoursSpent()));
        timelineField.setText(String.valueOf(selectedProject.getTimeline()));

        choiceBoxcompleted.getItems().addAll("True", "False");
        // adds the option to choose between true and false in the choice box
        choiceBoxcompleted.setValue(selectedProject.getCompleted());

        editButton.setOnAction(event -> editButtonOnAction(selectedProject));
    }

    // Cancel/close by clicking the cancel button
    @FXML
    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Cancel successful");
        stage.close();
    }

    @FXML
    public void editButtonOnAction(ProjectList selectedProject) {

        try {

            // Convert String values to right types
            String editedProjectName = projectNameField.getText();
            double editedPrice = Double.parseDouble(priceField.getText());
            int editedFacilitySize = Integer.parseInt(facilitySizeField.getText());
            String editedFacilityUsage = facilityUsageField.getText();
            int editedHoursSpent = Integer.parseInt(hoursSpentField.getText());
            int editedTimeline = Integer.parseInt(timelineField.getText());
            String editedcompleted = choiceBoxcompleted.getValue();

            if (editedProjectName.isEmpty() || editedFacilityUsage.isEmpty() || editedcompleted.isEmpty()) { // these are exceptions for the strings/choice-box options
                throw new IllegalArgumentException(); // this will be shown as the general error
            }

            ProjectList editedProject = new ProjectList(
                    editedProjectName,
                    "Industrial",
                    editedcompleted,
                    editedHoursSpent,
                    editedTimeline,
                    editedPrice,
                    0,
                    0,
                    0,
                    "",
                    0,
                    0,
                    "",
                    editedFacilityUsage,
                    editedFacilitySize,
                    0,
                    0,
                    0,
                    0,
                    "",
                    ""
            );


            // Update the project
            appController.editProject(selectedProject, editedProject);


            // Closes window once updated
            Stage stage = (Stage) editButton.getScene().getWindow();
            stage.close();


            // this exception handles number errors or general errors.
        } catch (NumberFormatException e) {
            exceptionLabel.setText("Please enter number values in the required fields.");
        } catch (Exception e) { // this exception specifically throws any other exception that's not NumberFormat
            exceptionLabel.setText("An error occurred, please check your inputs.");

            // since we only have 2 types of errors, either strings or numbers, we decided to make all string errors to general errors.
        }
    }
}