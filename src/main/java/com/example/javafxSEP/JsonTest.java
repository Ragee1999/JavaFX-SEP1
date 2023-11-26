package com.example.javafxSEP;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // ProjectList converts into JSON String, by useing objectMapper from Jackson Library through Maven built into JavaFX
    public static String toJson(ProjectList projectList) { // Serialization
        try {
            return objectMapper.writeValueAsString(projectList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // This part converts the JSON String into ProjectList
    public static ProjectList fromJson(String json) {  // Deserialization
        try {
            return objectMapper.readValue(json, ProjectList.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
