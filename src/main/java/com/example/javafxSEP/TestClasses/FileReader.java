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

public class FileReader {
    private static final String JSON_FILE_PATH = "project_data.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveData(ProjectList newProject) {
        try {
            List<ProjectList> projectList = loadData(); // Load existing data

            projectList.add(newProject); // Add the new project to the list

            writeData(projectList); // Save the entire list
            System.out.println("Existing Data loaded from JSON, new project added");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveData(List<ProjectList> items) {
        try {
            writeData(items);
            System.out.println("Entire JSON Data updated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ProjectList> loadData() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(JSON_FILE_PATH));

            if (jsonData.length == 0) {
                System.out.println("JSON IS NOW EMPTY");
                return FXCollections.observableArrayList();
            }

            List<ProjectList> projectList = objectMapper.readValue(jsonData, new TypeReference<>() {});
            System.out.println("Json data converted to projectList");

            return FXCollections.observableArrayList(projectList);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading data, empty list returned");
            return FXCollections.observableArrayList();
        }
    }

    private static void writeData(List<ProjectList> projectList) throws IOException {
        Files.write(Paths.get(JSON_FILE_PATH), objectMapper.writeValueAsString(projectList).getBytes(),
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Data is being written to JSON file");
    }

    // Print the entire list for debugging
    public static void printEntireList() {
        List<ProjectList> projectList = loadData();
        projectList.forEach(System.out::println);
    }
}

