package com.unicom.dop.doppublisher.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.unicom.dop.doppublisher.common.Result;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlan;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanIndex;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanTest;
import com.unicom.dop.doppublisher.mapper.*;

import com.unicom.dop.doppublisher.service.PublisherService;
import com.unicom.dop.doppublisher.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PublisherServiceImpl implements PublisherService {


    @Autowired
    DauEquipMapper dauEquipMapper;
    @Autowired
    private OdsBacknetEquipPlanMapper odsBacknetEquipPlanMapper;
    @Autowired
    private OdsBacknetEquipPlanTestMapper odsBacknetEquipPlanTestMapper;
    @Autowired
    private OdsBacknetEquipPlanIndexMapper odsBacknetEquipPlanIndexMapper;


    @Override
    public List<Map> getNationalTaskCount() {
        return dauEquipMapper.getNationalTaskCount();
    }

    @Override
//    public List<Map> getEquipPlanAnalyse(String area_city, String grid_name, String profession, String equip_status_new, String dt_month) {
    public Result getEquipPlanAnalyse(String planNum, String areaCity,
                                      String gridName, String profession,
                                      String equipStatusNew, String planYear, String planMonth,
                                      Integer pageNo,
                                      Integer pageSize,
                                      String neName) {

//        return dauEquipMapper.getEquipPlanAnalyse(area_city, grid_name, profession, equip_status_new, dt_month);
        JSONObject jsonObject = new JSONObject();
//        if (oConvertUtils.isEmpty(pageNo) || pageNo == 0) {
//            pageNo = 1;
//        }
//        if (oConvertUtils.isEmpty(pageSize) || pageSize == 0) {
//            pageSize = 10;
//        }
        int flag = 0;
        if (oConvertUtils.isNotEmpty(planMonth)) {
            flag = 1;
        } else if (oConvertUtils.isNotEmpty(planYear)) {
            flag = 2;
        }
        List<Map> equipPlanAnalyse = dauEquipMapper.getEquipPlanAnalyse(planNum, areaCity, gridName, profession, equipStatusNew, planYear, planMonth, pageNo, pageSize, flag,neName);
        Integer total = dauEquipMapper.getEquipPlanAnalyseCount(planNum, areaCity, gridName, profession, equipStatusNew, planYear, planMonth, flag,neName);
        jsonObject.put("total", total);
        ArrayList<Map> tmpArr = new ArrayList<>();
        Iterator<Map> iterator = equipPlanAnalyse.iterator();
        while (iterator.hasNext()) {
            Map obj = iterator.next();
            if (obj == null) {
                return Result.ok("没有数据");
            }
//            Object planNum;
//            Object areaCity;
//            Object gridName;
//            Object profession;
            Object backnetType;
//            Object neName;
            Object planBacknetTime;
            Object logdate;
            Object orderTime;
            Object realBacknetTime;
            Object planSaveCosts;
            Object realSaveCosts;
            Object realSaveElectricity;
            Object saveMoney;
            Object eqpSid;

            if (obj.get("planNum") == null) {
                planNum = "";
            } else {
                planNum = (String) obj.get("planNum");
            }
            if (obj.get("areaCity") == null) {
                areaCity = "";
            } else {
                areaCity = (String) obj.get("areaCity");
            }
            if (obj.get("gridName") == null) {
                gridName = "";
            } else {
                gridName = (String) obj.get("gridName");
            }
            if (obj.get("profession") == null) {
                profession = "";
            } else {
                profession = (String) obj.get("profession");
            }
            if (obj.get("backnetType") == null) {
                backnetType = "";
            } else {
                backnetType = (String) obj.get("backnetType");
            }
            if (obj.get("neName") == null) {
                neName = "";
            } else {
                neName = (String) obj.get("neName");
            }
            if (obj.get("planBacknetTime") == null) {
                planBacknetTime = "";
            } else {
                planBacknetTime = (String) obj.get("planBacknetTime");
            }
            if (obj.get("logdate") == null) {
                logdate = "";
            } else {
                logdate = (String) obj.get("logdate");
            }
            if (obj.get("orderTime") == null) {
                orderTime = "";
            } else {
                orderTime = (String) obj.get("orderTime");
            }
            if (obj.get("realBacknetTime") == null) {
                realBacknetTime = "";
            } else {
                realBacknetTime = (String) obj.get("realBacknetTime");
            }
            if (obj.get("equipStatusNew") == null) {
                equipStatusNew = "";
            } else {
                equipStatusNew = (String) obj.get("equipStatusNew");
            }
            if (obj.get("planSaveCosts") == null) {
                planSaveCosts = "";
            } else {
                planSaveCosts = (Double) obj.get("planSaveCosts");
            }
            if (obj.get("realSaveCosts") == null) {
                realSaveCosts = "";
            } else {
                realSaveCosts = (Double) obj.get("realSaveCosts");
            }
            if (obj.get("realSaveElectricity") == null) {
                realSaveElectricity = "";
            } else {
                realSaveElectricity = (Double) obj.get("realSaveElectricity");
            }
            if (obj.get("saveMoney") == null) {
                saveMoney = "";
            } else {
                saveMoney = (String) obj.get("saveMoney");
            }
            if (obj.get("eqpSid") == null) {
                eqpSid = "";
            } else {
                eqpSid = (String) obj.get("eqpSid");
            }

            Map model = new HashMap();
            model.put("计划编号", planNum);
            model.put("地市", areaCity);
            model.put("网格", gridName);
            model.put("专业", profession);
            model.put("退网类型", backnetType);
            model.put("网元名称", neName);
            model.put("计划退网时间", planBacknetTime);
            model.put("网管系统退网时间", logdate);
            model.put("资源系统退网时间", orderTime);
            model.put("实际退网时间", realBacknetTime);
            model.put("当前退网状态", equipStatusNew);
            model.put("计划节省成本/元", planSaveCosts);
            model.put("实际节省成本/元", realSaveCosts);
            model.put("节省电费/元", realSaveElectricity);
            model.put("节省维保费/元", saveMoney);
            model.put("eqpSid", eqpSid);

            tmpArr.add(model);
        }
        jsonObject.put("list", tmpArr);
        return Result.ok(jsonObject);
    }

    @Override
    public Result getEquipPlanAnalyseDetial(String neName, String eqpSid, String areaCity) {
//        return dauEquipMapper.getEquipPlanAnalyseDetial(neName);
        JSONObject jsonObject = new JSONObject();
        if ((oConvertUtils.isEmpty(neName) && oConvertUtils.isEmpty(areaCity))) {
            if (oConvertUtils.isEmpty(eqpSid)) {
                return Result.error("缺少参数，请联系管理员！");
            }
        }
        List<Map> equipPlanAnalyseDetial = dauEquipMapper.getEquipPlanAnalyseDetial(neName, eqpSid, areaCity);
//        Integer total = dauEquipMapper.getEquipPlanAnalyseDetialCount(neName);
        ArrayList<Map> tmpArr = new ArrayList<>();
        Iterator<Map> iterator = equipPlanAnalyseDetial.iterator();
        while (iterator.hasNext()) {
            Map obj = iterator.next();
            if (obj == null) {
                return Result.ok("数据为空！");
            }
            Object planNum;
            Object planName;
//            Object areaCity;
            Object gridName;
            Object profession;
            Object roomSname;
            Object eqpType;
            Object reslvl;
            Object backnetType;
            Object equipFactory;
            Object isUserMachroom;
            Object isBs;
            Object neType;
//            Object neName;
            Object backnetReason;
            Object netTime;
            Object isBuyMaintenance;
            Object isUsed;
            Object eqpSidRatedPower;
            Object isInvest;
            Object powerNumber;
            Object planBacknetTime;
            Object logDate;
            Object orderTime;
            Object realBacknetTime;
            Object logtype;
            Object status;
            Object equipStatusNew;
            Object realEquipStatus;
            Object realSaveCosts;
            Object saveMoney;
            Object realSaveElectricity;

            if (obj.get("planNum") == null) {
                planNum = "";
            } else {
                planNum = (String) obj.get("planNum");
            }
            if (obj.get("planName") == null) {
                planName = "";
            } else {
                planName = (String) obj.get("planName");
            }
            if (obj.get("areaCity") == null) {
                areaCity = "";
            } else {
                areaCity = (String) obj.get("areaCity");
            }
            if (obj.get("gridName") == null) {
                gridName = "";
            } else {
                gridName = (String) obj.get("gridName");
            }
            if (obj.get("profession") == null) {
                profession = "";
            } else {
                profession = (String) obj.get("profession");
            }
            if (obj.get("roomSname") == null) {
                roomSname = "";
            } else {
                roomSname = (String) obj.get("roomSname");
            }
            if (obj.get("eqpType") == null) {
                eqpType = "";
            } else {
                eqpType = (String) obj.get("eqpType");
            }
            if (obj.get("reslvl") == null) {
                reslvl = "";
            } else {
                reslvl = (String) obj.get("reslvl");
            }
            if (obj.get("backnetType") == null) {
                backnetType = "";
            } else {
                backnetType = (String) obj.get("backnetType");
            }
            if (obj.get("equipFactory") == null) {
                equipFactory = "";
            } else {
                equipFactory = (String) obj.get("equipFactory");
            }
            if (obj.get("isUserMachroom") == null) {
                isUserMachroom = "";
            } else {
                isUserMachroom = (String) obj.get("isUserMachroom");
            }
            if (obj.get("isBs") == null) {
                isBs = "";
            } else {
                isBs = (String) obj.get("isBs");
            }
            if (obj.get("neType") == null) {
                neType = "";
            } else {
                neType = (String) obj.get("neType");
            }
            if (obj.get("neName") == null) {
                neName = "";
            } else {
                neName = (String) obj.get("neName");
            }
            if (obj.get("backnetReason") == null) {
                backnetReason = "";
            } else {
                backnetReason = (String) obj.get("backnetReason");
            }
            if (obj.get("netTime") == null) {
                netTime = "";
            } else {
                netTime = (String) obj.get("netTime");
            }
            if (obj.get("isBuyMaintenance") == null) {
                isBuyMaintenance = "";
            } else {
                isBuyMaintenance = (String) obj.get("isBuyMaintenance");
            }
            if (obj.get("isUsed") == null) {
                isUsed = "";
            } else {
                isUsed = (String) obj.get("isUsed");
            }
            if (obj.get("eqpSidRatedPower") == null) {
                eqpSidRatedPower = "";
            } else {
                eqpSidRatedPower = (Double) obj.get("eqpSidRatedPower");
            }
            if (obj.get("isInvest") == null) {
                isInvest = "";
            } else {
                isInvest = (String) obj.get("isInvest");
            }
            if (obj.get("powerNumber") == null) {
                powerNumber = "";
            } else {
                powerNumber = obj.get("powerNumber");
            }
            if (obj.get("planBacknetTime") == null) {
                planBacknetTime = "";
            } else {
                planBacknetTime = (String) obj.get("planBacknetTime");
            }
            if (obj.get("logDate") == null) {
                logDate = "";
            } else {
                logDate = (String) obj.get("logDate");
            }
            if (obj.get("orderTime") == null) {
                orderTime = "";
            } else {
                orderTime = (String) obj.get("orderTime");
            }
            if (obj.get("realBacknetTime") == null) {
                realBacknetTime = "";
            } else {
                realBacknetTime = (String) obj.get("realBacknetTime");
            }
            if (obj.get("logtype") == null) {
                logtype = "";
            } else {
                logtype = (String) obj.get("logtype");
            }
            if (obj.get("status") == null) {
                status = "";
            } else {
                status = (String) obj.get("status");
            }
            if (obj.get("equipStatusNew") == null) {
                equipStatusNew = "";
            } else {
                equipStatusNew = (String) obj.get("equipStatusNew");
            }
            if (obj.get("realEquipStatus") == null) {
                realEquipStatus = "";
            } else {
                realEquipStatus = (String) obj.get("realEquipStatus");
            }
            if (obj.get("realSaveCosts") == null) {
                realSaveCosts = "";
            } else {
                realSaveCosts = (Double) obj.get("realSaveCosts");
            }
            if (obj.get("saveMoney") == null) {
                saveMoney = "";
            } else {
                saveMoney = (String) obj.get("saveMoney");
            }
            if (obj.get("realSaveElectricity") == null) {
                realSaveElectricity = "";
            } else {
                realSaveElectricity = (Double) obj.get("realSaveElectricity");
            }

            Map model = new HashMap();
            model.put("计划编号", planNum);
            model.put("计划名称", planName);
            model.put("地市", areaCity);
            model.put("设备所在网格", gridName);
            model.put("专业", profession);
            model.put("设备所在机房", roomSname);
            model.put("设备类型", eqpType);
            model.put("网络层级", reslvl);
            model.put("退网类型", backnetType);
            model.put("设备厂家", equipFactory);
            model.put("是否用户机房", isUserMachroom);
            model.put("是否基站", isBs);
            model.put("网元类型", neType);
            model.put("网元名称", neName);
            model.put("退网原因", backnetReason);
            model.put("网管上网元创建时间", netTime);
            model.put("是否购买维保", isBuyMaintenance);
            model.put("是否要利旧", isUsed);
            model.put("标称功耗", eqpSidRatedPower);
            model.put("是否需要投资", isInvest);
            model.put("网管功耗读数", powerNumber);
            model.put("计划退网时间", planBacknetTime);
            model.put("网管系统退网时间", logDate);
            model.put("资源系统退网时间", orderTime);
            model.put("实际退网时间", realBacknetTime);
            model.put("网管系统退网状态", logtype);
            model.put("资源系统设备状态", status);
            model.put("实际退网状态", equipStatusNew);
            model.put("退网设备状态", realEquipStatus);
            model.put("实际年度节省成本", realSaveCosts);
            model.put("实际年度节省维保费", saveMoney);
            model.put("实际年度节省电费", realSaveElectricity);

            tmpArr.add(model);
        }
        jsonObject.put("list", tmpArr);
        return Result.ok(jsonObject);
    }

    /**
     * 新增退网设备
     *
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addBackNet(JSONObject jsonObject) {
        try {
            List<OdsBacknetEquipPlanTest> odsBacknetEquipPlanList = jsonObject.getJSONArray("OdsBacknetEquipPlanList")
                    .toJavaList(OdsBacknetEquipPlanTest.class);
//            OdsBacknetEquipPlanIndex odsBacknetEquipPlanIndex = jsonObject.getJSONObject("OdsBacknetEquipPlanIndex")
//                    .toJavaObject(OdsBacknetEquipPlanIndex.class);
            if (oConvertUtils.listIsEmpty(odsBacknetEquipPlanList)) {
                return Result.error("新增失败，缺少参数");
            }
            String regex = "^[0-9]*$";
            for (OdsBacknetEquipPlanTest o : odsBacknetEquipPlanList) {
                String planNum = o.getPlanNum();
                if (oConvertUtils.isEmpty(planNum)){
                    return Result.error("计划编号不能为空！");
                }
                String planName = o.getPlanName();
                if (oConvertUtils.isEmpty(planName)){
                    return Result.error("计划名称不能为空！");
                }
                String planTime = o.getPlanTime();
                if (oConvertUtils.isEmpty(planTime)){
                    return Result.error("计划时间不能为空！");
                }
                String areaCity = o.getAreaCity();
                if (oConvertUtils.isEmpty(areaCity)) {
                    return Result.error("地市字段不能为空！");
                }
                String equipType = o.getEquipType();
                if (oConvertUtils.isEmpty(equipType)) {
                    return Result.error("设备类型字段不能为空！");
                }
                String equipFactory = o.getEquipFactory();
                if (oConvertUtils.isEmpty(equipFactory)) {
                    return Result.error("设备厂家字段不能为空！");
                }
                String backnetType = o.getBacknetType();
                if (oConvertUtils.isEmpty(backnetType)) {
                    return Result.error("退网类型字段不能为空！");
                }
                String neName = o.getNeName();
                if (oConvertUtils.isEmpty(neName)) {
                    return Result.error("网元名称字段不能为空！");
                }
                String neType = o.getNeType();
                if (oConvertUtils.isEmpty(neType)) {
                    return Result.error("网元类型字段不能为空！");
                }
                String planBacknetTime = o.getPlanBacknetTime();
                if (oConvertUtils.isEmpty(planBacknetTime)) {
                    return Result.error("计划退网时间字段不能为空！");
                }
                if (!isLegalDate(planBacknetTime.length(), planBacknetTime, "yyyy-MM-dd")) {
                    System.out.println(planBacknetTime);
                    System.out.println(areaCity);
                    System.out.println(o.getIsBuyMaintenance());
                    return Result.error("计划退网时间字段格式不正确，格式如：2021-01-01");
                }
                Double electricityPrice = o.getElectricityPrice();
                if (oConvertUtils.isEmpty(electricityPrice) || electricityPrice == 0) {
                    return Result.error("电费单价字段不能为空或是0！");
                }
                String isBuyMaintenance = o.getIsBuyMaintenance();
                if (oConvertUtils.isEmpty(isBuyMaintenance)) {
                    return Result.error("是否购买维保字段不能为空！");
                }
                String saveMoney = o.getSaveMoney();
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(saveMoney);
                if (!matcher.matches()) {
                    return Result.error("节省维保费字段值不能为非数字");
                }
                if (oConvertUtils.isEmpty(saveMoney) ) {
                    return Result.error("节省维保费字段不能为空！");
                }
                String createBy = o.getCreateBy();
                if (oConvertUtils.isEmpty(createBy)) {
                    return Result.error("创建人字段不能为空！");
                }
            }
//            if (true){
//                return Result.ok();
//            }
//            if (oConvertUtils.isEmpty(odsBacknetEquipPlanIndex)){
//                return Result.error("新增失败,缺少参数");
//            }
//            int insert = odsBacknetEquipPlanIndexMapper.insert(odsBacknetEquipPlanIndex);
//            if (insert<1){
//                return Result.error("新增失败");
//            }
            Integer i = odsBacknetEquipPlanTestMapper.insertAll(odsBacknetEquipPlanList);
            if (i < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.error("新增失败");
            }
            return Result.ok("新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("新增失败,请查看参数格式是否正确或检查是否有重复新增的设备！");
        }
    }

    /**
     * 获取退网信息
     *
     * @param pageNo
     * @param pageSize
     * @param planNum
     * @param planName
     * @return
     */
    @Override
    public Result getBackNet(Integer pageNo, Integer pageSize,
                             String planNum, String planName,
                             String equipType,
                             String equipFactory, String neName) {
//   计划编号
        if (oConvertUtils.isEmpty(planNum)) {
            return Result.error("计划编号为空！");
        }
        //计划名称
        if (oConvertUtils.isEmpty(planName)) {
            return Result.error("计划名称为空！");
        }
        List<OdsBacknetEquipPlanTest> list = odsBacknetEquipPlanTestMapper.selectBackNetByPlanNumAndPlanName(planNum, planName,
                pageNo, pageSize, equipType, equipFactory, neName);
        Integer total = odsBacknetEquipPlanTestMapper.countAll(planNum, planName, equipType, equipFactory, neName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", list);
        jsonObject.put("total", total);
        return Result.ok(jsonObject);
    }

    /**
     * 修改退网设备信息
     *
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateBackNet(JSONObject jsonObject) {
        try {
            OdsBacknetEquipPlanTest odsBacknetEquipPlan = jsonObject.getJSONObject("OdsBacknetEquipPlan")
                    .toJavaObject(OdsBacknetEquipPlanTest.class);
//            String planTime = odsBacknetEquipPlanTest.getPlanTime();
//            String planBacknetTime = odsBacknetEquipPlanTest.getPlanBacknetTime();
//            if (oConvertUtils.isEmpty(planBacknetTime)||!(planBacknetTime.length()==10)){
//                System.out.println(planBacknetTime);
//                System.out.println(planBacknetTime.length());
//                return Result.error("计划时间格式不正确或缺少参数！");
//            }
//            LocalDate date=LocalDate.now();
//            boolean before = date.isBefore(LocalDate.parse(planBacknetTime));
//            if (!before){
//                System.out.println(false);
//                return Result.error("该计划不可修改，当前时间晚于计划时间！");
//            }
//            if (before){
//                return Result.ok("可以修改");
//            }

            //后台校验字段
//            String regex = "^[0-9]*$";
            String regex = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
            String areaCity = odsBacknetEquipPlan.getAreaCity();
            if (oConvertUtils.isEmpty(areaCity)) {
                return Result.error("地市字段不能为空！");
            }
            String equipType = odsBacknetEquipPlan.getEquipType();
            if (oConvertUtils.isEmpty(equipType)) {
                return Result.error("设备类型字段不能为空！");
            }
            String equipFactory = odsBacknetEquipPlan.getEquipFactory();
            if (oConvertUtils.isEmpty(equipFactory)) {
                return Result.error("设备厂家字段不能为空！");
            }
            String backnetType = odsBacknetEquipPlan.getBacknetType();
            if (oConvertUtils.isEmpty(backnetType)) {
                return Result.error("退网类型字段不能为空！");
            }
            String neName = odsBacknetEquipPlan.getNeName();
            if (oConvertUtils.isEmpty(neName)) {
                return Result.error("网元名称字段不能为空！");
            }
            String neType = odsBacknetEquipPlan.getNeType();
            if (oConvertUtils.isEmpty(neType)) {
                return Result.error("网元类型字段不能为空！");
            }
            String planBacknetTime = odsBacknetEquipPlan.getPlanBacknetTime();
            if (oConvertUtils.isEmpty(planBacknetTime)) {
                return Result.error("计划退网时间字段不能为空！");
            }
            if (!isLegalDate(planBacknetTime.length(), planBacknetTime, "yyyy-MM-dd")) {
                System.out.println(planBacknetTime);
                System.out.println(areaCity);
                System.out.println(odsBacknetEquipPlan.getIsBuyMaintenance());
                return Result.error("计划退网时间字段格式不正确，格式如：2021-01-01");
            }
            Double electricityPrice = odsBacknetEquipPlan.getElectricityPrice();
            if (oConvertUtils.isEmpty(electricityPrice) || electricityPrice == 0) {
                return Result.error("电费单价字段不能为空！");
            }
            String isBuyMaintenance = odsBacknetEquipPlan.getIsBuyMaintenance();
            if (oConvertUtils.isEmpty(isBuyMaintenance)) {
                return Result.error("是否购买维保字段不能为空！");
            }
            String saveMoney = odsBacknetEquipPlan.getSaveMoney();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(saveMoney);
            if (!matcher.matches()) {
                return Result.error("节省维保费字段值不能为非数字");
            }
            if (oConvertUtils.isEmpty(saveMoney)) {
                return Result.error("节省维保费字段不能为空！");
            }
            String createBy = odsBacknetEquipPlan.getCreateBy();
            if (oConvertUtils.isEmpty(createBy)) {
                return Result.error("创建人字段不能为空！");
            }
            Integer i = odsBacknetEquipPlanTestMapper.updateByPrimaryKeySelective(odsBacknetEquipPlan);
            if (i < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.error("修改信息失败");
            }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("修改失败");
        }
    }

    /**
     * 删除（实删）退网设备
     *
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteBackNet(JSONObject jsonObject) {
        try {
            String areaCity = jsonObject.getString("areaCity");
            if (oConvertUtils.isEmpty(areaCity)) {
                return Result.error("缺少地市参数");
            }
            String neName = jsonObject.getString("neName");
            if (oConvertUtils.isEmpty(neName)) {
                return Result.error("缺少网元名称参数");
            }
            String planTime = jsonObject.getString("planTime");
            if (oConvertUtils.isEmpty(planTime) || planTime.length() > 7) {
                System.out.println(planTime);
                return Result.error("缺少计划时间参数或参数格式不正确！");
            }
            LocalDate date = LocalDate.now();
            boolean before = date.isBefore(LocalDate.parse(planTime + "-01"));
            if (!before) {
                System.out.println(false);
                return Result.error("该计划不可删除，当前时间晚于计划时间！");
            }
//            if (before){
//                return Result.ok("可以删除");
//            }
            Integer i = odsBacknetEquipPlanTestMapper.deleteByPrimaryKey(areaCity, neName);
            if (i < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.error("删除失败！");
            }
            return Result.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("删除失败");
        }
    }

    /**
     * 校验计划编号是否重复
     *
     * @param request
     * @return
     */
    @Override
    public Result verifyBackNet(HttpServletRequest request) {
        String planNum = request.getParameter("planNum");
        if (oConvertUtils.isEmpty(planNum)) {
            return Result.error("计划编号不能为空！");
        }
//        OdsBacknetEquipPlanIndex odsBacknetEquipPlanIndex = odsBacknetEquipPlanIndexMapper.selectByPrimaryKey(planNum);
        Integer i = odsBacknetEquipPlanTestMapper.countAll(planNum, "", "", "", "");
        if (oConvertUtils.isEmpty(i) || i == 0) {
            return Result.ok();
        }
        return Result.error("计划已存在！");
    }

    /**
     * 分页获取所有退网计划
     *
     * @param pageNo
     * @param pageSize
     * @param planNum
     * @param areaCity
     * @param profession
     * @return
     */
    @Override
    public Result selectAllPlan(Integer pageNo, Integer pageSize,
                                String planNum, String planName,
                                String areaCity, String profession,
                                String planYear, String planMonth) {
        JSONObject jsonObject = new JSONObject();
        int flag = 0;
        if (oConvertUtils.isNotEmpty(planMonth)) {
            flag = 1;
        } else if (oConvertUtils.isNotEmpty(planYear)) {
            flag = 2;
        }
        List<Map<String, Object>> list = odsBacknetEquipPlanTestMapper.selectAllPlan(pageNo, pageSize, planNum, planName, areaCity, profession, planYear, planMonth, flag);
        if (oConvertUtils.listIsNotEmpty(list)) {
            Integer total = odsBacknetEquipPlanTestMapper.selectAllPlanCount(planNum, planName, areaCity, profession, planYear, planMonth, flag);
            jsonObject.put("total", total);
        }
        jsonObject.put("list", list);
        return Result.ok(jsonObject);
    }

    /**
     * 真实删除所有退网计划
     *
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteAllPlan(JSONObject jsonObject) {
        try {
            //获取计划编号
            String planNum = jsonObject.getString("planNum");
            if (oConvertUtils.isEmpty(planNum)) {
                return Result.error("计划编码不存在");
            }
            //获取计划名称
            String planName = jsonObject.getString("planName");
            if (oConvertUtils.isEmpty(planName)) {
                return Result.error("计划名称不存在");
            }
            String planTime = jsonObject.getString("planTime");
            if (oConvertUtils.isEmpty(planTime) || planTime.length() > 7) {
                System.out.println(planTime);
                return Result.error("缺少时间参数或参数格式不正确！");
            }
            LocalDate date = LocalDate.now();
            System.out.println(planTime.length());
            boolean before = date.isBefore(LocalDate.parse(planTime + "-01"));
            if (!before) {
                System.out.println(false);
                return Result.error("该计划不可删除，当前时间晚于计划时间！");
            }

//            Integer backNetEquipNumber = jsonObject.getInteger("backNetEquipNumber");
//            if (oConvertUtils.isEmpty(backNetEquipNumber) || backNetEquipNumber == 0) {
//                return Result.error("删除失败");
//            }
            Integer i1 = odsBacknetEquipPlanTestMapper.deleteByPlanNumAndPlanName(planNum, planName);
            Integer i2 = odsBacknetEquipPlanTestMapper.deleteDWDByPlanNumAndPlanName(planNum, planName);
//            if (!i.equals(backNetEquipNumber)) {
//            if (!(i1.equals(backNetEquipNumber)&&i2.equals(backNetEquipNumber))) {
//                System.out.println("backNetEquipNumber==========="+backNetEquipNumber);
//                System.out.println("i1==========="+i1);
//                System.out.println("i2==========="+i2);
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                return Result.error("删除失败！");
//            }
            if (i1 < 1 && i2 < 1) {
                return Result.error("删除失败！");
            }
            return Result.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("删除失败！");
        }
    }


    /**
     * 批量修改退网计划信息
     *
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateAllPlan(JSONObject jsonObject) {
        try {
            //获取计划编号
            String planNum = jsonObject.getString("planNum");
            if (oConvertUtils.isEmpty(planNum)) {
                return Result.error("计划编码不存在");
            }
            //新计划编号
            String nPlanNum = jsonObject.getString("nPlanNum");
            //新计划名称
            String nPlanName = jsonObject.getString("nPlanName");
            //新的计划年月
            String nPlanTime = jsonObject.getString("nPlanTime");
            if (oConvertUtils.isEmpty(nPlanName) || nPlanTime.length() > 7) {
                return Result.error("计划时间不可为空或参数格式不正确");
            }
            LocalDate date = LocalDate.now();
            boolean before = date.isBefore(LocalDate.parse(nPlanTime + "-01"));
            if (!before) {
                System.out.println(false);
                return Result.error("该计划不可修改，当前时间晚于计划时间！");
            }
//            if (before){
//                return Result.ok("可以修改");
//            }
//            //获取计划名称
//            String planName = jsonObject.getString("planName");
//            if (oConvertUtils.isEmpty(planName)){
//                return Result.error("计划名称不存在");
//            }
//            Integer backNetEquipNumber = jsonObject.getInteger("backNetEquipNumber");
//            System.out.println("backNetEquipNumber======" + backNetEquipNumber);
//            if (oConvertUtils.isEmpty(backNetEquipNumber) || backNetEquipNumber == 0) {
//                return Result.error("缺少参数，修改失败");
//            }
            Integer i = odsBacknetEquipPlanTestMapper.updateByPlanNumAndPlanName(planNum, nPlanNum, nPlanName, nPlanTime);
            System.out.println("i=======" + i);
//            if (!i.equals(backNetEquipNumber)) {
//                return Result.error("修改失败，请重试！");
//            }
            if (i < 1) {
                return Result.error("修改失败，请重试！");
            }
            return Result.ok("修改成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return Result.error("批量修改失败");
        }
    }

    @Override
    public Result getNumberData(String areaCity, String gridName, String profession, String planYear, String planMonth) {
        int flag = 0;
        if (oConvertUtils.isNotEmpty(planMonth)) {
            flag = 1;
        } else if (oConvertUtils.isNotEmpty(planYear)) {
            flag = 2;
        }
        List<Map> list = odsBacknetEquipPlanTestMapper.getNumberData(areaCity, gridName, profession, planYear, planMonth, flag);
        return Result.ok(list);
    }

    @Override
    public Result planMonitoring(String planYear) {
        if (oConvertUtils.isEmpty(planYear)) {
            planYear = String.valueOf(LocalDate.now().getYear());
        }
        List<Map> list = odsBacknetEquipPlanTestMapper.planMonitoring(planYear);
        return Result.ok(list);
    }

    @Override
    public Result planArea(String planYear) {
        if (oConvertUtils.isEmpty(planYear)) {
            planYear = String.valueOf(LocalDate.now().getYear());
        }
        List<Map> list = odsBacknetEquipPlanTestMapper.planArea(planYear);
        return Result.ok(list);
    }

    /**
     * 通过excel导入批量插入数据
     *
     * @param list
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
//    public Result importBackNetExcel(String planNum, String planName, String planTime, List<OdsBacknetEquipPlanTest> list) {
    public Result importBackNetExcel( List<OdsBacknetEquipPlanTest> list) {
        try {
            if (oConvertUtils.listIsEmpty(list)) {
                return Result.error("新增失败，缺少参数");
            }
            String regex = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
            for (OdsBacknetEquipPlanTest o : list) {
                String areaCity = o.getAreaCity();
                if (oConvertUtils.isEmpty(areaCity)) {
                    return Result.error("地市字段不能为空！");
                }
                String equipType = o.getEquipType();
                if (oConvertUtils.isEmpty(equipType)) {
                    return Result.error("设备类型字段不能为空！");
                }
                String equipFactory = o.getEquipFactory();
                if (oConvertUtils.isEmpty(equipFactory)) {
                    return Result.error("设备厂家字段不能为空！");
                }
                String backnetType = o.getBacknetType();
                if (oConvertUtils.isEmpty(backnetType)) {
                    return Result.error("退网类型字段不能为空！");
                }
                String neName = o.getNeName();
                if (oConvertUtils.isEmpty(neName)) {
                    return Result.error("网元名称字段不能为空！");
                }
                String neType = o.getNeType();
                if (oConvertUtils.isEmpty(neType)) {
                    return Result.error("网元类型字段不能为空！");
                }
                String planBacknetTime = o.getPlanBacknetTime();
                if (oConvertUtils.isEmpty(planBacknetTime)) {
                    return Result.error("计划退网时间字段不能为空！");
                }
                if (!isLegalDate(planBacknetTime.length(), planBacknetTime, "yyyy-MM-dd")) {
                    System.out.println(planBacknetTime);
                    System.out.println(areaCity);
                    System.out.println(o.getIsBuyMaintenance());
                    return Result.error("计划退网时间字段格式不正确，格式如：2021-01-01");
                }
                Double electricityPrice = o.getElectricityPrice();
                if (oConvertUtils.isEmpty(electricityPrice) || electricityPrice == 0) {
                    return Result.error("电费单价字段不能为空或是0！");
                }
                String isBuyMaintenance = o.getIsBuyMaintenance();
                if (oConvertUtils.isEmpty(isBuyMaintenance) || isBuyMaintenance.length() > 1) {
                    return Result.error("是否购买维保字段不能为空或格式有误！");
                }
                String saveMoney = o.getSaveMoney();
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(saveMoney);
                if (!matcher.matches()) {
                    System.out.println(saveMoney);
                    return Result.error("节省维保费字段值不能为非数字");
                }
                if (oConvertUtils.isEmpty(saveMoney)
                        || ("1".equals(isBuyMaintenance) && "0".equals(saveMoney))
                        || ("0".equals(isBuyMaintenance) && !"0".equals(saveMoney))) {
                    System.out.println(areaCity);
                    System.out.println(saveMoney);
                    return Result.error("节省维保费字段不能为空或格式有误！");
                }
                String createBy = o.getCreateBy();
                if (oConvertUtils.isEmpty(createBy)) {
                    return Result.error("创建人字段不能为空！");
                }
                //其它是否字段的判断
                if (oConvertUtils.isNotEmpty(o.getIsBs()) && o.getIsBs().length() > 1) {
                    return Result.error("是否基站字段格式不正确");
                }
                if (oConvertUtils.isNotEmpty(o.getIsUserMachroom()) && o.getIsUserMachroom().length() > 1) {
                    return Result.error("是否用户机房字段格式不正确");
                }
                if (oConvertUtils.isNotEmpty(o.getIsUsed()) && o.getIsUsed().length() > 1) {
                    return Result.error("是否要利旧字段格式不正确");
                }
                if (oConvertUtils.isNotEmpty(o.getIsInvest()) && o.getIsInvest().length() > 1) {
                    return Result.error("是否需要投资字段格式不正确");
                }
                if (oConvertUtils.isNotEmpty(o.getIsInvestWorkable()) && o.getIsInvestWorkable().length() > 1) {
                    return Result.error("是否已落实投资字段格式不正确");
                }
//                if (oConvertUtils.isNotEmpty(o.getNetTime().toString())&&!isLegalDate(o.getNetTime().toString().length(), o.getNetTime().toString(), "yyyy-MM-dd")) {
//                    System.out.println(o.getNetTime());
//                    System.out.println(areaCity);
//                    System.out.println(o.getIsBuyMaintenance());
//                    System.out.println(o.getIsInvest());
//                    return Result.error("网管上网元创建时间字段格式不正确，格式如：2021-01-01");
//                }
//                //设置缺少的计划编号
//                o.setPlanNum(planNum);
//                //设置缺少的计划名称
//                o.setPlanName(planName);
//                //设置缺少的计划日期
//                o.setPlanTime(planTime);
            }
//            Integer i = odsBacknetEquipPlanTestMapper.insertAll(list);
//            if (i != list.size()) {
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                return Result.error("excel导入退网计划失败！");
//            }
//            return Result.ok("excel批量导入退网计划成功！");
            return Result.ok(list);
        } catch (NoTransactionException e) {
            e.printStackTrace();
            return Result.error("excel导入失败，请查看必选参数是否填写正确并重试！");
        }
    }


    /**
     * 根据时间 和时间格式 校验是否正确
     *
     * @param length 校验的长度
     * @param sDate  校验的日期
     * @param format 校验的格式
     * @return
     */
    public static boolean isLegalDate(int length, String sDate, String format) {
        int legalLen = length;
        if ((sDate == null) || (sDate.length() != legalLen)) {
            return false;
        }
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(sDate);
            return sDate.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }

    }
}
