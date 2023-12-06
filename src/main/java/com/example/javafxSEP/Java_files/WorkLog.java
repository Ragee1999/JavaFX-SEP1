package com.example.javafxSEP.Java_files;

public class WorkLog extends Log{
    private double budgetSpent;
    private String expenditureCategory;
    private double hoursSpent;
    private MyDate startDate;
    private MyDate endDate;
    private String description;
    public WorkLog(String typeOfLog, String loggedBy, String headline) {
        super(typeOfLog, loggedBy, headline);
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }

    public String getExpenditureCategory() {
        return expenditureCategory;
    }

    public double getHoursSpent() {
        return hoursSpent;
    }

    public MyDate getStartDate() {
        return startDate;
    }

    public MyDate getEndDate() {
        return endDate;
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

    public void setHoursSpent(double hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public void setStartDate(MyDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(MyDate endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int returnOverlappingDays(MyDate startDate, MyDate endDate){
        int notOverlappingDays=0;
        //if period start is after logs starting date
        if(this.getStartDate().isBefore(startDate))
        {
            //how many days after log starting date is period set for
            notOverlappingDays += getStartDate().daysBetween(startDate);
        }

        //if period ends before the logs end date
        if(endDate.isBefore(this.getEndDate()))
        {
            //how many days are not overlapping compared to the end date of the log
            notOverlappingDays += getEndDate().daysBetween(endDate);
        }

        int totalDaysBetween = getStartDate().daysBetween(this.getEndDate());

        if(totalDaysBetween<0){
            totalDaysBetween = 0;
        }

        return totalDaysBetween - notOverlappingDays;
    }

    @Override
    public String toJSON() {
        return null;
    }

    @Override
    public double hoursSpentBetween(MyDate startDate, MyDate endDate) {
        int overlappingDays = returnOverlappingDays(startDate, endDate);
        //if there are overlapping days
        if(overlappingDays>0){
            double totalDays = (double)getStartDate().daysBetween(getEndDate());
            double hoursPerDay = getHoursSpent()/totalDays;
            return hoursPerDay*overlappingDays;
        }
        return 0;
    }

    @Override
    public double budgetSpentBetween(MyDate startDate, MyDate endDate) {
        int overlappingDays = returnOverlappingDays(startDate, endDate);
        //if there are overlapping days
        if(overlappingDays>0){
            double totalDays = (double)getStartDate().daysBetween(getEndDate());
            double budgetSpentPerDay = getBudgetSpent()/totalDays;
            return budgetSpentPerDay*overlappingDays;
        }
        return 0;

    }
}
