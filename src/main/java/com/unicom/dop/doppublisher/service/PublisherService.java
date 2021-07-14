package com.unicom.dop.doppublisher.service;

import com.alibaba.fastjson.JSONObject;
import com.unicom.dop.doppublisher.common.Result;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PublisherService {

    public List<Map> getNationalTaskCount();

    public Result getEquipPlanAnalyse(String planNum,String areaCity,
                                      String gridName, String profession,
                                      String equipStatusNew,String planYear,String planMonth,
                                         Integer pageNo,
                                         Integer pageSize);

//    public List<Map> getEquipPlanAnalyseDetial(@Param("neName") String neName);
    public Result getEquipPlanAnalyseDetial(@Param("neName") String neName);


    Result addBackNet(JSONObject jsonObject);

    Result getBackNet(Integer pageNo,Integer pageSize,
                      String planNum,String planName,
                      String equipType,
                      String equipFactory,String neName);

    Result updateBackNet(JSONObject jsonObject);

    Result deleteBackNet(JSONObject jsonObject);

    Result verifyBackNet(HttpServletRequest request);

    Result selectAllPlan(Integer pageNo, Integer pageSize,
                         String planNum,String planName,
                         String areaCity, String profession,
                         String planYear,String planMonth);

    Result deleteAllPlan(JSONObject jsonObject);

    Result selectAllTask(Integer pageNo,Integer pageSize);

    Result updateAllPlan(JSONObject jsonObject);

    Result getNumberData(String areaCity, String gridName, String profession, String planYear,String planMonth);

    Result planMonitoring(String planYear);

    Result planArea(String planYear);
}
