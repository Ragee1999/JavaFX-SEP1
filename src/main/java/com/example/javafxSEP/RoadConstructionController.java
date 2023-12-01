package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RoadConstructionController {

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
    private Button createButton;
    @FXML
    private Button cancelButton;

    //Declaration that References to the AppController
    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController; // This is where the reference is set
    }

    private void setDefaultValues4() {
        timelineField.setText("18");
        tunnelsField.setText("0");
        bridgesField.setText("0");
        geographicalField.setText("none");
        environmentalField.setText("none");
        choiceBoxTrueFalse.setValue("False");
    }

    @FXML
    private void initialize(){
        choiceBoxTrueFalse.getItems().addAll("True", "False");
        setDefaultValues4();
    }


    @FXML
    public void createButtonOnAction() {


        String projectType = "Road Construction";
        String projectName = projectNameField.getText();
        String price = priceField.getText();
        String hoursSpent = hoursSpentField.getText();
        String timeline = timelineField.getText();
        String tunnels = tunnelsField.getText();
        String bridges= bridgesField.getText();
        String length = lengthField.getText();
        String width = widthField.getText();
        String geographical = geographicalField.getText();
        String environmental = environmentalField.getText();
        String trueFalse = choiceBoxTrueFalse.getValue();



        // Convert String values to right types
        int hoursSpentValue = Integer.parseInt(hoursSpent);
        int timelineValue = Integer.parseInt(timeline);
        double priceValue = Double.parseDouble(price);
        int tunnelsValue = Integer.parseInt(tunnels);
        int bridgesValue = Integer.parseInt(bridges);
        int lengthValue = Integer.parseInt(length);
        int widthValue = Integer.parseInt(width);



        ProjectList newProject = new ProjectList(// creates a ProjectList object with the new values
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
                "",
                0,
                lengthValue,
                widthValue,
                bridgesValue,
                tunnelsValue,
                environmental,
                geographical
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