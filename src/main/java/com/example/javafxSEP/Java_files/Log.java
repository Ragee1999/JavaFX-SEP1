package com.example.javafxSEP.Java_files;

import java.util.Objects;

public abstract class Log {
    private String typeOfLog;
    private MyDate logDate;
    private String loggedBy;
    private String Headline;

    public Log(String typeOfLog, String loggedBy, String headline) {
        this.typeOfLog = typeOfLog;
        this.loggedBy = loggedBy;
        Headline = headline;
    }

    public String getTypeOfLog() {
        return typeOfLog;
    }

    public MyDate getLogDate() {
        return logDate;
    }

    public String getLoggedBy() {
        return loggedBy;
    }

    public String getHeadline() {
        return Headline;
    }

    public void setTypeOfLog(String typeOfLog) {
        this.typeOfLog = typeOfLog;
    }

    public void setLogDate(MyDate logDate) {
        this.logDate = logDate;
    }

    public void setLoggedBy(String loggedBy) {
        this.loggedBy = loggedBy;
    }

    public void setHeadline(String headline) {
        Headline = headline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(typeOfLog, log.typeOfLog) && Objects.equals(Headline, log.Headline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfLog, Headline);
    }

    public abstract String toJSON();

    //should return the hours for the given period if the log fall between the dates.
    //Empty value if the log doesn't have hours
    public abstract double hoursSpentBetween(MyDate startDate, MyDate endDate);

    //should return the budget for the given period if the log fall between the dates.
    //Empty value if the log doesn't have hours
    public abstract double budgetSpentBetween(MyDate startDate, MyDate endDate);

}
