package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox<String> choiceBoxTrueFalse;
    @FXML
    private ChoiceBox<String>  buildTypeField;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;

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
        choiceBoxTrueFalse.setValue("False");
    }

    @FXML
    private void initialize(){
        choiceBoxTrueFalse.getItems().addAll("True", "False");
        buildTypeField.getItems().addAll("New", "Renovation");
        setDefaultValues();
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
        String trueFalse = choiceBoxTrueFalse.getValue();



        // Convert String values to right types
        int hoursSpentValue = Integer.parseInt(hoursSpent);
        int timelineValue = Integer.parseInt(timeline);
        double priceValue = Double.parseDouble(price);
        int kitchensValue = Integer.parseInt(kitchens);
        int otherRoomsValue = Integer.parseInt(otherRooms);
        int bathroomsValue = Integer.parseInt(bathrooms);
        int projectSizeValue = Integer.parseInt(projectSize);



        ProjectList newProject = new ProjectList(// creates a ProjectList object with the new values
                projectName,
                projectType,
                trueFalse,
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
    }


    // Cancel/close by clicking the cancel button
    @FXML
    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Cancel successful");
        stage.close();
    }
}