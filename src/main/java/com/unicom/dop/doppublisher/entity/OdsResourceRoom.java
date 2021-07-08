package com.unicom.dop.doppublisher.entity;

import java.util.Date;

public class OdsResourceRoom {
    private String roomSid;

    private String roomSname;

    private String cityCode;

    private String areaCode;

    private String roomType;

    private String roomSlvl;

    private String propertyType;

    private String propertyUnit;

    private String gridId;

    private String gridName;

    private String stationSid;

    private String stationName;

    private Date insertdbTime;

    private Date updateTime;

    public OdsResourceRoom(String roomSid, String roomSname, String cityCode, String areaCode, String roomType, String roomSlvl, String propertyType, String propertyUnit, String gridId, String gridName, String stationSid, String stationName, Date insertdbTime, Date updateTime) {
        this.roomSid = roomSid;
        this.roomSname = roomSname;
        this.cityCode = cityCode;
        this.areaCode = areaCode;
        this.roomType = roomType;
        this.roomSlvl = roomSlvl;
        this.propertyType = propertyType;
        this.propertyUnit = propertyUnit;
        this.gridId = gridId;
        this.gridName = gridName;
        this.stationSid = stationSid;
        this.stationName = stationName;
        this.insertdbTime = insertdbTime;
        this.updateTime = updateTime;
    }

    public OdsResourceRoom() {
        super();
    }

    public String getRoomSid() {
        return roomSid;
    }

    public void setRoomSid(String roomSid) {
        this.roomSid = roomSid == null ? null : roomSid.trim();
    }

    public String getRoomSname() {
        return roomSname;
    }

    public void setRoomSname(String roomSname) {
        this.roomSname = roomSname == null ? null : roomSname.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    public String getRoomSlvl() {
        return roomSlvl;
    }

    public void setRoomSlvl(String roomSlvl) {
        this.roomSlvl = roomSlvl == null ? null : roomSlvl.trim();
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType == null ? null : propertyType.trim();
    }

    public String getPropertyUnit() {
        return propertyUnit;
    }

    public void setPropertyUnit(String propertyUnit) {
        this.propertyUnit = propertyUnit == null ? null : propertyUnit.trim();
    }

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId == null ? null : gridId.trim();
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName == null ? null : gridName.trim();
    }

    public String getStationSid() {
        return stationSid;
    }

    public void setStationSid(String stationSid) {
        this.stationSid = stationSid == null ? null : stationSid.trim();
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public Date getInsertdbTime() {
        return insertdbTime;
    }

    public void setInsertdbTime(Date insertdbTime) {
        this.insertdbTime = insertdbTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}