package com.unicom.dop.doppublisher.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OdsBacknetEquipPlanTest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    @Excel(name = "计划编号")
    private String planNum;
    @Excel(name = "计划名称")
    private String planName;
    @Excel(name = "计划年月")
    private String planTime;

    @Excel(name = "地市")
    private String areaCity;
    @Excel(name = "设备类型")
    private String equipType;
    @Excel(name = "设备厂家")
    private String equipFactory;
    @Excel(name = "退网类型")
    private String backnetType;
    @Excel(name = "是否基站",replace = {"否_0","是_1"})
    private String isBs;
    @Excel(name = "是否用户机房",replace = {"否_0","是_1"})
    private String isUserMachroom;
    @Excel(name = "网元名称")
    private String neName;
    @Excel(name = "网元类型（主控类型）")
    private String neType;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "网管上网元创建时间",format = "yyyy-MM-dd",importFormat = "yyyy-MM-dd",databaseFormat = "yyyy-MM-dd")
    private Date netTime;
    @Excel(name = "2012年以后上线的设备退网原因")
    private String backnetReason;
    @Excel(name = "是否要利旧",replace = {"否_0","是_1"})
    private String isUsed;
    @Excel(name = "是否要需要投资",replace = {"否_0","是_1"})
    private String isInvest;
    @Excel(name = "是否已落实投资",replace = {"否_0","是_1"})
    private String isInvestWorkable;
    @Excel(name = "计划退网时间",format = "yyyy-MM-dd")
    private String planBacknetTime;
    @Excel(name = "电费单价（元）")
    private Double electricityPrice;
    @Excel(name = "网管功耗读数（W）")
    private String powerNumber;
    @Excel(name = "退网前卡表功耗（W）")
    private String beforeBacknetCurrent;
    @Excel(name = "退网后卡表功耗（W）")
    private String afterBacknetCurrent;
    @Excel(name = "是否购买维保",replace = {"否_0","是_1"})
    private String isBuyMaintenance;
    @Excel(name = "维保节省费（元）")
    private String saveMoney;
    @Excel(name = "创建人")
    private String createBy;
    @Excel(name = "备注")

    private String remarks;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date insertTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

//    /**
//     * 判断该对象是否为空
//     * @return
//     */
//    public boolean isNullObject(){
//        return this.areaCity == null;
//    }
}