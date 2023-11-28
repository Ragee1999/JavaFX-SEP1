package com.example.javafxSEP;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.javafxSEP.TestClasses.ProjectList;

import java.util.List;
// import java.util.stream.Collectors;

public class Search {
    /*
     * public static ObservableList<ProjectList>
     * searchByOwner(ObservableList<ProjectList> data, String searchText) {
     * String lowerSearchText = searchText.toLowerCase();
     * return data.stream()
     * .filter(project ->
     * project.getOwner().toLowerCase().contains(lowerSearchText))
     * .collect(Collectors.toCollection(FXCollections::observableArrayList));
     * }
     */

    private SearchModel searchModel;

    public Search() {
        this.searchModel = new SearchModel();
    }

    // Method to search by owner and return an ObservableList
    public ObservableList<ProjectList> searchByOwner(ObservableList<ProjectList> data, String searchText) {
        List<ProjectList> searchResults = searchModel.searchByOwner(data, searchText);
        return FXCollections.observableArrayList(searchResults);
    }

}
