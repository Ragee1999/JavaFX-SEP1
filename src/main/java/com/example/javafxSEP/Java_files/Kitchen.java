package com.example.javafxSEP.Java_files;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import static com.example.javafxSEP.TestClasses.FileReader.objectMapper;

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
        if(sinks<0){
            throw new IllegalArgumentException("sinks Value under 0 not valid");
        }
        this.sinks = sinks;
    }

    public void setNumberOfStoves(int numberOfStoves) {
        if(numberOfStoves<0){
            throw new IllegalArgumentException("numberOfStoves Value under 0 not valid");
        }
        this.numberOfStoves = numberOfStoves;
    }

    public void setCabinets(int cabinets) {
        if(cabinets<0){
            throw new IllegalArgumentException("cabinets Value under 0 not valid");
        }
        this.cabinets = cabinets;
    }

    public void setDishwasherIncluded(boolean dishwasherIncluded) {this.dishwasherIncluded = dishwasherIncluded;
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

    @Override
    public void infoFromJSON(String jsonText) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonText);
        String type = jsonNode.get("roomType").asText();
        if(!(type.equalsIgnoreCase("kitchen"))){
            throw new IllegalArgumentException("roomType is different from 'Kitchen'");
        }
        setRoomID(jsonNode.get("roomID").asText());
        setRoomName(jsonNode.get("roomName").asText());
        setSquareMeters(jsonNode.get("squareMeters").asInt());
        setSinks(jsonNode.get("sinks").asInt());
        setNumberOfStoves(jsonNode.get("numberOfStoves").asInt());
        setCabinets(jsonNode.get("cabinets").asInt());
        setDishwasherIncluded(jsonNode.get("dishwasher").asBoolean());
        setInductionStove(jsonNode.get("isInduction").asBoolean());
    }

    public static void swap(Kitchen k1,Kitchen k2){
        Kitchen temp = k1;
        k1 = k2;
        k2 = temp;
    }
}
