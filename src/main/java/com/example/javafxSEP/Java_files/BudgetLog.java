package com.example.javafxSEP.Java_files;

public class BudgetLog extends Log{
    private MyDate dateChanged;
    private double newBudget;
    private String description;


    public BudgetLog(String typeOfLog, String loggedBy, String headline) {
        super(typeOfLog, loggedBy, headline);
    }

    public MyDate getDateChanged() {
        return dateChanged;
    }

    public double getNewBudget() {
        return newBudget;
    }

    public String getDescription() {
        return description;
    }

    public void setDateChanged(MyDate dateChanged) {
        this.dateChanged = dateChanged;
    }

    public void setNewBudget(double newBudget) {
        this.newBudget = newBudget;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toJSON() {
        return null;
    }
    //Empty implementation as this log isn't relevant in usage of hours or budget
    @Override
    public double hoursSpentBetween(MyDate startDate, MyDate endDate) {
        return 0;
    }

    //Empty implementation as this log isn't relevant in usage of hours or budget
    @Override
    public double budgetSpentBetween(MyDate startDate, MyDate endDate) {
        return 0;
    }
}
