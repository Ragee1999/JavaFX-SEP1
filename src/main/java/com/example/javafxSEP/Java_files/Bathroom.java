package com.example.javafxSEP.Java_files;

public class Bathroom extends Room{
    private int sinks=0;
    private int toilets=0;
    private boolean bidet;

    private int bathtubs=0;

    //Constructor for user interface
    public Bathroom(String roomID, String roomName, int sinks, int toilets) {
        super(roomID, roomName);
        this.sinks = sinks;
        this.toilets = toilets;
    }

    //minimal Constructor for internal system use
    public Bathroom(String roomID, String roomName) {
        super(roomID, roomName);
    }

    public int getSinks() {
        return sinks;
    }

    public int getToilets() {
        return toilets;
    }

    public boolean isBidet() {
        return bidet;
    }

    public int getBathtubs() {
        return bathtubs;
    }

    public void setSinks(int sinks) {
        this.sinks = sinks;
    }

    public void setToilets(int toilets) {
        this.toilets = toilets;
    }

    public void setBidet(boolean bidet) {
        this.bidet = bidet;
    }

    public void setBathtubs(int bathtubs) {
        this.bathtubs = bathtubs;
    }
    public Bathroom copy(){
        Bathroom b1 = new Bathroom(this.getRoomID(), this.getRoomName(),this.getSinks(), this.getToilets());
        b1.setBidet(this.isBidet());
        b1.setBathtubs(this.getBathtubs());
        return b1;
    }

    public static void swap(Bathroom b1,Bathroom b2){
        Bathroom temp = b1;
        b1 = b2;
        b2 = temp;
    }

}
