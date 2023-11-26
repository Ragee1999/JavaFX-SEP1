package com.example.javafxSEP;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class AppController {

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

    @FXML
    private Button createNewProject;


    @FXML
    private void initialize() {
        initializeProjectTable();
        createNewProject.setOnAction(event -> openCreateController()); // createNewProject button clicked and openCreateController method is called
    }
    private void openCreateController() {
        try {
            // Load the FXML file for CreateController
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Create.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                        new ProjectList("Aaa","Road Construction", true, 6000, 30, 6666602.0)
                );

    projectList.setItems(data);
        ownerCol.setCellValueFactory(cellData -> cellData.getValue().ownerProperty());
        projectTypeCol.setCellValueFactory(cellData -> cellData.getValue().projectTypeProperty());
        completedCol.setCellValueFactory(cellData -> cellData.getValue().completedProperty());
        hoursSpentCol.setCellValueFactory(cellData -> cellData.getValue().hoursSpentProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        monthsCol.setCellValueFactory(cellData -> cellData.getValue().monthsProperty().asObject());
    }

    public void addProject(ProjectList newProject) {
        ObservableList<ProjectList> data = projectList.getItems();
        data.add(newProject);
        projectList.setItems(data);
    }
}
