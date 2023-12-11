package com.example.javafxSEP.Java_files;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public void setFloorID(String floorID) {
        this.floorID = floorID;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    public int getAllRoomsSquareMeters(){
        int result=0;
        for (Room r:roomArrayList) {
            result+=r.getSquareMeters();
        }
        return result;
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

    // Copy functions to make a copy by value according to composition declared in class diagram.
    public Floor copy(){
        Floor copyFloor = new Floor(this.floorID,this.level,this.squareMeters);
        copyFloor.setUsage(this.getUsage());
        //Upholding Composition for Arraylist
        copyFloor.roomArrayList = new ArrayList<Room>();
        //Upholding Composition for elements in Arraylist
        for (Room r: this.roomArrayList) {
            copyFloor.roomArrayList.add(r.copy());
        }
        return copyFloor;
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

    public Room getType(String classType){
        //return correct type fo room
        if(classType.equalsIgnoreCase("bathroom")){
            return new Bathroom();
        }
        if (classType.equalsIgnoreCase("kitchen")) {
            return new Kitchen();
        }
        if (classType.equalsIgnoreCase("office")) {
            return new Office();
        }
        if (classType.equalsIgnoreCase("otherroom")) {
            return new OtherRoom();
        }
            throw new IllegalArgumentException("rooms node contains invalid data - roomType not found");
    }

    public boolean doesRoomExist(Room newRoom){
        //contains() use overwritten equal function and checks if roomID exists
        return roomArrayList.contains(newRoom);
    }

    public void infoFromJSON(String jsonText) throws JsonProcessingException {
        //create json object from json text
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonText);
        //TODO: Check if the next line is the correct way to get sub array of a JsonNode type
        ArrayList<String> listRooms = objectMapper.readValue(jsonNode.get("rooms").asText(), new TypeReference<ArrayList<String>>(){});
        for (String s:listRooms)
        {
            Room tempRoom;
            String classType = objectMapper.readTree(jsonText).get("roomType").asText();
            tempRoom = getType(classType);
            //let the type call the correct implementation of the pure virtual function to handle JSON Data
            tempRoom.infoFromJSON(s);
            if(!doesRoomExist(tempRoom)){
                //copy() necessary for composition? - no other variable have access to it
                roomArrayList.add(tempRoom.copy());
            }
        }
        setFloorID(jsonNode.get("floorID").asText());
        setLevel(jsonNode.get("level").asInt());
        setUsage(jsonNode.get("usage").asText());
        setSquareMeters(jsonNode.get("squareMeters").asInt());
    }

        /*
        setRoomID(jsonNode.get("roomID").asText());
        setRoomName(jsonNode.get("roomName").asText());
        setSquareMeters(jsonNode.get("squareMeters").asInt());
        setPowerOutlets(jsonNode.get("powerOutlets").asInt());
        setUtilityRoom(jsonNode.get("utilityRoom").asBoolean());
        setGreyWaterDrainage(jsonNode.get("greyWaterDrainage").asBoolean());
        setBlackWaterDrainage(jsonNode.get("blackWaterDrainage").asBoolean());
        setWaterConnection(jsonNode.get("waterConnections").asInt());
        */

}
