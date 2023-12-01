package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;
import com.example.javafxSEP.TestClasses.FileReader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AppController {

    // @FXML MEANS IT IS INITIALIZED/INJECTED THROUGH THE FXML FILE INTO THIS CONTROLLER

    @FXML
    private TableView<ProjectList> projectList;
    @FXML
    private TableColumn<ProjectList, String> nameCol;
    @FXML
    private TableColumn<ProjectList, String> projectTypeCol;
    @FXML
    private TableColumn<ProjectList, Integer> hoursSpentCol;
    @FXML
    private TableColumn<ProjectList, Double> priceCol;
    @FXML
    private TableColumn<ProjectList, String> completedCol;
    @FXML
    private TableColumn<ProjectList, Integer> timelineCol;


    private ProjectFilterController filterController; // the attribute for ProjectFilterController class
    @FXML
    Button residentialSort;
    @FXML
    Button industrialSort;
    @FXML
    Button roadConstructionSort;
    @FXML
    Button commercialSort;
    @FXML
    Button showAllSort;
    @FXML
    Button ongoingSort;
    @FXML
    Button completedSort;

    @FXML
    private MenuItem addResidential;
    @FXML
    private MenuItem addCommercial;
    @FXML
    private MenuItem addIndustrial;
    @FXML
    private MenuItem addRoadConstruction;


    // when you run a private void initialize() you initially put code in here that you want to only be run after all the
    // FXML components have been loaded and right before the scene is shown.
    // This is because these methods needs to be run in certain periods and require different setups
    // The ProjectList needs to be loaded/updated regularly
    // InitializeProjectTable is just a sampleData for testing, but it will be applied with default settings in the FUTURE
    // The createNewProject is a buttonEvent that opens a new UI for creating new Projects

    @FXML
    private void initialize() {

        filterController = new ProjectFilterController(this);
        filterController.initializeFilterButtons();

        projectList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // AddNewProject Menu bar clicked and their individual controller method is called.
        addResidential.setOnAction(event -> AddResidential());
        addCommercial.setOnAction(event -> AddCommercial());
        addIndustrial.setOnAction(event -> AddIndustrial());
        addRoadConstruction.setOnAction(event -> AddRoadConstruction());

        ObservableList<ProjectList> data = FileReader.loadData(); // Load data from JSON

        // This entire section is the property settings for all the current values, need to update here + ProjectList Class if we want to create new values for projects.
        projectList.setItems(data);
        nameCol.setCellValueFactory(cellData -> cellData.getValue().projectNameProperty());
        projectTypeCol.setCellValueFactory(cellData -> cellData.getValue().projectTypeProperty());
        hoursSpentCol.setCellValueFactory(cellData -> cellData.getValue().hoursSpentProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        timelineCol.setCellValueFactory(cellData -> cellData.getValue().timelineProperty().asObject());
        completedCol.setCellValueFactory(cellData -> cellData.getValue().trueFalseProperty());
    }

    private void AddResidential() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addResidential.fxml"));
            Parent root = loader.load();

            ResidentialController residentialController = loader.getController(); // Declares the variable residentialController and gets the ResidentialController
            residentialController.setAppController(this);  // This sets communication between the residential and the AppController
            //  The reason we did this is that AppController is the main controller of our application, and other classes need information from the AppController
            // This essentially means the ResidentialController now has access to update data onto the AppController and vice Versa
            // Our example of this is how the residentialController need to add the new projects into the UI in the AppController

            // Creates the Residential.fxml
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void AddCommercial() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addCommercial.fxml"));
            Parent root = loader.load();

            CommercialController commercialController = loader.getController();
            commercialController.setAppController(this);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void AddIndustrial() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addIndustrial.fxml"));
            Parent root = loader.load();

            IndustrialController industrialController = loader.getController();
            industrialController.setAppController(this);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void AddRoadConstruction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addRoadConstruction.fxml"));
            Parent root = loader.load();

            RoadConstructionController roadConstructionController = loader.getController();
            roadConstructionController.setAppController(this);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
    }

    @FXML
    private void deleteData(ActionEvent deleteEvent) {
        System.out.println("Delete button clicked");

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION); //JAVAFX has an inbuilt alert system if you want to click yes or cancel
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Confirm delete!");
        confirmation.setContentText("Do you want to delete the project?");

        confirmation.showAndWait().ifPresent
                (response -> {
                    if (response == ButtonType.OK) {
                        TableView.TableViewSelectionModel<ProjectList> selectionModel = projectList.getSelectionModel();
                        ObservableList<ProjectList> selectedItems = selectionModel.getSelectedItems();
                        List<ProjectList> updatedProjectList = new ArrayList<>(projectList.getItems());
                        updatedProjectList.removeAll(selectedItems);
                        projectList.getItems().setAll(updatedProjectList);
                        FileReader.saveData(updatedProjectList);
                        System.out.println("Successfully removed from ui and saved in json");
                    } else {
                        System.out.println("Deletion cancel");
                    }
                });
    }

    public void addProject(ProjectList newProject) {
        ObservableList<ProjectList> data = projectList.getItems(); // This is everything we can see in the current APP UI
        data.add(newProject); // Adds the new project to the projectList
        projectList.setItems(data); // Adds and updates the data to the actual UI
        FileReader.saveData(newProject);  // saves the new project in the JSON file
        System.out.println("AppController: saved to UI and added to JSON");
    }

    // Project filter based on project type - CHECK ProjectFilterController class

    public void updateProjectTypeFilter(String selectedProjectType) {
        ObservableList<ProjectList> data = FileReader.loadData(); // Load all projects from JSON
        ObservableList<ProjectList> filteredData = FXCollections.observableArrayList();

        for (ProjectList project : data) {
            if (project.getProjectType().equalsIgnoreCase(selectedProjectType)) {
                filteredData.add(project);
            }
        }

        projectList.setItems(filteredData); // Update the UI with filtered data
    }


    public void updateStatusFilter(String selectedStatus) {
        ObservableList<ProjectList> data = FileReader.loadData(); // Load all projects from JSON
        ObservableList<ProjectList> filteredData = FXCollections.observableArrayList();

        for (ProjectList project : data) {
            if (project.getTrueFalse().equalsIgnoreCase(selectedStatus)) {
                filteredData.add(project);
            }
        }

        projectList.setItems(filteredData); // Update the UI with filtered data
    }


    public void showAllProjects() {
        ObservableList<ProjectList> data = FileReader.loadData(); // Load JSON
        projectList.setItems(data); // Update UI with all projects
    }
}




