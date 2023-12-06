package com.example.javafxSEP.Java_files;

import java.util.ArrayList;

public class ResidentialBuilding extends ProjectType{

    private int maxSquareMeters;

    private ArrayList<Floor> floorsAL;

    public ResidentialBuilding(String projectName, String projectType, int maxSquareMeters) {
        super(projectName, projectType);
        this.maxSquareMeters = maxSquareMeters;
    }

    public int getMaxSquareMeters() {
        return maxSquareMeters;
    }

    public void setMaxSquareMeters(int maxSquareMeters) {
        this.maxSquareMeters = maxSquareMeters;
    }

    public ArrayList<String> getAllFloorIDs(){
        ArrayList<String> result = new ArrayList<String>();
        for (Floor f:floorsAL) {
            if(!(result.contains(f.getFloorID()))){
                result.add(f.getFloorID());
            }
        }
        return result;
    }

    public void addFloor(Floor newFloor){
        ArrayList<String> idList = getAllFloorIDs();
        if(!(idList.contains(newFloor.getFloorID()))){
            floorsAL.add(newFloor.copy());
        }
        else{
            //Throw exception
            throw new RuntimeException("Floor ID already exist in this residential building");
        }
    };

    @Override
    public int getSquareMeters() {
        int result = 0;
        for(Floor f:floorsAL) {
            result += f.getAllRoomsSquareMeters();
        }
        return result;
    }

    @Override
    public int getNumberOfFloor() {
        return floorsAL.size();
    }

    //Deep copy needed to uphold composition
    @Override
    public ArrayList<Floor> getAllFloors() {
        ArrayList<Floor> resultList = new ArrayList<Floor>();
        for (Floor f: floorsAL) {
            resultList.add(f.copy());
        }
        return resultList;
    }
    @Override
    public ArrayList<Room> getAllRooms() {
        ArrayList<Room> resultList = new ArrayList<Room>();
        for (Floor f: floorsAL) {
            for (Room r : f.getRoomArrayList()) {
                resultList.add(r.copy());
            }
        }
        return resultList;
    }
    @Override
    public int getNumberOfBathroom() {
        int result = 0;
        for (Floor f: floorsAL) {
            for (Room r : f.getRoomArrayList()) {
                if(r instanceof Bathroom){
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public int getNumberOfKitchen() {
        int result = 0;
        for (Floor f: floorsAL) {
            for (Room r : f.getRoomArrayList()) {
                if(r instanceof Kitchen){
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public int getNumberOfOffice() {
        int result = 0;
        for (Floor f: floorsAL) {
            for (Room r : f.getRoomArrayList()) {
                if(r instanceof Office){
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public int getNumberOfOtherRooms() {
        int result = 0;
        for (Floor f: floorsAL) {
            for (Room r : f.getRoomArrayList()) {
                if(r instanceof OtherRoom){
                    result++;
                }
            }
        }
        return result;
    }
}
