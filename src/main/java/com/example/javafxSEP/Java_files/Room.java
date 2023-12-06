package com.example.javafxSEP.Java_files;

import java.util.Objects;

public abstract class Room {
    //Unique String used for identification
    private String roomID;
    //For User interface
    private String roomName;
    // universal attribute for specialized types
    private int squareMeters;

    public Room(String roomID, String roomName) {
        this.roomID = roomID;
        this.roomName = roomName;
    }

    public String getRoomID() {
        return roomID;
    }

    //public accessor
    public String getRoomName() {
        return roomName;
    }

    //public accessor
    public int getSquareMeters() {
        return squareMeters;
    }

    //public mutator not wanted as this is unique throughout the whole system
/*
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
 */
    //Public mutator
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    //Public mutator
    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    //Override for template library functions
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomID, room.roomID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomID);
    }

    public abstract Room copy();
}
