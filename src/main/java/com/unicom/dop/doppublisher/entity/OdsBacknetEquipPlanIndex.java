package com.unicom.dop.doppublisher.entity;

import lombok.Data;

import java.util.Date;
@Data
public class OdsBacknetEquipPlanIndex {
    private String planNum;

    private String planName;

    private String areaCity;

    private String profession;

    private String planBacknetTime;

    private Date insertTime;

    private Date updateTime;

    private String backNetEquipNumber;

    private String planSaveCosts;

}