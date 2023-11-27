package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;
import com.example.javafxSEP.TestClasses.ProjectTestStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AppController {

    // @FXML MEANS IT IS INITIALIZED/INJECTED THROUGH THE FXML FILE

    @FXML
    private TableView<ProjectList> projectList;
    @FXML
    private TableColumn<ProjectList, String> ownerCol;
    @FXML
    private TableColumn<ProjectList, String> projectTypeCol;
    @FXML
    private TableColumn<ProjectList, Integer> hoursSpentCol;
    @FXML
    private TableColumn<ProjectList, Double> priceCol;
    @FXML
    private TableColumn<ProjectList, Boolean> completedCol;
    @FXML
    private TableColumn<ProjectList, Integer> monthsCol;


    // need to work on an advanced/better delete function maybe by having trashcan icons that indicate delete
    @FXML
    private TableColumn<ProjectList, Button> removeCol;  //not used currently


    @FXML
    private Button createNewProject; // The button is initialized with the createNewProject button, all buttons can have a specific id, this would be the ID


    // when you run a private void initialize() you initially put code in here that you want to only be run after all the
    // FXML components have been loaded and right before the scene is shown.
    // This is because these methods needs to be run in certain periods and require different setups
    // The ProjectList needs to be loaded/updated regularly
    // InitializeProjectTable is just a sampleData for testing, but it will be applied with default settings in the FUTURE
    // The createNewProject is a buttonEvent that opens a new UI for creating new Projects

    @FXML
    private void initialize() {
        createNewProject.setOnAction(event -> openCreateController()); // createNewProject button clicked and openCreateController method is called
        // initializeProjectTable();   Sample Data to be replaced with JSON
        // ObservableList<ProjectList> data = FXCollections.observableArrayList();
        ObservableList<ProjectList> data = ProjectTestStorage.loadData(); // Load data from JSON

        // This entire section is the property settings for all the current values, need to update here + ProjectList Class if we want to create new values for projects.
        projectList.setItems(data);
        ownerCol.setCellValueFactory(cellData -> cellData.getValue().ownerProperty());
        projectTypeCol.setCellValueFactory(cellData -> cellData.getValue().projectTypeProperty());
        completedCol.setCellValueFactory(cellData -> cellData.getValue().completedProperty());
        hoursSpentCol.setCellValueFactory(cellData -> cellData.getValue().hoursSpentProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        monthsCol.setCellValueFactory(cellData -> cellData.getValue().monthsProperty().asObject());

    }

    private void openCreateController() {
        try {
            // Load the FXML file for CreateController
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Create.fxml"));
            Parent root = loader.load();


            CreateController createController = loader.getController(); // Declares the variable createController and gets the CreateController
            createController.setAppController(this);  // This sets communication between the createController and the AppController
            // The reason we did this is that AppController is the main controller of our application, and they need information from the AppController
            // This essentially means the CreateController now has access to update data onto the AppController and vice Versa
            // One example of this is how the createController need to add the new projects into the UI in the AppController

            // Creates the CreateController GUI
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void deleteData(ActionEvent deleteEvent) {
        System.out.println("Delete button clicked");

        TableView.TableViewSelectionModel<ProjectList> selectionModel = projectList.getSelectionModel();
        ObservableList<ProjectList> selectedItems = selectionModel.getSelectedItems();
        List<ProjectList> updatedProjectList = new ArrayList<>(projectList.getItems());
        updatedProjectList.removeAll(selectedItems);
        projectList.getItems().setAll(updatedProjectList);
        ProjectTestStorage.saveData(updatedProjectList);
        System.out.println("Successfully removed from ui and saved in json");

    }

    public void addProject(ProjectList newProject) {
        ObservableList<ProjectList> data = projectList.getItems(); // This is everything we can see in the current APP UI
        data.add(newProject); // Adds the new project to the projectList
        projectList.setItems(data); // Adds and updates the data to the actual UI
        ProjectTestStorage.saveData(newProject);  // saves the new project in the JSON file
        System.out.println("AppController: saved to UI and added to JSON");
    }
}
