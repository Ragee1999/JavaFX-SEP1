package com.example.javafxSEP.Java_files;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class OtherRoom extends Room{
private int powerOutlets;
private boolean utilityRoom;
private boolean greyWaterDrainage;
private boolean blackWaterDrainage;
private int waterConnection;

    public OtherRoom(String roomID, String roomName, int powerOutlets, boolean greyWaterDrainage, boolean blackWaterDrainage) {
        super(roomID, roomName);
        this.powerOutlets = powerOutlets;
        this.greyWaterDrainage = greyWaterDrainage;
        this.blackWaterDrainage = blackWaterDrainage;
    }

    //minimal Constructor for internal system use in JSON reader
    public OtherRoom() {
        super("1", "TemplateOtherRoom");
    }

    public int getPowerOutlets() {
        return powerOutlets;
    }

    public boolean isUtilityRoom() {
        return utilityRoom;
    }

    public boolean isGreyWaterDrainage() {
        return greyWaterDrainage;
    }

    public boolean isBlackWaterDrainage() {
        return blackWaterDrainage;
    }

    public int getWaterConnection() {
        return waterConnection;
    }

    public void setPowerOutlets(int powerOutlets) {
        this.powerOutlets = powerOutlets;
    }

    public void setUtilityRoom(boolean utilityRoom) {
        this.utilityRoom = utilityRoom;
    }

    public void setGreyWaterDrainage(boolean greyWaterDrainage) {
        this.greyWaterDrainage = greyWaterDrainage;
    }

    public void setBlackWaterDrainage(boolean blackWaterDrainage) {
        this.blackWaterDrainage = blackWaterDrainage;
    }

    public void setWaterConnection(int waterConnection) {
        this.waterConnection = waterConnection;
    }

    public OtherRoom copy(){
        OtherRoom or1 = new OtherRoom(this.getRoomID(),this.getRoomName(),this.powerOutlets, this.greyWaterDrainage, this.blackWaterDrainage);
        or1.setUtilityRoom(this.utilityRoom);
        or1.setWaterConnection(this.waterConnection);
        return or1;
    }

    @Override
    public void infoFromJSON(String jsonText) throws JsonProcessingException {
        //create json object from json text
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonText);
        //
        String type = jsonNode.get("roomType").asText();
        if(!(type.equalsIgnoreCase("office"))){
            throw new IllegalArgumentException("roomType is different from 'Office'");
        }
        setRoomID(jsonNode.get("roomID").asText());
        setRoomName(jsonNode.get("roomName").asText());
        setSquareMeters(jsonNode.get("squareMeters").asInt());
        setPowerOutlets(jsonNode.get("powerOutlets").asInt());
        setUtilityRoom(jsonNode.get("utilityRoom").asBoolean());
        setGreyWaterDrainage(jsonNode.get("greyWaterDrainage").asBoolean());
        setBlackWaterDrainage(jsonNode.get("blackWaterDrainage").asBoolean());
        setWaterConnection(jsonNode.get("waterConnections").asInt());
    }

    public static void swap(OtherRoom or1,OtherRoom or2){
        OtherRoom temp = or1;
        or1 = or2;
        or2 = temp;
    }

}
