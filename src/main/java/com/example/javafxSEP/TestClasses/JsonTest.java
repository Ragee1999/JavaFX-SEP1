package com.example.javafxSEP.TestClasses;
import com.example.javafxSEP.TestClasses.ProjectList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
    private static final ObjectMapper objectMapper = new ObjectMapper(); // Tool that makes it easier to convert JSON into Java Objects and vice verse

    // ProjectList converts Java Objects into JSON String, by using objectMapper from Jackson Library through Maven, which is already built into this JavaFX project.
    public static String toJson(ProjectList projectList) { // Serialization = Turn Java Objects into JSON String
        try {
            return objectMapper.writeValueAsString(projectList);
        } catch (Exception e) {
            logErrorDetails(e);
            return null;
        }
    }

    // This part converts the JSON String into Java Objects inside ProjectList
    public static ProjectList fromJson(String json) {  // Deserialization =  JSON String into Java Objects
        try {
            return objectMapper.readValue(json, ProjectList.class);
        } catch (Exception e) {
            logErrorDetails(e);
            return null;
        }
    }
    // We must log the errors from the JSON
    private static void logErrorDetails(Exception e) {
        System.err.println("Message: " + e.getMessage()); // printed to console via .err
        System.err.println("Class: " + e.getClass().getName()); // printed to console via .err
        e.printStackTrace(); // This basically prints the timeline of the error
    }
}