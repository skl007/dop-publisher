package com.unicom.dop.doppublisher.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DauEquipMapper {


    /**
     * 测试
     * @return
     */
    public List<Map> getNationalTaskCount();

//    public List<Map> getEquipPlanAnalyse(@Param("area_city") String area_city, @Param("grid_name") String grid_name, @Param("profession") String profession, @Param("equip_status_new") String equip_status_new, @Param("dt_month") String dt_month);
    public List<Map> getEquipPlanAnalyse(@Param("planNum") String planNum,
                                         @Param("areaCity") String areaCity,
                                         @Param("gridName") String gridName,
                                         @Param("profession") String profession,
                                         @Param("equipStatusNew") String equipStatusNew,
                                         @Param("planYear") String planYear,
                                         @Param("planMonth") String planMonth,
                                         @Param("pageNo")Integer pageNo,
                                         @Param("pageSize")Integer pageSize,
                                         @Param("flag")Integer flag,
                                         @Param("neName")String neName);

    public List<Map> getEquipPlanAnalyseDetial(@Param("neName") String neName,
                                               @Param("eqpSid") String eqpSid,
                                               @Param("areaCity") String areaCity);



    Integer getEquipPlanAnalyseCount(@Param("planNum") String planNum,
                                     @Param("areaCity") String areaCity,
                                     @Param("gridName") String gridName,
                                     @Param("profession") String profession,
                                     @Param("equipStatusNew") String equipStatusNew,
                                     @Param("planYear") String planYear,
                                     @Param("planMonth") String planMonth,
                                     @Param("flag")Integer flag,
                                     @Param("neName")String neName);

//    Integer getEquipPlanAnalyseDetialCount(@Param("neName")String neName);
}
