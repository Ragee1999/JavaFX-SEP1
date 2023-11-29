package com.example.javafxSEP;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.javafxSEP.TestClasses.ProjectList;

import java.util.List;
// import java.util.stream.Collectors;

// The Search class serves as an interface to the search functionalities provided by the SearchModel class.
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

    // Instance of SearchModel to handle the actual search logic.
    private SearchModel searchModel;

    // Constructor for the Search class. Initializes an instance of SearchModel.
    public Search() {
        this.searchModel = new SearchModel();
    }

    // Method to search by owner and return an ObservableList
    public ObservableList<ProjectList> searchByOwner(ObservableList<ProjectList> data, String searchText) {
        // Call the searchByOwner method of SearchModel and store the results in a list.
        List<ProjectList> searchResults = searchModel.searchByOwner(data, searchText);

        // Convert the list of search results into an ObservableList for use with JavaFX components.
        return FXCollections.observableArrayList(searchResults);
    }

    // Search by price (minPrice and maxPrice) and return an ObservableList
    public ObservableList<ProjectList> searchByPriceRange(ObservableList<ProjectList> data, double minPrice, double maxPrice) {
        List<ProjectList> searchResults = searchModel.searchByPriceRange(data, minPrice, maxPrice);
        return FXCollections.observableArrayList(searchResults);
    }
}
