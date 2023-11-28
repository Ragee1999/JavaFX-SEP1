package com.example.javafxSEP;

import com.example.javafxSEP.TestClasses.ProjectList;

import java.util.List;
import java.util.stream.Collectors;

public class SearchModel {
    public List<ProjectList> searchByOwner(List<ProjectList> data, String searchText) {
        String lowerSearchText = searchText.toLowerCase(); // This is to make the search case insensitive // O(1)
                                                           // constant time operation
        return data.stream()
                .filter(project -> project.getOwner().toLowerCase().contains(lowerSearchText)) // Streaming and
                                                                                               // filtering the list:
                                                                                               // O(1) constant time
                                                                                               // operation
                .collect(Collectors.toList());
    }

    // Overall time-complexity: O(n)
}
