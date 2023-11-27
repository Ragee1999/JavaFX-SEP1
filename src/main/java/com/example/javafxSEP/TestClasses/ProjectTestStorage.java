package com.example.javafxSEP.TestClasses;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjectTestStorage {
    private static final String JSON_FILE_PATH = "project_data.json"; //  JSON_FILE_PATH holds the file path and name to the json file.

    public static void saveData(ProjectList projectList) {
        String json = JsonTest.toJson(projectList);
        if (json != null) {
            try {
                Files.write(Paths.get(JSON_FILE_PATH), json.getBytes());
                System.out.println("Data saved to JSON file.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // CURRENTLY NOT WORKING WE NEED THE ACTUAL JSON TO IMPLEMENT THIS PROPERLY, HOWEVER WE HAVE A "project_data.json"
    public static ProjectList loadData() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH))); // Reads JSON DATA FROM FILE
            System.out.println("READS JSON DATA");
            return JsonTest.fromJson(json); // Converts JSON Data to projectList
        } catch (Exception e) {
            // return a new projectList if there is any errors
            return new ProjectList("","",false, 0,9,500000);
        }
    }
}

