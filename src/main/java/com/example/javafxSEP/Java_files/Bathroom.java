package com.example.javafxSEP.Java_files;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Bathroom extends Room{
    private int sinks=0;
    private int toilets=0;
    private boolean bidet;
    private int bathtubs=0;

    private int showers;

    //Constructor for user interface
    public Bathroom(String roomID, String roomName, int sinks, int toilets) {
        super(roomID, roomName);
        this.sinks = sinks;
        this.toilets = toilets;
    }

    //minimal Constructor for internal system use in JSON reader
    public Bathroom() {
        super("1", "templateBathroom");
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

    public int getShowers() {
        return showers;
    }

    public void setSinks(int sinks) {
        //check for illegal values
        if(sinks<0){
            throw new IllegalArgumentException("Sink Value under 0 not valid");
        }
        this.sinks = sinks;
    }

    public void setToilets(int toilets) {
        //check for illegal values
        if(toilets<0){
            throw new IllegalArgumentException("toilets Value under 0 not valid");
        }
        this.toilets = toilets;
    }

    public void setBidet(boolean bidet) {
        this.bidet = bidet;
    }

    public void setBathtubs(int bathtubs) {
        //check for illegal values
        if(bathtubs<0){
            throw new IllegalArgumentException("bathtubs Value under 0 not valid");
        }
        this.bathtubs = bathtubs;
    }

    public void setShowers(int showers) {
        //check for illegal values
        if(showers<0){
            throw new IllegalArgumentException("showers Value under 0 not valid");
        }
        this.showers = showers;
    }

    public Bathroom copy(){
        Bathroom b1 = new Bathroom(this.getRoomID(), this.getRoomName(),this.getSinks(), this.getToilets());
        b1.setBidet(this.isBidet());
        b1.setBathtubs(this.getBathtubs());
        return b1;
    }

    @Override
    public void infoFromJSON(String jsonText) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonText);
        String type = jsonNode.get("roomType").asText();
        if(!(type.equalsIgnoreCase("bathroom"))){
            throw new IllegalArgumentException("roomType is different from 'bathroom'");
        }
        setRoomID(jsonNode.get("roomID").asText());
        setRoomName(jsonNode.get("roomName").asText());
        setSquareMeters(jsonNode.get("squareMeters").asInt());
        setSinks(jsonNode.get("sinks").asInt());
        setBidet(jsonNode.get("bidet").asBoolean());
        setToilets(jsonNode.get("toilets").asInt());
        setBathtubs(jsonNode.get("bathtubs").asInt());
        setShowers(jsonNode.get("shower").asInt());
    }

    public static void swap(Bathroom b1,Bathroom b2){
        Bathroom temp = b1;
        b1 = b2;
        b2 = temp;
    }

}
