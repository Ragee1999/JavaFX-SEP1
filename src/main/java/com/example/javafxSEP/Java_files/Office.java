package com.example.javafxSEP.Java_files;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


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

    //minimal Constructor for internal system use in JSON reader
    public Office() {
        super("1", "templateOffice");
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
        if(powerOutlets<0){
            throw new IllegalArgumentException("powerOutlets Value under 0 not valid");
        }
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
        if(numberOfWorkers<0){
            throw new IllegalArgumentException("numberOfWorkers Value under 0 not valid");
        }
        this.numberOfWorkers = numberOfWorkers;
    }

    public Office copy(){
        Office o1 = new Office(this.getRoomID(),this.getRoomName(),this.powerOutlets,this.numberOfWorkers);
        o1.setNetworkHub(this.networkHub);
        o1.setOpenFloor(this.openFloor);
        o1.setPrivateOffice(this.privateOffice);
        return o1;
    }

    @Override
    public void infoFromJSON(String jsonText) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonText);
        String type = jsonNode.get("roomType").asText();
        if(!(type.equalsIgnoreCase("office"))){
            throw new IllegalArgumentException("roomType is different from 'Office'");
        }
        setRoomID(jsonNode.get("roomID").asText());
        setRoomName(jsonNode.get("roomName").asText());
        setSquareMeters(jsonNode.get("squareMeters").asInt());
        setPowerOutlets(jsonNode.get("powerOutlets").asInt());
        setNetworkHub(jsonNode.get("networkHub").asBoolean());
        setOpenFloor(jsonNode.get("openFloor").asBoolean());
        setPrivateOffice(jsonNode.get("privateOffice").asBoolean());
        setNumberOfWorkers(jsonNode.get("numberOfWorkers").asInt());
    }

    public static void swap(Office o1,Office o2){
        Office temp = o1;
        o1 = o2;
        o2 = temp;
    }
}
