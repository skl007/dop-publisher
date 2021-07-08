package com.unicom.dop.doppublisher.service;

import com.alibaba.fastjson.JSONObject;
import com.unicom.dop.doppublisher.common.Result;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PublisherService {

    public List<Map> getNationalTaskCount();

    public List<Map> getEquipPlanAnalyse(@Param("area_city") String area_city, @Param("grid_name") String grid_name, @Param("profession") String profession, @Param("equip_status_new") String equip_status_new, @Param("dt_month") String dt_month);

    public List<Map> getEquipPlanAnalyseDetial(@Param("neName") String neName);


    Result addBackNet(JSONObject jsonObject);

    Result getBackNet(Integer pageNo,Integer pageSize,String planNum,String planName,HttpServletRequest request);

    Result updateBackNet(JSONObject jsonObject);

    Result deleteBackNet(JSONObject jsonObject);

    Result verifyBackNet(HttpServletRequest request);
}
