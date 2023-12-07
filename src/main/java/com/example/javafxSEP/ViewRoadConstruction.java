package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewRoadConstruction {

    // ATTRIBUTES
    @FXML
    private TextField tunnelsField;
    @FXML
    private TextField bridgesField;
    @FXML
    private TextField environmentalField;
    @FXML
    private TextField geographicalField;
    @FXML
    private TextField lengthField;
    @FXML
    private TextField widthField;
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

    //Declaration that References to the AppController
    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController; // This is where the reference is set
    }


    public void loadProjectData(ProjectList selectedProject)
    {
        projectNameField.setText(selectedProject.getProjectName());
        priceField.setText(String.valueOf(selectedProject.getPrice()));
        hoursSpentField.setText(String.valueOf(selectedProject.getHoursSpent()));
        timelineField.setText(String.valueOf(selectedProject.getTimeline()));
        lengthField.setText(String.valueOf(selectedProject.getLength()));
        widthField.setText(String.valueOf(selectedProject.getWidth()));
        bridgesField.setText(String.valueOf(selectedProject.getBridges()));
        tunnelsField.setText(String.valueOf(selectedProject.getTunnels()));
        environmentalField.setText(String.valueOf(selectedProject.getEnvironmental()));
        geographicalField.setText(String.valueOf(selectedProject.getGeographical()));

        choiceBoxTrueFalse.getItems().addAll("True", "False");
        // adds the option to choose between true and false in the choice box
        choiceBoxTrueFalse.setValue(selectedProject.getTrueFalse());

        editButton.setOnAction(event -> editButtonOnAction(selectedProject));
    }

    @FXML
    public void editButtonOnAction(ProjectList selectedProject) {
        String editedProjectName = projectNameField.getText();
        double editedPrice = Double.parseDouble(priceField.getText());
        int editedLength = Integer.parseInt(lengthField.getText());
        int editedWidth = Integer.parseInt(widthField.getText());
        int editedBridges = Integer.parseInt(bridgesField.getText());
        int editedTunnels = Integer.parseInt(tunnelsField.getText());
        String editedEnvironmental = environmentalField.getText();
        String editedGeographical = geographicalField.getText();
        int editedHoursSpent = Integer.parseInt(hoursSpentField.getText());
        int editedTimeline = Integer.parseInt(timelineField.getText());
        String editedTrueFalse = choiceBoxTrueFalse.getValue();


        ProjectList editedProject = new ProjectList(
                editedProjectName,
                "Road Construction",
                editedTrueFalse,
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
                "",
                0,
                editedLength,
                editedWidth,
                editedBridges,
                editedTunnels,
                editedEnvironmental,
                editedGeographical
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
