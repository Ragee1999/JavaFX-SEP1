package com.example.javafxSEP;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


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
        ObservableList<ProjectList> data = FXCollections.observableArrayList();
        initializeProjectTable(); // Sample Data to be replaced with JSON
        createNewProject.setOnAction(event -> openCreateController()); // createNewProject button clicked and openCreateController method is called
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

            // Creates new CreateController GUI
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Instead of using sample data I probably need to implement this into the Json to test.
    private void initializeProjectTable() {

        ObservableList<ProjectList> data = FXCollections.observableArrayList
                (
                        new ProjectList("Bob1", "Industrial", true, 200, 3, 150000.0),
                        new ProjectList("Bob2", "Commercial", false, 150, 5, 200000.0),
                        new ProjectList("Bob3", "Industrial", true, 100, 1, 1550000.0),
                        new ProjectList("Bob4", "Commercial", true, 800, 4, 200000.0),
                        new ProjectList("A", "Road Construction", true, 3000, 1, 20000.55),
                        new ProjectList("B", "Road Construction", true, 1200, 3, 340000.0),
                        new ProjectList("CD5", "Road Construction", true, 700, 1, 503000.0),
                        new ProjectList("ABC", "Road Construction", true, 100, 2, 344310.30),
                        new ProjectList("ABDE", "Road Construction", false, 100, 18, 3032000.0),
                        new ProjectList("Z", "Residential", true, 100, 16, 2054200.0),
                        new ProjectList("AB", "Residential", false, 300, 4, 4001000.0),
                        new ProjectList("QWER", "Road Construction", true, 100, 22, 300000.0),
                        new ProjectList("ADS", "Road Construction", true, 500, 7, 700000.0),
                        new ProjectList("D", "Road Construction", true, 200, 10, 500000.15),
                        new ProjectList("Never Finished", "Road Construction", false, 300000, 5000, 8999999.5),
                        new ProjectList("Aaa", "Road Construction", true, 6000, 30, 6666602.0)
                );

        projectList.setItems(data);
        ownerCol.setCellValueFactory(cellData -> cellData.getValue().ownerProperty());
        projectTypeCol.setCellValueFactory(cellData -> cellData.getValue().projectTypeProperty());
        completedCol.setCellValueFactory(cellData -> cellData.getValue().completedProperty());
        hoursSpentCol.setCellValueFactory(cellData -> cellData.getValue().hoursSpentProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        monthsCol.setCellValueFactory(cellData -> cellData.getValue().monthsProperty().asObject());
    }




    // DELETE EVENT - THIS DELETES ANYTHING THAT IS SELECTED

    // NEED TO IMPLEMENT AN EXTRA STEP BEFORE DELETION:
    // "Would you like to delete this"   |||  then 2 buttons Yes / No

    @FXML
    private void deleteData(ActionEvent deleteEvent) {
        System.out.println("Delete button clicked");
        TableView.TableViewSelectionModel<ProjectList> selectionModel = projectList.getSelectionModel();
        ObservableList<ProjectList> selectedItems = selectionModel.getSelectedItems();
        for (ProjectList selectedItem : selectedItems) { // For every selected item
            projectList.getItems().remove(selectedItem);  // Removes the item or project from the UI
            ProjectTestStorage.saveData(selectedItem); // Saves the Data in the JSON file
            System.out.println("Successfully removed");
        }
    }

    public void addProject(ProjectList newProject) {
        ObservableList<ProjectList> data = projectList.getItems(); // This is everything we can see in the current APP UI
        data.add(newProject); // Adds the newly created project to the projectList
        projectList.setItems(data); // Adds and updates the data to the actual UI
        System.out.println("Project added");
        ProjectTestStorage.saveData(newProject);  // saves the new project in the JSON file
    }
}
