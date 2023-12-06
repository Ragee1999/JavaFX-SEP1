package com.example.javafxSEP.Java_files;

import java.util.ArrayList;

public class RoadConstruction extends ProjectType{

    private double length;
    private double width;
    private ArrayList<String> constructionChallenges;
    private ArrayList<String> bridges;
    private ArrayList<String> tunnels;
    public RoadConstruction(String projectName, String projectType) {
        super(projectName, projectType);
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public ArrayList<String> getConstructionChallenges() {
        return constructionChallenges;
    }

    public ArrayList<String> getBridges() {
        return bridges;
    }

    public ArrayList<String> getTunnels() {
        return tunnels;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setConstructionChallenges(ArrayList<String> constructionChallenges) {
        this.constructionChallenges = constructionChallenges;
    }

    public void setBridges(ArrayList<String> bridges) {
        this.bridges = bridges;
    }

    public void setTunnels(ArrayList<String> tunnels) {
        this.tunnels = tunnels;
    }

    @Override
    public int getSquareMeters() {
        return (int)(length*width);
    }

    @Override
    public int getNumberOfFloor() {
        return 0;
    }

    @Override
    public ArrayList<Floor> getAllFloors() {
        return null;
    }

    @Override
    public ArrayList<Room> getAllRooms() {
        return null;
    }

    @Override
    public int getNumberOfBathroom() {
        return 0;
    }

    @Override
    public int getNumberOfKitchen() {
        return 0;
    }

    @Override
    public int getNumberOfOffice() {
        return 0;
    }

    @Override
    public int getNumberOfOtherRooms() {
        return 0;
    }
}
