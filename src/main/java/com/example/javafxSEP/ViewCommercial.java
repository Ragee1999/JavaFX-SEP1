package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ViewCommercial {

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
    private Button cancelButton;
    @FXML
    private Button editButton;

    // Declaration that References to the AppController
    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController; // This is where the reference is set
    }



    public void loadProjectData(ProjectList selectedProject)
    {
        projectNameField.setText(selectedProject.getProjectName());
        priceField.setText(String.valueOf(selectedProject.getPrice()));
        floorsField.setText(String.valueOf(selectedProject.getFloors()));
        projectSizeField.setText(String.valueOf(selectedProject.getProjectSize()));
        buildingUsageField.setText(selectedProject.getBuildingUsage());
        hoursSpentField.setText(String.valueOf(selectedProject.getHoursSpent()));
        timelineField.setText(String.valueOf(selectedProject.getTimeline()));

        choiceBoxTrueFalse.getItems().addAll("True", "False");
        // adds the option to choose between true and false in the choice box
        choiceBoxTrueFalse.setValue(selectedProject.getTrueFalse());

        editButton.setOnAction(event -> editButtonOnAction(selectedProject));
    }

    @FXML
    public void editButtonOnAction(ProjectList selectedProject) {
        String editedProjectName = projectNameField.getText();
        double editedPrice = Double.parseDouble(priceField.getText());
        int editedFloors = Integer.parseInt(floorsField.getText());
        int editedProjectSize = Integer.parseInt(projectSizeField.getText());
        String editedBuildingUsage = buildingUsageField.getText();
        int editedHoursSpent = Integer.parseInt(hoursSpentField.getText());
        int editedTimeline = Integer.parseInt(timelineField.getText());
        String editedTrueFalse = choiceBoxTrueFalse.getValue();


        ProjectList editedProject = new ProjectList(
                editedProjectName,
                "Commercial",
                editedTrueFalse,
                editedHoursSpent,
                editedTimeline,
                editedPrice,
                0,
                0,
                0,
                "",
                editedProjectSize,
                editedFloors,
                editedBuildingUsage,
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
