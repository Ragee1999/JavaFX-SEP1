package com.example.javafxSEP.Java_files;

import java.util.Objects;

public class Milestones {
    private String headline;
    private double budget;
    private String supervisor;
    private MyDate startDate;
    private MyDate endDate;
    private double hoursEstimated;
    private String description;

    public Milestones(String headline, String supervisor, MyDate startDate) {
        this.headline = headline;
        this.supervisor = supervisor;
        this.startDate = startDate;
    }

    public Milestones(String jsonText) {
    }

    public String getHeadline() {
        return headline;
    }

    public double getBudget() {
        return budget;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public MyDate getStartDate() {
        return startDate;
    }

    public MyDate getEndDate() {
        return endDate;
    }

    public double getHoursEstimated() {
        //TODO insert exception for negative hours.
        return hoursEstimated;
    }

    public String getDescription() {
        return description;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setBudget(double budget) {
        //TODO: insert exception for negative value
        this.budget = budget;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public void setStartDate(MyDate startDate) {
        //TODO: insert exception for relative date value
        this.startDate = startDate;
    }

    public void setEndDate(MyDate endDate) {
        //TODO: insert exception for relative date value
        this.endDate = endDate;
    }

    public void setHoursEstimated(double hoursEstimated) {
        this.hoursEstimated = hoursEstimated;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void moveDeadline(int days){
        int cnt = days;
        while(cnt<0){
            endDate.stepBackwardOneDay();
            cnt++;
        }
        while(cnt>0){
            endDate.stepBackwardOneDay();
            cnt--;
        }
    }
    public void moveDeadline(MyDate newDate){
        //TODO: Try block here!
            if (getStartDate().isBefore(newDate)){
                this.setEndDate(newDate);
            }
            else{
                //Throw an error
            }


    }
    public void moveStartingDate(int days){
        int cnt = days;
        while(cnt<0){
            startDate.stepBackwardOneDay();
            cnt++;
        }
        while(cnt>0){
            startDate.stepBackwardOneDay();
            cnt--;
        }
    }
    public void moveStartingDate(MyDate newDate){
        //TODO: Try block here!
        if (newDate.isBefore(endDate)){
            this.setStartDate(newDate);
        }
        else{
            //Throw an error
        }
    }
    public double calculateHoursEstimated(){
        return 1.00;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestones that = (Milestones) o;
        return Objects.equals(headline, that.headline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline);
    }
    public void addInfo(double budget,MyDate startDate, MyDate endDate, double hoursEstimated){
        setBudget(budget);
        setStartDate(startDate);
        setEndDate(endDate);
        setHoursEstimated(hoursEstimated);
    }

    public void appendDescription(String text){
        this.description += "\n";
        this.description += text;
    }
}
