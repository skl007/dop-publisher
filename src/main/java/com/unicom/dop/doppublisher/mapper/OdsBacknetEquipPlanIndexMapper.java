package com.unicom.dop.doppublisher.mapper;

import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanIndex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface OdsBacknetEquipPlanIndexMapper {
    int deleteByPrimaryKey(String planNum);

    int insert(@Param("record") OdsBacknetEquipPlanIndex record);

    int insertSelective(OdsBacknetEquipPlanIndex record);

    OdsBacknetEquipPlanIndex selectByPrimaryKey(@Param("planNum") String planNum);

    int updateByPrimaryKeySelective(OdsBacknetEquipPlanIndex record);

    int updateByPrimaryKey(OdsBacknetEquipPlanIndex record);
}