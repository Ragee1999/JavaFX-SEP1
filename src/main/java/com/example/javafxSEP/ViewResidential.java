package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewResidential {

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
    private ChoiceBox<String> buildTypeField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button editButton;


    //Declaration that References to the AppController
    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController; // This is where the reference is set
    }


    public void loadProjectData(ProjectList selectedProject) {
        projectNameField.setText(selectedProject.getProjectName());
        priceField.setText(String.valueOf(selectedProject.getPrice()));
        projectSizeField.setText(String.valueOf(selectedProject.getProjectSize()));
        hoursSpentField.setText(String.valueOf(selectedProject.getHoursSpent()));
        timelineField.setText(String.valueOf(selectedProject.getTimeline()));
        kitchensField.setText(String.valueOf(selectedProject.getKitchens()));
        bathroomsField.setText(String.valueOf(selectedProject.getBathrooms()));
        otherRoomsField.setText(String.valueOf(selectedProject.getOtherRooms()));

        choiceBoxTrueFalse.getItems().addAll("True", "False");
        // adds the option to choose between true and false in the choice box
        choiceBoxTrueFalse.setValue(selectedProject.getTrueFalse());

        buildTypeField.getItems().addAll("New", "Renovation");
        // adds the option to choose between new and renovation in the choice box
        buildTypeField.setValue(selectedProject.getBuildType());

        editButton.setOnAction(event -> editButtonOnAction(selectedProject));
    }

    @FXML
    public void editButtonOnAction(ProjectList selectedProject) {
        // Convert String values to right types
        String editedProjectName = projectNameField.getText();
        double editedPrice = Double.parseDouble(priceField.getText());
        int editedProjectSize = Integer.parseInt(projectSizeField.getText());
        int editedKitchens = Integer.parseInt(kitchensField.getText());
        int editedBathrooms = Integer.parseInt(bathroomsField.getText());
        int editedOtherRooms = Integer.parseInt(otherRoomsField.getText());
        int editedHoursSpent = Integer.parseInt(hoursSpentField.getText());
        int editedTimeline = Integer.parseInt(timelineField.getText());
        String editedTrueFalse = choiceBoxTrueFalse.getValue();
        String editedBuildType = buildTypeField.getValue();


        ProjectList editedProject = new ProjectList(// creates a ProjectList object with the new values
                editedProjectName,
                "Residential",
                editedTrueFalse,
                editedHoursSpent,
                editedTimeline,
                editedPrice,
                editedKitchens,
                editedOtherRooms,
                editedBathrooms,
                editedBuildType,
                editedProjectSize,
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

        // Update the project
        appController.editProject(selectedProject, editedProject);


        // Closes window once updated
        Stage stage = (Stage) editButton.getScene().getWindow();
        stage.close();

    }


    // Cancel/close by clicking the cancel button
    @FXML
    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Cancel successful");
        stage.close();
    }
}