package com.example.javafxSEP.TestClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.*;


public class ProjectList {
    private StringProperty owner;
    private StringProperty projectType;
    private BooleanProperty completed;
    private IntegerProperty hoursSpent;
    private IntegerProperty months;
    private DoubleProperty price;

    public String getOwner() {
        return owner.get();
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner.set(owner);
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

    public boolean isCompleted() {
        return completed.get();
    }

    public BooleanProperty completedProperty() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed.set(completed);
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

    public int getMonths() {
        return months.get();
    }

    public IntegerProperty monthsProperty() {
        return months;
    }

    public void setMonths(int months) {
        this.months.set(months);
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

    @JsonCreator
    public ProjectList(
            @JsonProperty("owner") String owner,
            @JsonProperty("projectType") String projectType,
            @JsonProperty("completed") boolean completed,
            @JsonProperty("hoursSpent") int hoursSpent,
            @JsonProperty("months") int months,
            @JsonProperty("price") double price) {
        this.owner = new SimpleStringProperty(owner);
        this.projectType = new SimpleStringProperty(projectType);
        this.completed = new SimpleBooleanProperty(completed);
        this.hoursSpent = new SimpleIntegerProperty(hoursSpent);
        this.months = new SimpleIntegerProperty(months);
        this.price = new SimpleDoubleProperty(price);


    }
}



