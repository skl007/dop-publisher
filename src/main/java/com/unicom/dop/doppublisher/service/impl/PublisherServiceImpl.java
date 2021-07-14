package com.unicom.dop.doppublisher.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.unicom.dop.doppublisher.common.Result;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlan;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanIndex;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanTest;
import com.unicom.dop.doppublisher.entity.OdsBacknetTaskManagement;
import com.unicom.dop.doppublisher.mapper.*;

import com.unicom.dop.doppublisher.service.PublisherService;
import com.unicom.dop.doppublisher.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

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
    @Autowired
    private OdsBacknetTaskManagementMapper odsBacknetTaskManagementMapper;


    @Override
    public List<Map> getNationalTaskCount() {
        return dauEquipMapper.getNationalTaskCount();
    }

    @Override
//    public List<Map> getEquipPlanAnalyse(String area_city, String grid_name, String profession, String equip_status_new, String dt_month) {
    public Result getEquipPlanAnalyse(String planNum,String areaCity,
                                      String gridName, String profession,
                                      String equipStatusNew,String planYear,String planMonth,
                                      Integer pageNo,
                                      Integer pageSize) {

//        return dauEquipMapper.getEquipPlanAnalyse(area_city, grid_name, profession, equip_status_new, dt_month);
        JSONObject jsonObject = new JSONObject();
//        if (oConvertUtils.isEmpty(pageNo) || pageNo == 0) {
//            pageNo = 1;
//        }
//        if (oConvertUtils.isEmpty(pageSize) || pageSize == 0) {
//            pageSize = 10;
//        }
        int flag=0;
        if (oConvertUtils.isNotEmpty(planMonth)){
            flag=1;
        }else if (oConvertUtils.isNotEmpty(planYear)){
            flag=2;
        }
        List<Map> equipPlanAnalyse = dauEquipMapper.getEquipPlanAnalyse(planNum,areaCity, gridName, profession, equipStatusNew, planYear,planMonth, pageNo, pageSize,flag);
        Integer total = dauEquipMapper.getEquipPlanAnalyseCount(planNum,areaCity, gridName, profession, equipStatusNew, planYear,planMonth,flag);
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
            Object neName;
            Object planBacknetTime;
            Object logdate;
            Object orderTime;
            Object realBacknetTime;
            Object planSaveCosts;
            Object realSaveCosts;
            Object realSaveElectricity;
            Object saveMoney;

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
            model.put("计划节省成本/元", planSaveCosts);
            model.put("实际节省成本/元", realSaveCosts);
            model.put("节省电费/元", realSaveElectricity);
            model.put("节省维保费/元", saveMoney);

            tmpArr.add(model);
        }
        jsonObject.put("list", tmpArr);
        return Result.ok(jsonObject);
    }

    @Override
    public Result getEquipPlanAnalyseDetial(String neName) {
//        return dauEquipMapper.getEquipPlanAnalyseDetial(neName);
        JSONObject jsonObject = new JSONObject();
        List<Map> equipPlanAnalyseDetial = dauEquipMapper.getEquipPlanAnalyseDetial(neName);
//        Integer total = dauEquipMapper.getEquipPlanAnalyseDetialCount(neName);
        ArrayList<Map> tmpArr = new ArrayList<>();
        Iterator<Map> iterator = equipPlanAnalyseDetial.iterator();
        while (iterator.hasNext()) {
            Map obj = iterator.next();
            if (obj == null) {
                return Result.ok("数据为空！");
            }
            Object planName;
            Object areaCity;
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
            Object realBacknetTime;
            Object logtype;
            Object status;
            Object equipStatusNew;
            Object realSaveCosts;
            Object saveMoney;
            Object realSaveElectricity;


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
            model.put("计划编号", planName);
            model.put("地市", areaCity);
            model.put("设备所在网格", gridName);
            model.put("专业", profession);
            model.put("设备所在机房", roomSname);
            model.put("设备类型", eqpType);
            model.put("网络层级", reslvl);
            model.put("退网类型", backnetType);
            model.put("设备厂家", equipFactory);
            model.put("是否用户机房",isUserMachroom);
            model.put("是否基站",isBs);
            model.put("网元类型", neType);
            model.put("网元名称", neName);
            model.put("退网原因", backnetReason);
            model.put("网管上网元创建时间", netTime);
            model.put("是否购买维保", isBuyMaintenance);
            model.put("是否要利旧", isUsed);
            model.put("标称功率", eqpSidRatedPower);
            model.put("是否需要投资", isInvest);
            model.put("网管功耗读数", powerNumber);
            model.put("计划退网时间", planBacknetTime);
            model.put("实际退网时间", realBacknetTime);
//            model.put("稽核系统设备状态", logtype);
//            model.put("资源系统设备状态", status);
            model.put("退网设备状态", equipStatusNew);
            model.put("实际年度节省成本", realSaveCosts);
            model.put("实际年度节省维保费", saveMoney);
            model.put("实际年度节省电费", realSaveElectricity);

            tmpArr.add(model);
        }
        jsonObject.put("list", tmpArr);
//        jsonObject.put("total", total);
        return Result.ok(jsonObject);
    }

    /**
     * 新增退网设备--从表和主表入库
     *
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addBackNet(JSONObject jsonObject) {
        try {
            List<OdsBacknetEquipPlanTest> odsBacknetEquipPlan = jsonObject.getJSONArray("OdsBacknetEquipPlanList")
                    .toJavaList(OdsBacknetEquipPlanTest.class);
//            OdsBacknetEquipPlanIndex odsBacknetEquipPlanIndex = jsonObject.getJSONObject("OdsBacknetEquipPlanIndex")
//                    .toJavaObject(OdsBacknetEquipPlanIndex.class);
            if (oConvertUtils.listIsEmpty(odsBacknetEquipPlan)) {
                return Result.error("新增失败，缺少参数");
            }
//            if (oConvertUtils.isEmpty(odsBacknetEquipPlanIndex)){
//                return Result.error("新增失败,缺少参数");
//            }
//            int insert = odsBacknetEquipPlanIndexMapper.insert(odsBacknetEquipPlanIndex);
//            if (insert<1){
//                return Result.error("新增失败");
//            }
            Integer i = odsBacknetEquipPlanTestMapper.insertAll(odsBacknetEquipPlan);
            if (i < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.error("新增失败");
            }
            return Result.ok("新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("新增失败！");
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
                             String planNum,String planName,
                             String equipType,
                             String equipFactory,String neName) {
//   计划编号
        if (oConvertUtils.isEmpty(planNum)) {
            return Result.error("计划编号为空！");
        }
        //计划名称
        if (oConvertUtils.isEmpty(planName)) {
            return Result.error("计划名称为空！");
        }
        List<OdsBacknetEquipPlanTest> list = odsBacknetEquipPlanTestMapper.selectBackNetByPlanNumAndPlanName(planNum, planName,
                pageNo, pageSize,equipType,equipFactory,neName);
        Integer total = odsBacknetEquipPlanTestMapper.countAll(planNum, planName,equipType,equipFactory,neName);
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
            OdsBacknetEquipPlanTest odsBacknetEquipPlanTest = jsonObject.getJSONObject("OdsBacknetEquipPlan")
                    .toJavaObject(OdsBacknetEquipPlanTest.class);
            Integer i = odsBacknetEquipPlanTestMapper.updateByPrimaryKeySelective(odsBacknetEquipPlanTest);
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
            String neName = jsonObject.getString("neName");
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
        Integer i = odsBacknetEquipPlanTestMapper.countAll(planNum, "","","","");
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
                                String planNum,String planName,
                                String areaCity, String profession,
                                String planYear,String planMonth) {
        JSONObject jsonObject = new JSONObject();
        int flag=0;
        if (oConvertUtils.isNotEmpty(planMonth)){
            flag=1;
        }else if (oConvertUtils.isNotEmpty(planYear)){
            flag=2;
        }
        List<Map<String, Object>> list = odsBacknetEquipPlanTestMapper.selectAllPlan(pageNo, pageSize, planNum,planName, areaCity, profession,planYear,planMonth,flag);
        if (oConvertUtils.listIsNotEmpty(list)) {
            Integer total = odsBacknetEquipPlanTestMapper.selectAllPlanCount(planNum,planName, areaCity, profession,planYear,planMonth,flag);
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
            if (oConvertUtils.isEmpty(planName)){
                return Result.error("计划名称不存在");
            }
            Integer backNetEquipNumber = jsonObject.getInteger("backNetEquipNumber");
            if (oConvertUtils.isEmpty(backNetEquipNumber) || backNetEquipNumber == 0) {
                return Result.error("删除失败");
            }
            Integer i1 = odsBacknetEquipPlanTestMapper.deleteByPlanNumAndPlanName(planNum, planName);
            Integer i2 = odsBacknetEquipPlanTestMapper.deleteDWDByPlanNumAndPlanName(planNum, planName);
//            if (!i.equals(backNetEquipNumber)) {
            if (!(i1.equals(backNetEquipNumber)&&i2.equals(backNetEquipNumber))) {
                System.out.println("backNetEquipNumber==========="+backNetEquipNumber);
                System.out.println("i1==========="+i1);
                System.out.println("i2==========="+i2);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
     * 获取所有退网任务计划管理
     * @return
     */
    @Override
    public Result selectAllTask(Integer pageNo,Integer pageSize) {
        JSONObject jsonObject=new JSONObject();
       List<Map> list= odsBacknetTaskManagementMapper.selectAll(pageNo,pageSize);
       Integer total=odsBacknetTaskManagementMapper.selectAllCount();
       jsonObject.put("list",list);
       jsonObject.put("total",total);
        return Result.ok(jsonObject);
    }

    /**
     * 批量修改退网计划信息
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
//            //获取计划名称
//            String planName = jsonObject.getString("planName");
//            if (oConvertUtils.isEmpty(planName)){
//                return Result.error("计划名称不存在");
//            }
            Integer backNetEquipNumber = jsonObject.getInteger("backNetEquipNumber");
            if (oConvertUtils.isEmpty(backNetEquipNumber) || backNetEquipNumber == 0) {
                return Result.error("缺少参数，修改失败");
            }
          Integer i= odsBacknetEquipPlanTestMapper.updateByPlanNumAndPlanName(planNum,nPlanNum,nPlanName,nPlanTime);
            if (!i.equals(backNetEquipNumber)){
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
    public Result getNumberData(String areaCity, String gridName, String profession, String planYear,String planMonth) {
        int flag=0;
        if (oConvertUtils.isNotEmpty(planMonth)){
            flag=1;
        }else if (oConvertUtils.isNotEmpty(planYear)){
            flag=2;
        }
       List<Map> list= odsBacknetEquipPlanTestMapper.getNumberData(areaCity,gridName,profession,planYear,planMonth,flag);
        return Result.ok(list);
    }

    @Override
    public Result planMonitoring(String planYear) {
        if (oConvertUtils.isEmpty(planYear)){
            planYear= String.valueOf(LocalDate.now().getYear());
        }
       List<Map> list= odsBacknetEquipPlanTestMapper.planMonitoring(planYear);
        return Result.ok(list);
    }

    @Override
    public Result planArea(String planYear) {
        if (oConvertUtils.isEmpty(planYear)){
            planYear= String.valueOf(LocalDate.now().getYear());
        }
        List<Map> list= odsBacknetEquipPlanTestMapper.planArea(planYear);
        return Result.ok(list);
    }
}
