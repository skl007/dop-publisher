package com.unicom.dop.doppublisher.mapper;

import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface OdsBacknetEquipPlanMapper {
//    int deleteByPrimaryKey(OdsBacknetEquipPlanKey key);
    int insert(OdsBacknetEquipPlan record);

    int insertSelective(OdsBacknetEquipPlan record);

//    OdsBacknetEquipPlan selectByPrimaryKey(OdsBacknetEquipPlanKey key);

    int updateByPrimaryKeySelective(OdsBacknetEquipPlan record);

    int updateByPrimaryKey(OdsBacknetEquipPlan record);

    Integer insertAll(List<OdsBacknetEquipPlan> odsBacknetEquipPlan);
}