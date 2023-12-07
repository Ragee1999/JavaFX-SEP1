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
    private StringProperty buildingUsage;
    private IntegerProperty floors;
    private StringProperty facilityUsage;
    private IntegerProperty facilitySize;
    private IntegerProperty length;
    private IntegerProperty width;
    private IntegerProperty bridges;
    private IntegerProperty tunnels;
    private StringProperty environmental;
    private StringProperty geographical;



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
            @JsonProperty("projectSize") int projectSize,
            @JsonProperty("floors") int floors,
            @JsonProperty("buildUsage") String buildingUsage,
            @JsonProperty("facilityUsage") String facilityUsage,
            @JsonProperty("facilitySize") int facilitySize,
            @JsonProperty("length") int length,
            @JsonProperty("width") int width,
            @JsonProperty("bridges") int bridges,
            @JsonProperty("tunnels") int tunnels,
            @JsonProperty("environmental") String environmental,
            @JsonProperty("geographical") String geographical
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
        this.floors = new SimpleIntegerProperty(floors);
        this.buildingUsage = new SimpleStringProperty(buildingUsage);
        this.facilityUsage = new SimpleStringProperty(facilityUsage);
        this.facilitySize = new SimpleIntegerProperty(facilitySize);
        this.length = new SimpleIntegerProperty(length);
        this.width= new SimpleIntegerProperty(width);
        this.bridges = new SimpleIntegerProperty(bridges);
        this.tunnels = new SimpleIntegerProperty(tunnels);
        this.environmental = new SimpleStringProperty(environmental);
        this.geographical = new SimpleStringProperty(geographical);

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

    public String getBuildingUsage() {
        return buildingUsage.get();
    }

    public StringProperty buildingUsageProperty() {
        return buildingUsage;
    }

    public void setBuildingUsage(String buildingUsage) {
        this.buildingUsage.set(buildingUsage);
    }

    public int getFloors() {
        return floors.get();
    }

    public IntegerProperty floorsProperty() {
        return floors;
    }

    public String getFacilityUsage() {
        return facilityUsage.get();
    }

    public StringProperty facilityUsageProperty() {
        return facilityUsage;
    }

    public void setFacilityUsage(String facilityUsage) {
        this.facilityUsage.set(facilityUsage);
    }

    public int getFacilitySize() {
        return facilitySize.get();
    }

    public IntegerProperty facilitySizeProperty() {
        return facilitySize;
    }

    public void setFacilitySize(int facilitySize) {
        this.facilitySize.set(facilitySize);
    }

    public int getLength() {
        return length.get();
    }

    public IntegerProperty lengthProperty() {
        return length;
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public int getWidth() {
        return width.get();
    }

    public IntegerProperty widthProperty() {
        return width;
    }

    public void setWidth(int width) {
        this.width.set(width);
    }

    public int getBridges() {
        return bridges.get();
    }

    public IntegerProperty bridgesProperty() {
        return bridges;
    }

    public void setBridges(int bridges) {
        this.bridges.set(bridges);
    }

    public int getTunnels() {
        return tunnels.get();
    }

    public IntegerProperty tunnelsProperty() {
        return tunnels;
    }

    public void setTunnels(int tunnels) {
        this.tunnels.set(tunnels);
    }

    public String getEnvironmental() {
        return environmental.get();
    }

    public StringProperty environmentalProperty() {
        return environmental;
    }

    public void setEnvironmental(String environmental) {
        this.environmental.set(environmental);
    }

    public String getGeographical() {
        return geographical.get();
    }

    public StringProperty geographicalProperty() {
        return geographical;
    }

    public void setGeographical(String geographical) {
        this.geographical.set(geographical);
    }
}

