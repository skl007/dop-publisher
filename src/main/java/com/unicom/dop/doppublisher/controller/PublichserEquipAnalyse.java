package com.unicom.dop.doppublisher.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unicom.dop.doppublisher.common.Result;
import com.unicom.dop.doppublisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
public class PublichserEquipAnalyse {

    @Autowired
    PublisherService publisherService;

    @GetMapping("/total")
    public String getUnicomHeaderPersonTrack() {
        List<Map> personTrack = publisherService.getNationalTaskCount();

        return JSON.toJSONString(personTrack);
    }

    @GetMapping("/equip/analyse/{area_city}/{grid_name}/{profession}/{equip_status_new}/{dt_month}")
    public String getDauEquipPlanAnalyse(@PathVariable("area_city") String area_city, @PathVariable("grid_name") String grid_name, @PathVariable("profession") String profession2, @PathVariable("equip_status_new") String equip_status_new, @PathVariable("dt_month") String dt_month) {

        List<Map> equipPlanAnalyse = publisherService.getEquipPlanAnalyse(area_city, grid_name, profession2, equip_status_new, dt_month);
        ArrayList<Map> tmpArr = new ArrayList<>();
        Iterator<Map> iterator = equipPlanAnalyse.iterator();
        while (iterator.hasNext()) {
            Map obj = iterator.next();
            if (obj == null) {
                return "[{}]";
            }
            Object eqpSid;
            Object areaCity;
            Object gridName;
            Object profession;
            Object backnetType;
            Object neName;
            Object boardName;
            Object planBacknetTime;
            Object realBacknetTime;
            Object planSaveCosts;
            Object realSaveCosts;
            Object realSaveElectricity;
            Object saveMoney;

            if (obj.get("eqpSid") == null) {
                eqpSid = "";
            } else {
                eqpSid = (String) obj.get("eqpSid");
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
            if (obj.get("boardName") == null) {
                boardName = "";
            } else {
                boardName = (String) obj.get("boardName");
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
            model.put("计划编号", eqpSid);
            model.put("地市", areaCity);
            model.put("网格", gridName);
            model.put("专业", profession);
            model.put("退网类型", backnetType);
            model.put("网元名称", neName);
            model.put("板卡名称", boardName);
            model.put("计划退网时间", planBacknetTime);
            model.put("实际退网时间", realBacknetTime);
            model.put("计划节省成本/元", planSaveCosts);
            model.put("实际节省成本/元", realSaveCosts);
            model.put("节省电费/元", realSaveElectricity);
            model.put("节省维保费/元", saveMoney);

            tmpArr.add(model);
        }

        return JSON.toJSONString(tmpArr);

    }


    @GetMapping("/equip/analyse/detail/{neName}")
    public String getDauEquipPlanAnalyseDetail(@PathVariable("neName") String neName2) {

        List<Map> equipPlanAnalyseDetial = publisherService.getEquipPlanAnalyseDetial(neName2);
        ArrayList<Map> tmpArr = new ArrayList<>();
        Iterator<Map> iterator = equipPlanAnalyseDetial.iterator();
        while (iterator.hasNext()) {
            Map obj = iterator.next();
            if (obj == null) {
                return "[{}]";
            }
            Object eqpSid;
            Object areaCity;
            Object gridName;
            Object profession;
            Object roomSname;
            Object eqpType;
            Object reslvl;
            Object backnetType;
            Object equipFactory;
            Object neType;
            Object neName;
            Object backnetReason;
            Object netTime;
            Object eqpSidRatedPower;
            Object powerNumber;
            Object planBacknetTime;
            Object realBacknetTime;
            Object logtype;
            Object status;
            Object equipStatusNew;
            Object realSaveCosts;
            Object saveMoney;
            Object realSaveElectricity;


            if (obj.get("eqpSid") == null) {
                eqpSid = "";
            } else {
                eqpSid = (String) obj.get("eqpSid");
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
            if (obj.get("eqpSidRatedPower") == null) {
                eqpSidRatedPower = "";
            } else {
                eqpSidRatedPower = (Double) obj.get("eqpSidRatedPower");
            }
            if (obj.get("powerNumber") == null) {
                powerNumber = "";
            } else {
                powerNumber = (Double) obj.get("powerNumber");
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
            model.put("计划编号", eqpSid);
            model.put("地市", areaCity);
            model.put("设备所在网格", gridName);
            model.put("专业", profession);
            model.put("设备所在机房", roomSname);
            model.put("设备类型", eqpType);
            model.put("网络层级", reslvl);
            model.put("退网类型", backnetType);
            model.put("设备厂家", equipFactory);
            model.put("网元类型", neType);
            model.put("网元名称", neName);
            model.put("退网原因", backnetReason);
            model.put("网管上网元创建时间", netTime);
            model.put("标称功率", eqpSidRatedPower);
            model.put("网管功耗读数", powerNumber);
            model.put("计划退网时间", planBacknetTime);
            model.put("实际退网时间", realBacknetTime);
            model.put("稽核系统设备状态", logtype);
            model.put("资源系统设备状态", status);
            model.put("退网设备状态", equipStatusNew);
            model.put("实际年度节省成本", realSaveCosts);
            model.put("实际年度节省维保费", saveMoney);
            model.put("实际年度节省电费", realSaveElectricity);

            tmpArr.add(model);
        }

        return JSON.toJSONString(tmpArr);

    }


    @RequestMapping(value = "/equip/backnet/addBackNet", method = RequestMethod.POST)
    public Result addBackNet(@RequestBody JSONObject jsonObject) {
        return publisherService.addBackNet(jsonObject);
    }

    /**
     * 校验计划编号是否重复
     * @param request
     * @return
     */
    @RequestMapping(value = "/equip/backnet/verifyBackNet", method = RequestMethod.GET)
    public Result verifyBackNet(HttpServletRequest request) {
        return publisherService.verifyBackNet(request);
    }

    /**
     * 获取退网设备详情
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/equip/backnet/getBackNet", method = RequestMethod.GET)
    public Result getBackNet(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                             @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                             @RequestParam(name="planNum") String planNum,
                             @RequestParam(name="planName") String planName) {
        return publisherService.getBackNet(pageNo,pageSize,planNum,planName);
    }
    @RequestMapping(value = "/equip/backnet/updateBackNet", method = RequestMethod.POST)
    public Result updateBackNet(@RequestBody JSONObject jsonObject) {
        return publisherService.updateBackNet(jsonObject);
    }

    @RequestMapping(value = "/equip/backnet/deleteBackNet", method = RequestMethod.POST)
    public Result deleteBackNet(@RequestBody JSONObject jsonObject) {
        return publisherService.deleteBackNet(jsonObject);
    }

    @RequestMapping(value = "/equip/backnet/selectAllPlan",method = RequestMethod.GET)
    public Result selectAllPlan(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                @RequestParam(name="planNum",required = false) String planNum,
                                @RequestParam(name = "areaCity",required = false)String areaCity,
                                @RequestParam(name = "profession",required = false)String profession){
        return publisherService.selectAllPlan(pageNo,pageSize,planNum,areaCity,profession);
    }
}