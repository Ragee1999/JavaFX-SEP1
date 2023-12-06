package com.example.javafxSEP.Java_files;

import java.util.ArrayList;
import java.util.Objects;

public class ProjectType {
    private String owner;
    private String projectName;
    private String projectType;
    private double budget;
    private boolean isCompleted;
    private boolean isRenovation;
    private TimeSchedule timeSchedule;
    //private ArrayList<>

    public ProjectType(String projectName, String projectType) {
        this.projectName = projectName;
        this.projectType = projectType;
    }

    public String getOwner() {
        return owner;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public double getBudget() {
        return budget;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public boolean isRenovation() {
        return isRenovation;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setRenovation(boolean renovation) {
        isRenovation = renovation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectType that = (ProjectType) o;
        return Objects.equals(projectName, that.projectName) && Objects.equals(projectType, that.projectType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, projectType);
    }
}
