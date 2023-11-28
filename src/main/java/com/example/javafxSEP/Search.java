package com.example.javafxSEP;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.javafxSEP.TestClasses.ProjectList;
import java.util.stream.Collectors;

public class Search {
    public static ObservableList<ProjectList> searchByOwner(ObservableList<ProjectList> data, String searchText) {
        String lowerSearchText = searchText.toLowerCase();
        return data.stream()
                .filter(project -> project.getOwner().toLowerCase().contains(lowerSearchText))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}
