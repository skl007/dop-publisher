package com.unicom.dop.doppublisher.service;

import com.alibaba.fastjson.JSONObject;
import com.unicom.dop.doppublisher.common.Result;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PublisherService {

    public List<Map> getNationalTaskCount();

    public Result getEquipPlanAnalyse(@Param("area_city") String area_city,
                                         @Param("grid_name") String grid_name,
                                         @Param("profession") String profession,
                                         @Param("equip_status_new") String equip_status_new,
                                         @Param("dt_month") String dt_month,
                                         Integer pageNo,
                                         Integer pageSize);

//    public List<Map> getEquipPlanAnalyseDetial(@Param("neName") String neName);
    public Result getEquipPlanAnalyseDetial(@Param("neName") String neName,Integer pageNo,Integer pageSize);


    Result addBackNet(JSONObject jsonObject);

    Result getBackNet(Integer pageNo,Integer pageSize,String planNum,String planName);

    Result updateBackNet(JSONObject jsonObject);

    Result deleteBackNet(JSONObject jsonObject);

    Result verifyBackNet(HttpServletRequest request);

    Result selectAllPlan(Integer pageNo, Integer pageSize, String planNum, String areaCity, String profession);

    Result deleteAllPlan(JSONObject jsonObject);
}
