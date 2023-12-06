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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class AppController {

    // @FXML MEANS IT IS INITIALIZED/INJECTED THROUGH THE FXML FILE INTO THIS
    // CONTROLLER

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

    private ObservableList<ProjectList> allProjects = FXCollections.observableArrayList();

    // Search by project name
    @FXML
    private TextField searchField;
    private SearchController searchUtility;

    // Search by price
    @FXML
    private TextField minPriceField;
    @FXML
    private TextField maxPriceField;

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

        filterController = new ProjectFilterController(this);
        filterController.initializeFilterButtons();

        projectList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // AddNewProject Menu bar clicked and their individual controller method is
        // called.
        addResidential.setOnAction(event -> AddResidential());
        addCommercial.setOnAction(event -> AddCommercial());
        addIndustrial.setOnAction(event -> AddIndustrial());
        addRoadConstruction.setOnAction(event -> AddRoadConstruction());

        ObservableList<ProjectList> data = FileReader.loadData(); // Load data from JSON

        // This entire section is the property settings for all the current values, need
        // to update here + ProjectList Class if we want to create new values for
        // projects.
        projectList.setItems(data);
        nameCol.setCellValueFactory(cellData -> cellData.getValue().projectNameProperty());
        projectTypeCol.setCellValueFactory(cellData -> cellData.getValue().projectTypeProperty());
        hoursSpentCol.setCellValueFactory(cellData -> cellData.getValue().hoursSpentProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        timelineCol.setCellValueFactory(cellData -> cellData.getValue().timelineProperty().asObject());
        completedCol.setCellValueFactory(cellData -> cellData.getValue().trueFalseProperty());

        this.searchUtility = new SearchController();

        // Add a listener to the searchField for 'search by owner'
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchByProjectName();
        });

        // Add listeners to the price fields for 'search by price'
        minPriceField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchByPrice();
        });
        maxPriceField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchByPrice();
        });

        // Load data from JSON
        allProjects = FileReader.loadData();
        projectList.setItems(allProjects);


        // double click to view a project type model

        projectList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                viewByDoubleClick();
            }
        });
        //
        //
        // INITIALIZE STOPS HERE
    }

    private void viewByDoubleClick() { // If a project has been double-clicked it will show the view
        ProjectList selectedProject = projectList.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            showEditView(selectedProject);
        }
    }

    public void editProject(ProjectList originalProject, ProjectList editedProject) {  // Edit project type
        ObservableList<ProjectList> data = projectList.getItems();

        int index = data.indexOf(originalProject);

        if (index != -1) { // Check if the element is actually existing

            data.set(index, editedProject); // Replace the previous project with the newly edited project

            projectList.setItems(data); // Update the UI by saving data method
            System.out.println("AppController: Updated UI");

            ObservableList<ProjectList> allProjects = FileReader.loadData();

            int allProjectsIndex = -1;
            for (int i = 0; i < allProjects.size(); i++) {
                if (allProjects.get(i).getProjectName().equals(originalProject.getProjectName())) {  // Compare projects by name
                    allProjectsIndex = i;
                    break;
                }
            }
            if (allProjectsIndex != -1) { // if the element exists
                allProjects.set(allProjectsIndex, editedProject);  // changes the UI based on changes
                FileReader.saveData(allProjects); // saves the data into the json
                System.out.println("AppController: Saved to UI and updated in JSON");
            } else {
                System.out.println("AppController: Project not found in UI");
            }
        }
    }

    private void showEditView(ProjectList selectedProject) { // Opens project type based on one of the types clicked
        String projectType = selectedProject.getProjectType();
        switch (projectType) {
            case "Industrial" -> viewIndustrial(selectedProject);
            case "Commercial" -> viewCommercial(selectedProject);
            case "Residential" -> viewResidential(selectedProject);
            case "Road Construction" -> viewRoadConstruction(selectedProject);
        }
    }


    ///////////////  View + edit models  ///////////////
    private void viewCommercial(ProjectList selectedProject) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCommercial.fxml"));
            Parent root = loader.load();

            ViewCommercial viewCommercial = loader.getController();
            viewCommercial.setAppController(this); // reference to App
            viewCommercial.loadProjectData(selectedProject); // Gets the selected project

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewIndustrial(ProjectList selectedProject) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewIndustrial.fxml"));
            Parent root = loader.load();

            ViewIndustrial viewIndustrial = loader.getController();
            viewIndustrial.setAppController(this); // reference to App
            viewIndustrial.loadProjectData(selectedProject); // Gets the selected project

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewResidential(ProjectList selectedProject) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewResidential.fxml"));
            Parent root = loader.load();

            ViewResidential viewResidential = loader.getController();
            viewResidential.setAppController(this); // reference to App
            viewResidential.loadProjectData(selectedProject); // Gets the selected project

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewRoadConstruction(ProjectList selectedProject) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewRoadConstruction.fxml"));
            Parent root = loader.load();

            ViewRoadConstruction viewRoadConstruction = loader.getController();
            viewRoadConstruction.setAppController(this); // reference to App
            viewRoadConstruction.loadProjectData(selectedProject); // Gets the selected project

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /////////////// Create project models ///////////////

    private void AddResidential() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addResidential.fxml"));
            Parent root = loader.load();

            ResidentialController residentialController = loader.getController(); // Declares the variable
            // residentialController and gets the
            // ResidentialController
            residentialController.setAppController(this); // This sets communication between the residential and the
            // AppController
            // The reason we did this is that AppController is the main controller of our
            // application, and other classes need information from the AppController
            // This essentially means the ResidentialController now has access to update
            // data onto the AppController and vice Versa
            // Our example of this is how the residentialController need to add the new
            // projects into the UI in the AppController

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
        }
    }

    @FXML
    private void deleteData(ActionEvent deleteEvent) {
        System.out.println("Delete button clicked");

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION); // JAVAFX has an inbuilt alert system if you want
        // to click yes or cancel
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Confirm delete!");
        confirmation.setContentText("Do you want to delete the project?");

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                ObservableList<ProjectList> selectedProjects = projectList.getSelectionModel().getSelectedItems();

                // Remove selected projects from the allProjects list
                allProjects.removeAll(selectedProjects);

                // Reapply the current filters
                applyCurrentFilters();

                // Save the updated allProjects list to JSON
                FileReader.saveData(new ArrayList<>(allProjects));
                System.out.println("Successfully removed from UI and saved in JSON");
            } else {
                System.out.println("Deletion canceled");
            }
        });
    }

    private void applyCurrentFilters() {
        // Check if any filter is active and apply it
        if (!searchField.getText().isEmpty()) {
            searchByProjectName();
        } else if (!minPriceField.getText().isEmpty() || !maxPriceField.getText().isEmpty()) {
            searchByPrice();
        } else {
            // If no filters are active, just set the TableView to allProjects
            projectList.setItems(allProjects);
        }
    }

    public void addProject(ProjectList newProject) {
        ObservableList<ProjectList> data = projectList.getItems(); // This is everything we can see in the current APP
        // UI
        data.add(newProject); // Adds the new project to the projectList
        projectList.setItems(data); // Adds and updates the data to the actual UI
        FileReader.saveData(newProject); // saves the new project in the JSON file
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

        // When the 'Show All' button is clicked, clear the TextFields for search and
        // price range
        searchField.setText("");
        minPriceField.setText("");
        maxPriceField.setText("");

        // Load all projects from JSON and set them to the TableView
        allProjects = FileReader.loadData();
        projectList.setItems(allProjects);
    }

    // Search by project name
    private void searchByProjectName() {
        // Retrieve the search text from the searchField
        String searchText = searchField.getText();

        if (searchText.isEmpty()) {
            // If the search text is empty, display all projects
            projectList.setItems(allProjects);
        } else {
            // Otherwise, display only the projects that match the search text
            projectList.setItems(allProjects.filtered(project -> project.getProjectName().contains(searchText)));
        }
    }

    // Search by price
    private void searchByPrice() {
        try {
            // Retrieve the minPrice and maxPrice from the price fields
            double minPrice = minPriceField.getText().isEmpty() ? Double.MIN_VALUE
                    : Double.parseDouble(minPriceField.getText());
            double maxPrice = maxPriceField.getText().isEmpty() ? Double.MAX_VALUE
                    : Double.parseDouble(maxPriceField.getText());

            // Filter the data based on the price range from allProjects.
            ObservableList<ProjectList> filteredData = searchUtility.searchByPriceRange(allProjects, minPrice,
                    maxPrice);

            // Update the projectList TableView to display only the items that match the
            // search criteria.
            projectList.setItems(filteredData);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price input");
        }
    }
}
