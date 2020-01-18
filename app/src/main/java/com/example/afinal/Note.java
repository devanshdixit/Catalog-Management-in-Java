package com.example.afinal;

public class Note {
    private String Property_Type, referencefrom, referenceType;
    private String Bhk, Floor, Parking;
    private int bhkid, floorid, parkingid;
    private String avail;
    private String rentAmount;
    private String Deposit;
    private String carpetArea;
    private String ownerAgentName;
    private String propertyAddress;
    private String RoomType;
    private String MobileNo;
    private String tenantType;


    Note(String property_Type, String referencefrom, String referenceType, String bhk, String floor, String parking, int bhkid, int floorid, int parkingid, String avail, String rentAmount, String deposit, String carpetArea, String ownerAgentName, String propertyAddress, String roomType, String mobileNo, String tenantType) {
        Property_Type = property_Type;
        this.referencefrom = referencefrom;
        this.referenceType = referenceType;
        Bhk = bhk;
        Floor = floor;
        Parking = parking;
        this.bhkid = bhkid;
        this.floorid = floorid;
        this.parkingid = parkingid;
        this.avail = avail;
        this.rentAmount = rentAmount;
        Deposit = deposit;
        this.carpetArea = carpetArea;
        this.ownerAgentName = ownerAgentName;
        this.propertyAddress = propertyAddress;
        RoomType = roomType;
        MobileNo = mobileNo;
        this.tenantType = tenantType;
    }


    public Note() {
    }

    public String getProperty_Type() {
        return Property_Type;
    }

    public void setProperty_Type(String property_Type) {
        Property_Type = property_Type;
    }

    public String getReferencefrom() {
        return referencefrom;
    }

    public void setReferencefrom(String referencefrom) {
        this.referencefrom = referencefrom;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getBhk() {
        return Bhk;
    }

    public void setBhk(String bhk) {
        Bhk = bhk;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getParking() {
        return Parking;
    }

    public void setParking(String parking) {
        Parking = parking;
    }

    public int getBhkid() {
        return bhkid;
    }

    public void setBhkid(int bhkid) {
        this.bhkid = bhkid;
    }

    public int getFloorid() {
        return floorid;
    }

    public void setFloorid(int floorid) {
        this.floorid = floorid;
    }

    public int getParkingid() {
        return parkingid;
    }

    public void setParkingid(int parkingid) {
        this.parkingid = parkingid;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public String getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(String rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getDeposit() {
        return Deposit;
    }

    public void setDeposit(String deposit) {
        Deposit = deposit;
    }

    public String getCarpetArea() {
        return carpetArea;
    }

    public void setCarpetArea(String carpetArea) {
        this.carpetArea = carpetArea;
    }

    public String getOwnerAgentName() {
        return ownerAgentName;
    }

    public void setOwnerAgentName(String ownerAgentName) {
        this.ownerAgentName = ownerAgentName;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }
}