package com.example.javafxSEP.Java_files;

import java.util.ArrayList;

public class TimeSchedule {
    private MyDate startDate;
    private MyDate endDate;
    private String supervisor;
    private double budgetEstimated;
    private double hoursEstimated;

    private ArrayList<Milestone> milestonesAL;
    private ArrayList<Log> logsAL;

    public TimeSchedule(MyDate startDate, String supervisor) {
        this.startDate = startDate;
        this.supervisor = supervisor;
    }

    public void setStartDate(MyDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(MyDate endDate) {
        this.endDate = endDate;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public void setBudgetEstimated(double budgetEstimated) {
        this.budgetEstimated = budgetEstimated;
    }

    public void setHoursEstimated(double hoursEstimated) {
        this.hoursEstimated = hoursEstimated;
    }

    public void setMilestonesAL(ArrayList<Milestone> milestonesAL) {
        //Todo: make copy to uphold composition?
        this.milestonesAL = milestonesAL;
    }

    public void setLogsAL(ArrayList<Log> logsAL) {
        //Todo: make copy to uphold composition?
        this.logsAL = logsAL;
    }

    public MyDate getStartDate() {
        return startDate;
    }

    public MyDate getEndDate() {
        return endDate;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public double getBudgetEstimated() {
        return budgetEstimated;
    }

    public double getHoursEstimated() {
        return hoursEstimated;
    }
    public ArrayList<Milestone> getMilestonesAL() {
        return milestonesAL;
    }

    public ArrayList<Log> getLogsAL() {
        return logsAL;
    }

    public void addMilestone(Milestone milestone){
        if(milestone.getStartDate().isBefore(this.getStartDate())){
            //Update new Staring date and uphold composition relation with copy
            this.setStartDate(milestone.getStartDate().copy());
        }

        if(this.getEndDate().isBefore(milestone.getEndDate())){
            //Update new end date and uphold composition relation with copy
            this.setEndDate(milestone.getEndDate().copy());
        }

        milestonesAL.add(milestone);
    }

    public double calculateBudget(){
        double result=0;
        for (Milestone m: getMilestonesAL()) {
            result+=m.getBudget();
        }
        return result;
    }
    public void addLog(Log newLog){
        getLogsAL().add(newLog);
    }

    public double calculateBudgetSpent(){
        double result=0;
        for (Log l: getLogsAL()) {
            result+=l.budgetSpentBetween(this.getStartDate(),this.getEndDate());
        }
        return result;
    }

    public double calculateHoursSpent(){
        double result=0;
        for (Log l: getLogsAL()) {
            result+=l.hoursSpentBetween(this.getStartDate(),this.getEndDate());
        }
        return result;
    }


    public double categoryExpenditureTotal(String category){
        double total=0;
        for (Log l: logsAL) {
            if(l instanceof ResourceLog){
                //Cast generalized type to a specialized type to access functions
                String logCategory = ((ResourceLog) l).getExpenditureCategory().toLowerCase();
                if(logCategory.equals(category.toLowerCase())){
                    total+=((ResourceLog) l).getBudgetSpent();
                }
            } else if(l instanceof WorkLog){
                //Cast generalized type to a specialized type to access functions
                String logCategory = ((WorkLog) l).getExpenditureCategory().toLowerCase();
                if(logCategory.equals(category.toLowerCase())){
                    total+=((WorkLog) l).getBudgetSpent();
                }
            }
        }

        return total;
    }
    public double supplierExpenditureTotal(String category){
        double total=0;

        for (Log l: logsAL) {
            if(l instanceof ResourceLog){
                //Cast generalized type to a specialized type to access functions

                String logCategory = ((ResourceLog) l).getSupplier().toLowerCase();
                if(logCategory.equals(category.toLowerCase())){
                    total+=((ResourceLog) l).getBudgetSpent();
                }
            }
        }

        return total;
    }

    public ArrayList<String> getAllExpenditureCategories(){
        ArrayList<String> categoryResults = new ArrayList<String>();
        for (Log l: logsAL) {
            if(l instanceof ResourceLog){
                //Cast generalized type to a specialized type to access functions
                String logCategory = ((ResourceLog) l).getExpenditureCategory().toLowerCase();
                if(!(categoryResults.contains(logCategory))){
                    categoryResults.add(logCategory);
                }

            } else if(l instanceof WorkLog){
                //Cast generalized type to a specialized type to access functions
                String logCategory = ((WorkLog) l).getExpenditureCategory().toLowerCase();
                if(!(categoryResults.contains(logCategory))){
                    categoryResults.add(logCategory);
                }
            }
        }
        return categoryResults;
    }

    public ArrayList<String> getAllSuppliers(){
        ArrayList<String> categoryResults = new ArrayList<String>();

        for (Log l: logsAL) {
            if(l instanceof ResourceLog){
                //Cast generalized type to a specialized type to access functions
                String logCategory = ((ResourceLog) l).getSupplier().toLowerCase();
                if(!(categoryResults.contains(logCategory))){
                    categoryResults.add(logCategory);
                }

            }
        }
        return categoryResults;
    }

    public double hoursLoggedBetween(MyDate startDate, MyDate endDate){
        double total=0;
        for (Log l: logsAL){
            total += l.hoursSpentBetween(startDate,endDate);
        }
        return total;
    }

    public double budgetSpentBetween(MyDate startDate, MyDate endDate){
        double total=0;
        for (Log l: logsAL){
            total += l.budgetSpentBetween(startDate,endDate);
        }
        return total;
    }

}
