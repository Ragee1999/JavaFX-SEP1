package com.example.javafxSEP.TestClasses;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ProjectTestStorage {
    private static final String JSON_FILE_PATH = "project_data.json";  // Name of the json data file, it is a reference from the JSON_FILE_PATH


    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static void saveData(ProjectList newProject) {  // this static saves 1 new project into the JSON FILE
        try {
            List<ProjectList> projectList = loadData();
            projectList.add(newProject);
            writeData(projectList);
            System.out.println("Existing Data loaded from JSON, new project added");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveData(List<ProjectList> items) { // Saves the list of projects to JSON file
        try {
            writeData(items);
            System.out.println("Entire JSON Data updated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ProjectList> loadData() { // Loads data from JSON file
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(JSON_FILE_PATH)); // reads the json file
            if (jsonData.length == 0) { // checks if file is empty
                System.out.println("JSON IS NOW EMPTY");
                return getInitialProjects(); // returns the initial projects look further down in the code
            }


            // converts JSON data into the projectList data
            List<ProjectList> projectList = objectMapper.readValue(jsonData, new TypeReference<>() {
            }); // Deserialize and TypeReference keeps the values
            System.out.println("Json data converted to projectList");


            return FXCollections.observableArrayList(projectList); // returns the observable list of projects
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading data, empty list returned");
            return FXCollections.observableArrayList();
        }
    }

    // all the projectList is written into the JSON file
    private static void writeData(List<ProjectList> projectList) throws IOException {
        String jsonArray = objectMapper.writeValueAsString(projectList);
        Files.write(Paths.get(JSON_FILE_PATH), (jsonArray + "\n").getBytes(), StandardOpenOption.CREATE);
        System.out.println("Data is being rewritten in JSON file");
    }

    // this is the initial project list in case we don't have any starter data
    private static ObservableList<ProjectList> getInitialProjects() {
        ProjectList residentialProject = new ProjectList("resi", "Residential", "True", 1, 2, 3, 5, 6,2, "New",4);
       List<ProjectList> initialProjects = List.of(residentialProject);
        writeInitialData(initialProjects); // this just writes the data into JSON
        return FXCollections.observableArrayList(initialProjects); // returns the observable list, so we can see it in the UI
    }

    private static void writeInitialData(List<ProjectList> initialProjects) { // this one retrieves the initial data to the file if the json file is empty for some other reason
        try {
            String jsonArray = objectMapper.writeValueAsString(initialProjects);
            Files.write(Paths.get(JSON_FILE_PATH), (jsonArray + "\n").getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


