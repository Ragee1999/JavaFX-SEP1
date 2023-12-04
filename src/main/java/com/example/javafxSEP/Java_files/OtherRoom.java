package com.example.javafxSEP.Java_files;

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

    public OtherRoom(String roomID, String roomName) {
        super(roomID, roomName);
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

    public static void swap(OtherRoom or1,OtherRoom or2){
        OtherRoom temp = or1;
        or1 = or2;
        or2 = temp;
    }

}
