package com.unicom.dop.doppublisher.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unicom.dop.doppublisher.common.Result;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanTest;
import com.unicom.dop.doppublisher.service.PublisherService;
import com.unicom.dop.doppublisher.util.ExportXls;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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

    //@GetMapping("/equip/analyse/{area_city}/{grid_name}/{profession}/{equip_status_new}/{dt_month}")
    @GetMapping("/equip/analyse/getAllEquip")
//    public String getDauEquipPlanAnalyse(@PathVariable("area_city") String area_city, @PathVariable("grid_name") String grid_name, @PathVariable("profession") String profession2, @PathVariable("equip_status_new") String equip_status_new, @PathVariable("dt_month") String dt_month) {
    public Result getDauEquipPlanAnalyse(@RequestParam(value = "planNum", required = false) String planNum,
                                         @RequestParam(value = "areaCity", required = false) String areaCity,
                                         @RequestParam(value = "gridName", required = false) String gridName,
                                         @RequestParam(value = "profession", required = false) String profession,
                                         @RequestParam(value = "equipStatusNew", required = false) String equipStatusNew,
                                         @RequestParam(value = "neName", required = false) String neName,
                                         @RequestParam(name = "planYear", required = false) String planYear,
                                         @RequestParam(name = "planMonth", required = false) String planMonth,
                                         @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

//        List<Map> equipPlanAnalyse = publisherService.getEquipPlanAnalyse(area_city, grid_name, profession2, equip_status_new, dt_month);
        return publisherService.getEquipPlanAnalyse(planNum, areaCity, gridName, profession, equipStatusNew, planYear, planMonth, pageNo, pageSize,neName);
//        JSONObject jsonObject=new JSONObject();
//        ArrayList<Map> tmpArr = new ArrayList<>();
//        Iterator<Map> iterator = equipPlanAnalyse.iterator();
//        while (iterator.hasNext()) {
//            Map obj = iterator.next();
//            if (obj == null) {
//                return Result.ok("????????????");
//            }
//            Object eqpSid;
//            Object areaCity;
//            Object gridName;
//            Object profession;
//            Object backnetType;
//            Object neName;
//            Object boardName;
//            Object planBacknetTime;
//            Object realBacknetTime;
//            Object planSaveCosts;
//            Object realSaveCosts;
//            Object realSaveElectricity;
//            Object saveMoney;
//
//            if (obj.get("eqpSid") == null) {
//                eqpSid = "";
//            } else {
//                eqpSid = (String) obj.get("eqpSid");
//            }
//            if (obj.get("areaCity") == null) {
//                areaCity = "";
//            } else {
//                areaCity = (String) obj.get("areaCity");
//            }
//            if (obj.get("gridName") == null) {
//                gridName = "";
//            } else {
//                gridName = (String) obj.get("gridName");
//            }
//            if (obj.get("profession") == null) {
//                profession = "";
//            } else {
//                profession = (String) obj.get("profession");
//            }
//            if (obj.get("backnetType") == null) {
//                backnetType = "";
//            } else {
//                backnetType = (String) obj.get("backnetType");
//            }
//            if (obj.get("neName") == null) {
//                neName = "";
//            } else {
//                neName = (String) obj.get("neName");
//            }
//            if (obj.get("boardName") == null) {
//                boardName = "";
//            } else {
//                boardName = (String) obj.get("boardName");
//            }
//            if (obj.get("planBacknetTime") == null) {
//                planBacknetTime = "";
//            } else {
//                planBacknetTime = (String) obj.get("planBacknetTime");
//            }
//            if (obj.get("realBacknetTime") == null) {
//                realBacknetTime = "";
//            } else {
//                realBacknetTime = (String) obj.get("realBacknetTime");
//            }
//            if (obj.get("planSaveCosts") == null) {
//                planSaveCosts = "";
//            } else {
//                planSaveCosts = (Double) obj.get("planSaveCosts");
//            }
//            if (obj.get("realSaveCosts") == null) {
//                realSaveCosts = "";
//            } else {
//                realSaveCosts = (Double) obj.get("realSaveCosts");
//            }
//            if (obj.get("realSaveElectricity") == null) {
//                realSaveElectricity = "";
//            } else {
//                realSaveElectricity = (Double) obj.get("realSaveElectricity");
//            }
//            if (obj.get("saveMoney") == null) {
//                saveMoney = "";
//            } else {
//                saveMoney = (String) obj.get("saveMoney");
//            }
//
//            Map model = new HashMap();
//            model.put("????????????", eqpSid);
//            model.put("??????", areaCity);
//            model.put("??????", gridName);
//            model.put("??????", profession);
//            model.put("????????????", backnetType);
//            model.put("????????????", neName);
//            model.put("????????????", boardName);
//            model.put("??????????????????", planBacknetTime);
//            model.put("??????????????????", realBacknetTime);
//            model.put("??????????????????/???", planSaveCosts);
//            model.put("??????????????????/???", realSaveCosts);
//            model.put("????????????/???", realSaveElectricity);
//            model.put("???????????????/???", saveMoney);
//
//            tmpArr.add(model);
//        }
//        jsonObject.put("list",tmpArr);
//        return Result.ok(jsonObject);

    }


    //    @GetMapping("/equip/analyse/detail/{neName}")
    @GetMapping("/equip/analyse/detail")
//    public String getDauEquipPlanAnalyseDetail(@PathVariable("neName") String neName2) {
    public Result getDauEquipPlanAnalyseDetail(@RequestParam(value = "neName",required = false) String neName,
                                               @RequestParam(value = "eqpSid",required = false) String eqpSid,
                                               @RequestParam(value = "areaCity",required = false) String areaCity) {

        return publisherService.getEquipPlanAnalyseDetial(neName,eqpSid,areaCity);
//        List<Map> equipPlanAnalyseDetial = publisherService.getEquipPlanAnalyseDetial(neName2);
//        ArrayList<Map> tmpArr = new ArrayList<>();
//        Iterator<Map> iterator = equipPlanAnalyseDetial.iterator();
//        while (iterator.hasNext()) {
//            Map obj = iterator.next();
//            if (obj == null) {
//                return "[{}]";
//            }
//            Object eqpSid;
//            Object areaCity;
//            Object gridName;
//            Object profession;
//            Object roomSname;
//            Object eqpType;
//            Object reslvl;
//            Object backnetType;
//            Object equipFactory;
//            Object neType;
//            Object neName;
//            Object backnetReason;
//            Object netTime;
//            Object eqpSidRatedPower;
//            Object powerNumber;
//            Object planBacknetTime;
//            Object realBacknetTime;
//            Object logtype;
//            Object status;
//            Object equipStatusNew;
//            Object realSaveCosts;
//            Object saveMoney;
//            Object realSaveElectricity;
//
//
//            if (obj.get("eqpSid") == null) {
//                eqpSid = "";
//            } else {
//                eqpSid = (String) obj.get("eqpSid");
//            }
//            if (obj.get("areaCity") == null) {
//                areaCity = "";
//            } else {
//                areaCity = (String) obj.get("areaCity");
//            }
//            if (obj.get("gridName") == null) {
//                gridName = "";
//            } else {
//                gridName = (String) obj.get("gridName");
//            }
//            if (obj.get("profession") == null) {
//                profession = "";
//            } else {
//                profession = (String) obj.get("profession");
//            }
//            if (obj.get("roomSname") == null) {
//                roomSname = "";
//            } else {
//                roomSname = (String) obj.get("roomSname");
//            }
//            if (obj.get("eqpType") == null) {
//                eqpType = "";
//            } else {
//                eqpType = (String) obj.get("eqpType");
//            }
//            if (obj.get("reslvl") == null) {
//                reslvl = "";
//            } else {
//                reslvl = (String) obj.get("reslvl");
//            }
//            if (obj.get("backnetType") == null) {
//                backnetType = "";
//            } else {
//                backnetType = (String) obj.get("backnetType");
//            }
//            if (obj.get("equipFactory") == null) {
//                equipFactory = "";
//            } else {
//                equipFactory = (String) obj.get("equipFactory");
//            }
//            if (obj.get("neType") == null) {
//                neType = "";
//            } else {
//                neType = (String) obj.get("neType");
//            }
//            if (obj.get("neName") == null) {
//                neName = "";
//            } else {
//                neName = (String) obj.get("neName");
//            }
//            if (obj.get("backnetReason") == null) {
//                backnetReason = "";
//            } else {
//                backnetReason = (String) obj.get("backnetReason");
//            }
//            if (obj.get("netTime") == null) {
//                netTime = "";
//            } else {
//                netTime = (String) obj.get("netTime");
//            }
//            if (obj.get("eqpSidRatedPower") == null) {
//                eqpSidRatedPower = "";
//            } else {
//                eqpSidRatedPower = (Double) obj.get("eqpSidRatedPower");
//            }
//            if (obj.get("powerNumber") == null) {
//                powerNumber = "";
//            } else {
//                powerNumber = (Double) obj.get("powerNumber");
//            }
//            if (obj.get("planBacknetTime") == null) {
//                planBacknetTime = "";
//            } else {
//                planBacknetTime = (String) obj.get("planBacknetTime");
//            }
//            if (obj.get("realBacknetTime") == null) {
//                realBacknetTime = "";
//            } else {
//                realBacknetTime = (String) obj.get("realBacknetTime");
//            }
//            if (obj.get("logtype") == null) {
//                logtype = "";
//            } else {
//                logtype = (String) obj.get("logtype");
//            }
//            if (obj.get("status") == null) {
//                status = "";
//            } else {
//                status = (String) obj.get("status");
//            }
//            if (obj.get("equipStatusNew") == null) {
//                equipStatusNew = "";
//            } else {
//                equipStatusNew = (String) obj.get("equipStatusNew");
//            }
//            if (obj.get("realSaveCosts") == null) {
//                realSaveCosts = "";
//            } else {
//                realSaveCosts = (Double) obj.get("realSaveCosts");
//            }
//            if (obj.get("saveMoney") == null) {
//                saveMoney = "";
//            } else {
//                saveMoney = (String) obj.get("saveMoney");
//            }
//            if (obj.get("realSaveElectricity") == null) {
//                realSaveElectricity = "";
//            } else {
//                realSaveElectricity = (Double) obj.get("realSaveElectricity");
//            }
//
//            Map model = new HashMap();
//            model.put("????????????", eqpSid);
//            model.put("??????", areaCity);
//            model.put("??????????????????", gridName);
//            model.put("??????", profession);
//            model.put("??????????????????", roomSname);
//            model.put("????????????", eqpType);
//            model.put("????????????", reslvl);
//            model.put("????????????", backnetType);
//            model.put("????????????", equipFactory);
//            model.put("????????????", neType);
//            model.put("????????????", neName);
//            model.put("????????????", backnetReason);
//            model.put("???????????????????????????", netTime);
//            model.put("????????????", eqpSidRatedPower);
//            model.put("??????????????????", powerNumber);
//            model.put("??????????????????", planBacknetTime);
//            model.put("??????????????????", realBacknetTime);
//            model.put("????????????????????????", logtype);
//            model.put("????????????????????????", status);
//            model.put("??????????????????", equipStatusNew);
//            model.put("????????????????????????", realSaveCosts);
//            model.put("???????????????????????????", saveMoney);
//            model.put("????????????????????????", realSaveElectricity);
//
//            tmpArr.add(model);
//        }
//
//        return JSON.toJSONString(tmpArr);

    }


    @RequestMapping(value = "/equip/backnet/addBackNet", method = RequestMethod.POST)
    public Result addBackNet(@RequestBody JSONObject jsonObject) {
        return publisherService.addBackNet(jsonObject);
    }

    /**
     * ??????????????????????????????
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/equip/backnet/verifyBackNet", method = RequestMethod.GET)
    public Result verifyBackNet(HttpServletRequest request) {
        return publisherService.verifyBackNet(request);
    }

    /**
     * ????????????????????????
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/equip/backnet/getBackNet", method = RequestMethod.GET)
    public Result getBackNet(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "planNum") String planNum,
                             @RequestParam(name = "planName") String planName,
//                             @RequestParam(name = "areaCity") String areaCity,
                             @RequestParam(name = "equipType", required = false) String equipType,
                             @RequestParam(name = "equipFactory", required = false) String equipFactory,
                             @RequestParam(name = "neName", required = false) String neName) {
        return publisherService.getBackNet(pageNo, pageSize, planNum, planName, equipType, equipFactory, neName);
    }

    @RequestMapping(value = "/equip/backnet/updateBackNet", method = RequestMethod.POST)
    public Result updateBackNet(@RequestBody JSONObject jsonObject) {
        return publisherService.updateBackNet(jsonObject);
    }

    @RequestMapping(value = "/equip/backnet/deleteBackNet", method = RequestMethod.POST)
    public Result deleteBackNet(@RequestBody JSONObject jsonObject) {
        return publisherService.deleteBackNet(jsonObject);
    }

    @RequestMapping(value = "/equip/backnet/selectAllPlan", method = RequestMethod.GET)
    public Result selectAllPlan(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "planNum", required = false) String planNum,
                                @RequestParam(name = "planName", required = false) String planName,
                                @RequestParam(name = "areaCity", required = false) String areaCity,
                                @RequestParam(name = "profession", required = false) String profession,
                                @RequestParam(name = "planYear", required = false) String planYear,
                                @RequestParam(name = "planMonth", required = false) String planMonth) {
        return publisherService.selectAllPlan(pageNo, pageSize, planNum, planName, areaCity, profession, planYear, planMonth);
    }

    @RequestMapping(value = "/equip/backnet/deleteAllPlan", method = RequestMethod.POST)
    public Result deleteAllPlan(@RequestBody JSONObject jsonObject) {
        return publisherService.deleteAllPlan(jsonObject);
    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public Result selectTaskManagement(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
//                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
//        return publisherService.selectAllTask(pageNo, pageSize);
//    }

    @RequestMapping(value = "/equip/backnet/updateAllPlan", method = RequestMethod.POST)
    public Result updateAllPlan(@RequestBody JSONObject jsonObject) {
        return publisherService.updateAllPlan(jsonObject);
    }

    /**
     * ?????????????????????????????????
     *
     * @param areaCity
     * @param gridName
     * @param profession
     * @param planYear
     * @param planMonth
     * @return
     */
    @RequestMapping(value = "/equip/analyse/getNumberData", method = RequestMethod.GET)
    public Result getNumberData(@RequestParam(name = "areaCity", required = false) String areaCity,
                                @RequestParam(name = "gridName", required = false) String gridName,
                                @RequestParam(name = "profession", required = false) String profession,
                                @RequestParam(name = "planYear", required = false) String planYear,
                                @RequestParam(name = "planMonth", required = false) String planMonth) {
        return publisherService.getNumberData(areaCity, gridName, profession, planYear, planMonth);
    }

    /**
     * ????????????????????????
     *
     * @param planYear
     * @return
     */
    @RequestMapping(value = "equip/analyse/planMonitoring", method = RequestMethod.GET)
    public Result planMonitoring(@RequestParam(name = "planYear", required = false) String planYear) {
        return publisherService.planMonitoring(planYear);
    }

    /**
     * ????????????????????????
     *
     * @param planYear
     * @return
     */
    @RequestMapping(value = "equip/analyse/planArea", method = RequestMethod.GET)
    public Result planArea(@RequestParam(name = "planYear", required = false) String planYear) {
        return publisherService.planArea(planYear);
    }

    @RequestMapping(value = "equip/analyse/importBackNetExcel", method = RequestMethod.POST)
    public Result importBackNetExcel(HttpServletRequest request, HttpServletResponse response) {
        List<OdsBacknetEquipPlanTest> list = ExportXls.importExcel(request, OdsBacknetEquipPlanTest.class);
//        String planNum = request.getParameter("planNum");
//        String planName = request.getParameter("planName");
//        String planTime = request.getParameter("planTime");
//        return publisherService.importBackNetExcel(planNum,planName,planTime,list);
        return publisherService.importBackNetExcel(list);
    }




}