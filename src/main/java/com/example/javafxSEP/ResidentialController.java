package com.example.javafxSEP;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ResidentialController {

    // ATTRIBUTES

    @FXML
    private TextField kitchensField;
    @FXML
    private TextField otherRoomsField;
    @FXML
    private TextField projectSizeField;
    @FXML
    private TextField projectNameField;
    @FXML
    private TextField bathroomsField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField hoursSpentField;
    @FXML
    private TextField timelineField;
    @FXML
    private ChoiceBox<String> choiceBoxcompleted;
    @FXML
    private ChoiceBox<String> buildTypeField;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label exceptionLabel;

    //Declaration that References to the AppController
    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController; // This is where the reference is set
    }

    private void setDefaultValues() {
        timelineField.setText("9");
        kitchensField.setText("1");
        bathroomsField.setText("1");
        otherRoomsField.setText("1");
        buildTypeField.setValue("New");
        choiceBoxcompleted.setValue("False");
    }

    @FXML
    private void initialize() {
        choiceBoxcompleted.getItems().addAll("True", "False");
        buildTypeField.getItems().addAll("New", "Renovation");
        setDefaultValues();
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

        String projectType = "Residential";
        String projectName = projectNameField.getText();
        String price = priceField.getText();
        String hoursSpent = hoursSpentField.getText();
        String timeline = timelineField.getText();
        String kitchens = kitchensField.getText();
        String otherRooms = otherRoomsField.getText();
        String bathrooms = bathroomsField.getText();
        String projectSize = projectSizeField.getText();
        String buildType = buildTypeField.getValue();
        String completed = choiceBoxcompleted.getValue();

        try {
            // Convert String values to right types
            int hoursSpentValue = Integer.parseInt(hoursSpent);
            int timelineValue = Integer.parseInt(timeline);
            double priceValue = Double.parseDouble(price);
            int kitchensValue = Integer.parseInt(kitchens);
            int otherRoomsValue = Integer.parseInt(otherRooms);
            int bathroomsValue = Integer.parseInt(bathrooms);
            int projectSizeValue = Integer.parseInt(projectSize);

            if (projectName.isEmpty() || buildType.isEmpty() || completed.isEmpty()) { // these are exceptions for the strings/choice-box options
                throw new IllegalArgumentException();
            }

            ProjectList newProject = new ProjectList(// creates a ProjectList object with the new values
                    projectName,
                    projectType,
                    completed,
                    hoursSpentValue,
                    timelineValue,
                    priceValue,
                    kitchensValue,
                    otherRoomsValue,
                    bathroomsValue,
                    buildType,
                    projectSizeValue,
                    0,
                    "",
                    "",
                    0,
                    0,
                    0,
                    0,
                    0,
                    "",
                    ""
            );

            appController.addProject(newProject); // This adds the newProject to the appController (Main UI)

            //Closes the ResidentialController after adding newProject
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