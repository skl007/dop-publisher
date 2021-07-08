package com.unicom.dop.doppublisher.entity;

import java.util.Date;

public class OdsBacknetEquipPlanIndex {
    private String planNum;

    private String planName;

    private String areaCity;

    private String profession;

    private Date insertTime;

    private Date updateTime;

    public OdsBacknetEquipPlanIndex(String planNum, String planName, String areaCity, String profession, Date insertTime, Date updateTime) {
        this.planNum = planNum;
        this.planName = planName;
        this.areaCity = areaCity;
        this.profession = profession;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
    }

    public OdsBacknetEquipPlanIndex() {
        super();
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum == null ? null : planNum.trim();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public String getAreaCity() {
        return areaCity;
    }

    public void setAreaCity(String areaCity) {
        this.areaCity = areaCity == null ? null : areaCity.trim();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}