package com.example.javafxSEP.Java_files;

import java.util.ArrayList;
import java.util.Objects;

public class Floor {
    private String floorID;
    private int level;
    private String usage;
    private int squareMeters;
    private ArrayList<Room> roomArrayList;

    public Floor(String floorID, int level, int squareMeters) {
        this.floorID = floorID;
        this.level = level;
        this.squareMeters = squareMeters;
    }

    public String getFloorID() {
        return floorID;
    }

    public int getLevel() {
        return level;
    }

    public String getUsage() {
        return usage;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public ArrayList<Room> getRoomArrayList() {
        return roomArrayList;
    }

    /* The unique ID shouldn't be changed after instantiation
    public void setFloorID(String floorID) {
        this.floorID = floorID;
    }
     */

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    public void setRoomArrayList(ArrayList<Room> roomArrayList) {
        this.roomArrayList = roomArrayList;
    }
    public static void swapFloor(Floor f1, Floor f2){
        Floor temp = f1;
        f1 = f2;
        f2 = temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return Objects.equals(floorID, floor.floorID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(floorID);
    }

    public int spaceRemaining(){
        int result=0;
        for(Room r : roomArrayList){
            result += r.getSquareMeters();
        }
        result= this.squareMeters - result;
        return result;
    }

    public int getNumberOfRooms(){
        return roomArrayList.size();
    }

    public int getNumberOfKitchens(){
        int result=0;
        for(Room r : roomArrayList){
            if(r instanceof Kitchen){
            result++;
            }
        }
        return result;
    }

    public int getNumberOfBathrooms(){
        int result=0;
        for(Room r : roomArrayList){
            if(r instanceof Bathroom){
                result++;
            }
        }
        return result;
    }
    public int getNumberOfOffices(){
        int result=0;
        for(Room r : roomArrayList){
            if(r instanceof Office){
                result++;
            }
        }
        return result;
    }
    public int getNumberOfOtherRooms(){
        int result=0;
        for(Room r : roomArrayList){
            if(r instanceof OtherRoom){
                result++;
            }
        }
        return result;
    }

}
