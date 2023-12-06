package com.example.javafxSEP.Java_files;

public class ResourceLog extends Log {
    private double budgetSpent;
    private String expenditureCategory;
    private String supplier;
    private String description;

    public ResourceLog(String typeOfLog, String loggedBy, String headline) {
        super(typeOfLog, loggedBy, headline);
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }

    public String getExpenditureCategory() {
        return expenditureCategory;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
    }

    public void setExpenditureCategory(String expenditureCategory) {
        this.expenditureCategory = expenditureCategory;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toJSON() {
        return null;
    }

    //Empty implementation as this log isn't relevant in usage of hours
    @Override
    public double hoursSpentBetween(MyDate startDate, MyDate endDate) {
        return 0;
    }

    @Override
    public double budgetSpentBetween(MyDate startDate, MyDate endDate) {
        if(
            //Log date is between period searched for
            startDate.isBefore(getLogDate())&&
            getLogDate().isBefore(endDate)
        ){
            return getBudgetSpent();
        }
        return 0;
    }
}
