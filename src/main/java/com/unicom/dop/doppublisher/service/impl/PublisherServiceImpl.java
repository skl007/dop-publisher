package com.unicom.dop.doppublisher.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.unicom.dop.doppublisher.common.Result;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlan;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanIndex;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanTest;
import com.unicom.dop.doppublisher.mapper.DauEquipMapper;

import com.unicom.dop.doppublisher.mapper.OdsBacknetEquipPlanIndexMapper;
import com.unicom.dop.doppublisher.mapper.OdsBacknetEquipPlanMapper;
import com.unicom.dop.doppublisher.mapper.OdsBacknetEquipPlanTestMapper;
import com.unicom.dop.doppublisher.service.PublisherService;
import com.unicom.dop.doppublisher.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    public List<Map> getEquipPlanAnalyse(String area_city, String grid_name, String profession, String equip_status_new, String dt_month) {
        return dauEquipMapper.getEquipPlanAnalyse(area_city, grid_name, profession, equip_status_new, dt_month);
    }

    @Override
    public List<Map> getEquipPlanAnalyseDetial(String neName) {
        return dauEquipMapper.getEquipPlanAnalyseDetial(neName);
    }

    /**
     * 新增退网设备--从表和主表入库
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addBackNet(JSONObject jsonObject) {
        try {
            List<OdsBacknetEquipPlanTest> odsBacknetEquipPlan = jsonObject.getJSONArray("OdsBacknetEquipPlan")
                    .toJavaList(OdsBacknetEquipPlanTest.class);
            OdsBacknetEquipPlanIndex odsBacknetEquipPlanIndex = jsonObject.getJSONObject("OdsBacknetEquipPlanIndex")
                    .toJavaObject(OdsBacknetEquipPlanIndex.class);
            if (oConvertUtils.listIsEmpty(odsBacknetEquipPlan)) {
                return Result.error("新增失败，缺少参数");
            }
            if (oConvertUtils.isEmpty(odsBacknetEquipPlanIndex)){
                return Result.error("新增失败,缺少参数");
            }
            int insert = odsBacknetEquipPlanIndexMapper.insert(odsBacknetEquipPlanIndex);
            if (insert<1){
                return Result.error("新增失败");
            }
            Integer i = odsBacknetEquipPlanTestMapper.insertAll(odsBacknetEquipPlan);
            if (i < 1) {
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
     * @param pageNo
     * @param pageSize
     * @param planNum
     * @param planName
     * @return
     */
    @Override
    public Result getBackNet(Integer pageNo,Integer pageSize,String planNum,String planName) {
//   计划编号
        if (oConvertUtils.isEmpty(planNum)){
            return Result.error("计划编号为空！");
        }
        //计划名称
        if (oConvertUtils.isEmpty(planName)){
            return Result.error("计划名称为空！");
        }
        List<OdsBacknetEquipPlanTest> list=odsBacknetEquipPlanTestMapper.selectBackNetByPlanNumAndPlanName(planNum,planName,pageNo,pageSize);
       Integer total= odsBacknetEquipPlanTestMapper.countAll(planNum,planName);
       JSONObject jsonObject=new JSONObject();
       jsonObject.put("list",list);
       jsonObject.put("total",total);
        return Result.ok(jsonObject);
    }

    /**
     * 修改退网设备信息
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateBackNet(JSONObject jsonObject) {
        try {
            OdsBacknetEquipPlanTest odsBacknetEquipPlanTest = jsonObject.getJSONObject("OdsBacknetEquipPlanTest")
                    .toJavaObject(OdsBacknetEquipPlanTest.class);
           Integer i= odsBacknetEquipPlanTestMapper.updateByPrimaryKeySelective(odsBacknetEquipPlanTest);
           if (i<1){
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
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteBackNet(JSONObject jsonObject) {
        try {
            String areaCity = jsonObject.getString("areaCity");
            String neName = jsonObject.getString("neName");
           Integer i= odsBacknetEquipPlanTestMapper.deleteByPrimaryKey(areaCity,neName);
           if (i<1){
               return Result.error("删除失败！");
           }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("删除失败");
        }
    }

    @Override
    public Result verifyBackNet(HttpServletRequest request) {
        String planNum = request.getParameter("planNum");
        if (oConvertUtils.isEmpty(planNum)){
            return Result.error("计划编号不能为空！");
        }
        OdsBacknetEquipPlanIndex odsBacknetEquipPlanIndex = odsBacknetEquipPlanIndexMapper.selectByPrimaryKey(planNum);
        if (oConvertUtils.isEmpty(odsBacknetEquipPlanIndex)){
            return Result.ok();
        }
        return Result.error("计划已存在！");
    }

    @Override
    public Result selectAllPlan(Integer pageNo, Integer pageSize, String planNum, String areaCity, String profession) {
        JSONObject jsonObject=new JSONObject();
        List<OdsBacknetEquipPlanIndex> list= odsBacknetEquipPlanIndexMapper.selectAllPlan(pageNo,pageSize,planNum,areaCity,profession);
        if (oConvertUtils.listIsNotEmpty(list)){
            Integer total=odsBacknetEquipPlanIndexMapper.selectAllPlanCount(planNum,areaCity,profession);
            jsonObject.put("total",total);
        }
        jsonObject.put("list",list);
        return Result.ok(jsonObject);
    }
}
