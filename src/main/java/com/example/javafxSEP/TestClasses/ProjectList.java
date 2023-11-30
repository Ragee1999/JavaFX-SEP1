package com.example.javafxSEP.TestClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.*;


public class ProjectList {
    // These JavaFX properties allows for binding the data with the UI components.
    private StringProperty projectName;
    private StringProperty projectType;
    private StringProperty trueFalse;
    private IntegerProperty hoursSpent;
    private IntegerProperty timeline;
    private DoubleProperty price;
    private IntegerProperty kitchens;
    private IntegerProperty otherRooms;
    private IntegerProperty bathrooms;
    private StringProperty buildType;
    private IntegerProperty projectSize;

    @JsonCreator
    // annotation for the constructor, it means these properties can be created and deserialized with the jackson library
    public ProjectList
            (
            // Basically this constructor creates these instances from the JSON Data like giving the owner a name
            @JsonProperty("projectName") String projectName,
            @JsonProperty("projectType") String projectType,
            @JsonProperty("trueFalse") String trueFalse,
            @JsonProperty("hoursSpent") int hoursSpent,
            @JsonProperty("timeline") int timeline,
            @JsonProperty("price") double price,
            @JsonProperty("kitchens") int kitchens,
            @JsonProperty("otherRooms") int otherRooms,
            @JsonProperty("bathrooms") int bathrooms,
            @JsonProperty("buildType") String buildType,
            @JsonProperty("projectSize") int projectSize
            )


    {
        // this is the initialize section where properties are given default values or set values
        this.projectName = new SimpleStringProperty(projectName);
        this.projectType = new SimpleStringProperty(projectType);
        this.trueFalse = new SimpleStringProperty(trueFalse);
        this.hoursSpent = new SimpleIntegerProperty(hoursSpent);
        this.timeline = new SimpleIntegerProperty(timeline);
        this.price = new SimpleDoubleProperty(price);
        this.kitchens = new SimpleIntegerProperty(kitchens);
        this.otherRooms = new SimpleIntegerProperty(otherRooms);
        this.bathrooms = new SimpleIntegerProperty(bathrooms);
        this.buildType = new SimpleStringProperty(buildType);
        this.projectSize = new SimpleIntegerProperty(projectSize);

        //  This is deserialization, so it is able to load the json.file from the start of application and then making it into the ProjectList as objects
        // This code does not serialize, that happens in the writeData in the ProjectTestStorage class
    }

    public String getProjectName() {
        return projectName.get();
    }

    public StringProperty projectNameProperty() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }

    public String getProjectType() {
        return projectType.get();
    }

    public StringProperty projectTypeProperty() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType.set(projectType);
    }

    public String getTrueFalse() {
        return trueFalse.get();
    }

    public StringProperty trueFalseProperty() {
        return trueFalse;
    }

    public void setTrueFalse(String trueFalse) {
        this.trueFalse.set(trueFalse);
    }

    public int getHoursSpent() {
        return hoursSpent.get();
    }

    public IntegerProperty hoursSpentProperty() {
        return hoursSpent;
    }

    public void setHoursSpent(int hoursSpent) {
        this.hoursSpent.set(hoursSpent);
    }

    public int getTimeline() {
        return timeline.get();
    }

    public IntegerProperty timelineProperty() {
        return timeline;
    }

    public void setTimeline(int timeline) {
        this.timeline.set(timeline);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getKitchens() {
        return kitchens.get();
    }

    public IntegerProperty kitchensProperty() {
        return kitchens;
    }

    public void setKitchens(int kitchens) {
        this.kitchens.set(kitchens);
    }

    public int getOtherRooms() {
        return otherRooms.get();
    }

    public IntegerProperty otherRoomsProperty() {
        return otherRooms;
    }

    public void setOtherRooms(int otherRooms) {
        this.otherRooms.set(otherRooms);
    }

    public int getBathrooms() {
        return bathrooms.get();
    }

    public IntegerProperty bathroomsProperty() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms.set(bathrooms);
    }

    public String getBuildType() {
        return buildType.get();
    }

    public StringProperty buildTypeProperty() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType.set(buildType);
    }

    public int getProjectSize() {
        return projectSize.get();
    }

    public IntegerProperty projectSizeProperty() {
        return projectSize;
    }

    public void setProjectSize(int projectSize) {
        this.projectSize.set(projectSize);
    }
}

