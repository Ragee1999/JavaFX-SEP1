package com.example.javafxSEP;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjectTestStorage {
    private static final String JSON_FILE_PATH = "project_data.json";

    public static void saveData(ProjectList projectList) {
        String json = JsonTest.toJson(projectList);
        if (json != null) {
            try {
                Files.write(Paths.get(JSON_FILE_PATH), json.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ProjectList loadData() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH))); // Reads JSON DATA FROM FILE
            return JsonTest.fromJson(json); // Converts JSON Data to projectList
        } catch (Exception e) {
            // return a new projectList if there is any errors
            return new ProjectList("","",false, 0,9,500000);
        }
    }
}

