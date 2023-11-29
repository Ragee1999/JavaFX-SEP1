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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppController {

    // @FXML MEANS IT IS INITIALIZED/INJECTED THROUGH THE FXML FILE INTO THIS
    // CONTROLLER

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
    private Button createNewProject; // The button is initialized with the createNewProject button, all buttons can
                                     // have a specific id, this would be the ID

    // FREDERIK
    // Search by owner
    @FXML
    private TextField searchField;
    // @FXML
    // private Button searchByOwnerButton;
    private SearchController searchUtility;

    // Search by price
    @FXML
    private TextField minPriceField;
    @FXML
    private TextField maxPriceField;;
    // @FXML
    // private Button searchByPriceButton;

    // when you run a private void initialize() you initially put code in here that
    // you want to only be run after all the
    // FXML components have been loaded and right before the scene is shown.
    // This is because these methods needs to be run in certain periods and require
    // different setups
    // The ProjectList needs to be loaded/updated regularly
    // InitializeProjectTable is just a sampleData for testing, but it will be
    // applied with default settings in the FUTURE
    // The createNewProject is a buttonEvent that opens a new UI for creating new
    // Projects

    @FXML
    private void initialize() {
        createNewProject.setOnAction(event -> openCreateController()); // createNewProject button clicked and
                                                                       // openCreateController method is called
        // initializeProjectTable(); Sample Data to be replaced with JSON
        // ObservableList<ProjectList> data = FXCollections.observableArrayList();
        ObservableList<ProjectList> data = ProjectTestStorage.loadData(); // Load data from JSON

        // This entire section is the property settings for all the current values, need
        // to update here + ProjectList Class if we want to create new values for
        // projects.
        projectList.setItems(data);
        ownerCol.setCellValueFactory(cellData -> cellData.getValue().ownerProperty());
        projectTypeCol.setCellValueFactory(cellData -> cellData.getValue().projectTypeProperty());
        completedCol.setCellValueFactory(cellData -> cellData.getValue().completedProperty());
        hoursSpentCol.setCellValueFactory(cellData -> cellData.getValue().hoursSpentProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        monthsCol.setCellValueFactory(cellData -> cellData.getValue().monthsProperty().asObject());

        // Search
        this.searchUtility = new SearchController();

        // Add a listener to the searchField for 'search by owner'
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchByOwner();
        });

        // Add listeners to the price fields for 'search by price'
        minPriceField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchByPrice();
        });
        maxPriceField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchByPrice();
        });
    }

    private void openCreateController() {
        try {
            // Load the FXML file for CreateController
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Create.fxml"));
            Parent root = loader.load();

            CreateController createController = loader.getController(); // Declares the variable createController and
                                                                        // gets the CreateController
            createController.setAppController(this); // This sets communication between the createController and the
                                                     // AppController
            // The reason we did this is that AppController is the main controller of our
            // application, and they need information from the AppController
            // This essentially means the CreateController now has access to update data
            // onto the AppController and vice Versa
            // One example of this is how the createController need to add the new projects
            // into the UI in the AppController

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
        ObservableList<ProjectList> data = projectList.getItems(); // This is everything we can see in the current APP
                                                                   // UI
        data.add(newProject); // Adds the new project to the projectList
        projectList.setItems(data); // Adds and updates the data to the actual UI
        ProjectTestStorage.saveData(newProject); // saves the new project in the JSON file
        System.out.println("AppController: saved to UI and added to JSON");
    }

    // FREDERIK

    // Search by owner
    /*
     * @FXML
     * private void searchButtonClicked(ActionEvent event) {
     * // Retrieve the text entered in the searchField.
     * String searchText = searchField.getText();
     * // Load the full list of ProjectList objects from storage.
     * ObservableList<ProjectList> data = ProjectTestStorage.loadData();
     * 
     * // Check if the search field is empty.
     * if (searchText.isEmpty()) {
     * // If search field is empty, display all items in the projectList TableView.
     * projectList.setItems(data);
     * } else {
     * // If search field is not empty, perform a search based on the entered text.
     * // The searchUtility.searchByOwner method filters the data based on the
     * // searchText.
     * ObservableList<ProjectList> filteredData = searchUtility.searchByOwner(data,
     * searchText);
     * 
     * // Update the projectList TableView to display only the items that match the
     * // search criteria.
     * projectList.setItems(filteredData);
     * }
     * }
     */

    private void searchByOwner() {
        // Retrieve the text entered in the searchField.
        String searchText = searchField.getText();
        // Load the full list of ProjectList objects from storage.
        ObservableList<ProjectList> data = ProjectTestStorage.loadData();

        // Check if the search field is empty.
        if (searchText.isEmpty()) {
            // If search field is empty, display all items in the projectList TableView.
            projectList.setItems(data);
        } else {
            // If search field is not empty, perform a search based on the entered text.
            // The searchUtility.searchByOwner method filters the data based on the
            // searchText.
            ObservableList<ProjectList> filteredData = searchUtility.searchByOwner(data, searchText);

            // Update the projectList TableView to display only the items that match the
            // search criteria.
            projectList.setItems(filteredData);
        }
    }

    // Search by price
    /*
     * @FXML
     * private void searchByPriceButtonClicked(ActionEvent event) {
     * try {
     * // double minPrice = Double.parseDouble(minPriceField.getText());
     * // double maxPrice = Double.parseDouble(maxPriceField.getText());
     * 
     * // If the minPriceField is empty, set minPrice to 0.0
     * double minPrice = minPriceField.getText().isEmpty() ? Double.MIN_VALUE
     * : Double.parseDouble(minPriceField.getText());
     * // If the maxPriceField is empty, set maxPrice to Double.MAX_VALUE
     * double maxPrice = maxPriceField.getText().isEmpty() ? Double.MAX_VALUE
     * : Double.parseDouble(maxPriceField.getText());
     * 
     * // Load the full list of ProjectList objects from storage.
     * ObservableList<ProjectList> data = ProjectTestStorage.loadData();
     * 
     * // Filter the data based on the price range.
     * ObservableList<ProjectList> filteredData =
     * searchUtility.searchByPriceRange(data, minPrice, maxPrice);
     * 
     * // Update the projectList TableView to display only the items that match the
     * // search criteria.
     * projectList.setItems(filteredData);
     * } catch (NumberFormatException e) {
     * // Handle invalid input in price fields
     * System.out.println("Invalid price input");
     * }
     * }
     */

    private void searchByPrice() {
        try {
            // double minPrice = Double.parseDouble(minPriceField.getText());
            // double maxPrice = Double.parseDouble(maxPriceField.getText());

            // If the minPriceField is empty, set minPrice to 0.0
            double minPrice = minPriceField.getText().isEmpty() ? Double.MIN_VALUE
                    : Double.parseDouble(minPriceField.getText());
            // If the maxPriceField is empty, set maxPrice to Double.MAX_VALUE
            double maxPrice = maxPriceField.getText().isEmpty() ? Double.MAX_VALUE
                    : Double.parseDouble(maxPriceField.getText());

            // Load the full list of ProjectList objects from storage.
            ObservableList<ProjectList> data = ProjectTestStorage.loadData();

            // Filter the data based on the price range.
            ObservableList<ProjectList> filteredData = searchUtility.searchByPriceRange(data, minPrice, maxPrice);

            // Update the projectList TableView to display only the items that match the
            // search criteria.
            projectList.setItems(filteredData);
        } catch (NumberFormatException e) {
            // Handle invalid input in price fields
            System.out.println("Invalid price input");
        }
    }
}
