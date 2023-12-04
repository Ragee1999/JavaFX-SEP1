package com.example.javafxSEP.Java_files;

public class Kitchen extends Room {
    private int sinks;
    private int numberOfStoves;
    private int cabinets;
    private boolean dishwasherIncluded;
    private boolean inductionStove;

    public Kitchen(String roomID, String roomName, int sinks, int numberOfStoves, boolean dishwasherIncluded) {
        super(roomID, roomName);
        this.sinks = sinks;
        this.numberOfStoves = numberOfStoves;
        this.dishwasherIncluded = dishwasherIncluded;
    }

    public Kitchen(String roomID, String roomName) {
        super(roomID, roomName);
    }

    public int getSinks() {
        return sinks;
    }

    public int getNumberOfStoves() {
        return numberOfStoves;
    }

    public int getCabinets() {
        return cabinets;
    }

    public boolean isDishwasherIncluded() {
        return dishwasherIncluded;
    }

    public boolean isInductionStove() {
        return inductionStove;
    }

    public void setSinks(int sinks) {
        this.sinks = sinks;
    }

    public void setNumberOfStoves(int numberOfStoves) {
        this.numberOfStoves = numberOfStoves;
    }

    public void setCabinets(int cabinets) {
        this.cabinets = cabinets;
    }

    public void setDishwasherIncluded(boolean dishwasherIncluded) {
        this.dishwasherIncluded = dishwasherIncluded;
    }

    public void setInductionStove(boolean inductionStove) {
        this.inductionStove = inductionStove;
    }

    public Kitchen copy(){
        Kitchen k1 = new Kitchen(this.getRoomID(),this.getRoomName(),this.sinks,this.numberOfStoves, this.dishwasherIncluded);
        k1.setCabinets(this.cabinets);
        k1.setInductionStove(this.inductionStove);
        return k1;
    }

    public static void swap(Kitchen k1,Kitchen k2){
        Kitchen temp = k1;
        k1 = k2;
        k2 = temp;
    }
}
