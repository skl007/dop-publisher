package com.unicom.dop.doppublisher.mapper;

import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanIndex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface OdsBacknetEquipPlanIndexMapper {
    int deleteByPrimaryKey(String planNum);

    int insert(@Param("record") OdsBacknetEquipPlanIndex record);

    int insertSelective(OdsBacknetEquipPlanIndex record);

    OdsBacknetEquipPlanIndex selectByPrimaryKey(@Param("planNum") String planNum);

    int updateByPrimaryKeySelective(OdsBacknetEquipPlanIndex record);

    int updateByPrimaryKey(OdsBacknetEquipPlanIndex record);

    List<OdsBacknetEquipPlanIndex> selectAllPlan(@Param("pageNo") Integer pageNo,
                                                 @Param("pageSize") Integer pageSize,
                                                 @Param("planNum") String planNum,
                                                 @Param("areaCity") String areaCity,
                                                 @Param("profession") String profession);

    Integer selectAllPlanCount(@Param("planNum") String planNum,
                               @Param("areaCity") String areaCity,
                               @Param("profession") String profession);
}