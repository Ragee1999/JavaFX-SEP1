package com.example.javafxSEP;
import javafx.beans.property.*;

// field
    public class ProjectList {
        private final StringProperty owner;
        private final StringProperty projectType;
        private final BooleanProperty completed;
        private final IntegerProperty hoursSpent;
        private final IntegerProperty months;
        private final DoubleProperty price;

//Constructor
        public ProjectList(String owner, String projectType, boolean completed, int hoursSpent, int months, double price) {
            this.owner = new SimpleStringProperty(owner);
            this.projectType = new SimpleStringProperty(projectType);
            this.completed = new SimpleBooleanProperty(completed);
            this.hoursSpent = new SimpleIntegerProperty(hoursSpent);
            this.months = new SimpleIntegerProperty(months);
            this.price = new SimpleDoubleProperty(price);
        }

        public StringProperty ownerProperty() {
            return owner;
        }
        public StringProperty projectTypeProperty() {
            return projectType;
        }
        public BooleanProperty completedProperty() {
            return completed;
        }
        public IntegerProperty hoursSpentProperty() {
            return hoursSpent;
        }
        public IntegerProperty monthsProperty() {
            return months;
        }
        public DoubleProperty priceProperty() {
            return price;
        }

        public ProjectList(){
            this(null,null,false,0,0,0);
        }

    public String getOwner() {
        return owner.get();
    }
    public String getProjectType() {
        return projectType.get();
    }
    public boolean isCompleted() {
        return completed.get();
    }
    public int getHoursSpent() {
        return hoursSpent.get();
    }
    public int getMonths() {
        return months.get();
    }
    public double getPrice() {
        return price.get();
    }
}


