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
    public List<Map> getEquipPlanAnalyse(@Param("area_city") String area_city,
                                         @Param("grid_name") String grid_name,
                                         @Param("profession") String profession,
                                         @Param("equip_status_new") String equip_status_new,
                                         @Param("dt_month") String dt_month,
                                         @Param("pageNo")Integer pageNo,
                                         @Param("pageSize")Integer pageSize);

//    public List<Map> getEquipPlanAnalyseDetial(@Param("neName") String neName);
    public List<Map> getEquipPlanAnalyseDetial(@Param("neName") String neName,
                                               @Param("pageNo")Integer pageNo,
                                               @Param("pageSize")Integer pageSize);


    Integer getEquipPlanAnalyseCount(@Param("area_city") String area_city,
                                     @Param("grid_name") String grid_name,
                                     @Param("profession") String profession,
                                     @Param("equip_status_new") String equip_status_new,
                                     @Param("dt_month") String dt_month);

    Integer getEquipPlanAnalyseDetialCount(@Param("neName")String neName);
}
