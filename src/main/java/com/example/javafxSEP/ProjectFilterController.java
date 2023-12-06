package com.example.javafxSEP;

public class ProjectFilterController {

    private AppController appController;

    public ProjectFilterController(AppController appController) {
        this.appController = appController;
    }

    public void initializeFilterButtons() {
        appController.residentialSort.setOnAction(event -> updateProjectTypeFilter("Residential"));
        appController.commercialSort.setOnAction(event -> updateProjectTypeFilter("Commercial"));
        appController.industrialSort.setOnAction(event -> updateProjectTypeFilter("Industrial"));
        appController.roadConstructionSort.setOnAction(event -> updateProjectTypeFilter("Road Construction"));
        appController.ongoingSort.setOnAction(event -> updateStatusFilter("False"));
        appController.completedSort.setOnAction(event -> updateStatusFilter("True"));
        appController.showAllSort.setOnAction(event -> showAllProjects());
    }

    private void updateProjectTypeFilter(String selectedProjectType) {
        // Call the method in AppController to handle the filter update
        appController.updateProjectTypeFilter(selectedProjectType);
    }

    private void updateStatusFilter(String selectedStatus) {
        appController.updateStatusFilter(selectedStatus);
    }

    private void showAllProjects() {
        // Call the method in AppController to show all projects
        appController.showAllProjects();
    }
}
