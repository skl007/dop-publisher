package com.unicom.dop.doppublisher.entity;

import lombok.Data;

import java.util.Date;
@Data
public class OdsBacknetEquipPlan{
    private String id;

    private String mouthplanId;

    private String areaCity;

    private String eqpGrid;

    private String specialty;

    private String netlevel;

    private String equipType;

    private String equipFactory;

    private String backnetType;

    private String isBs;

    private String isUserMachroom;

    private String nename;

    private String neType;

    private String boardName;

    private String boardType;

    private Date netTime;

    private String backnetReason;

    private String isUsed;

    private String isInvest;

    private String isInvestWorkable;

    private String planBacknetTime;

    private String realityBacknetTime;

    private String standardCurrent;

    private String powerNumber;

    private String beforeBacknetCurrent;

    private String afterBacknetCurrent;

    private String isBuyMaintenance;

    private String saveMoney;

    private String remarks;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Double electricityPrice;
}