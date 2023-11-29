package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;

import java.util.List;
import java.util.stream.Collectors;

// SearchModel class handles the logic for searching through lists of ProjectList objects.
public class SearchModel {

    // Searches a list of ProjectList objects for entries where the owner's name contains the specified search text.

    /*
        @param data The list of ProjectList objects to be searched.
        @param searchText The text to search for in the owner's name of each ProjectList.
        @return A list of ProjectList objects that match the search criteria.
     */

    public List<ProjectList> searchByOwner(List<ProjectList> data, String searchText) {
        // Convert searchText to lower case to enable case-insensitive search.
        // This is a constant time operation: O(1).
        String lowerSearchText = searchText.toLowerCase();

        // Stream the data and filter based on whether the owner's name contains the searchText.
        // The filter operation checks each element in the list, making it a linear time operation: O(n).
        return data.stream()
                .filter(project -> project.getOwner().toLowerCase().contains(lowerSearchText))
                .collect(Collectors.toList());
    }

    // The overall time complexity of the searchByOwner method is O(n) where n is the number of elements in the data list.

    public List<ProjectList> searchByPriceRange(List<ProjectList> data, double minPrice, double maxPrice) {
        return data.stream()
                .filter(project -> project.getPrice() >= minPrice && project.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
