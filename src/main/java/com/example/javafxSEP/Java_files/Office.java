package com.example.javafxSEP.Java_files;

public class Office extends Room{
    private int powerOutlets;
    private boolean networkHub;
    private boolean openFloor;
    private boolean privateOffice;
    private int numberOfWorkers;

    public Office(String roomID, String roomName, int powerOutlets, int numberOfWorkers) {
        super(roomID, roomName);
        this.powerOutlets = powerOutlets;
        this.numberOfWorkers = numberOfWorkers;
    }

    public Office(String roomID, String roomName) {
        super(roomID, roomName);
    }

    public int getPowerOutlets() {
        return powerOutlets;
    }

    public boolean isNetworkHub() {
        return networkHub;
    }

    public boolean isOpenFloor() {
        return openFloor;
    }

    public boolean isPrivateOffice() {
        return privateOffice;
    }

    public int getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setPowerOutlets(int powerOutlets) {
        this.powerOutlets = powerOutlets;
    }

    public void setNetworkHub(boolean networkHub) {
        this.networkHub = networkHub;
    }

    public void setOpenFloor(boolean openFloor) {
        this.openFloor = openFloor;
    }

    public void setPrivateOffice(boolean privateOffice) {
        this.privateOffice = privateOffice;
    }

    public void setNumberOfWorkers(int numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    public Office copy(){
        Office o1 = new Office(this.getRoomID(),this.getRoomName(),this.powerOutlets,this.numberOfWorkers);
        o1.setNetworkHub(this.networkHub);
        o1.setOpenFloor(this.openFloor);
        o1.setPrivateOffice(this.privateOffice);
        return o1;
    }

    public static void swap(Office o1,Office o2){
        Office temp = o1;
        o1 = o2;
        o2 = temp;
    }
}
