package com.unicom.dop.doppublisher.mapper;

import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanIndex;
import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OdsBacknetEquipPlanTestMapper {
//    int deleteByPrimaryKey(OdsBacknetEquipPlanTestKey key);

    int insert(OdsBacknetEquipPlanTest record);

    int insertSelective(OdsBacknetEquipPlanTest record);

//    OdsBacknetEquipPlanTest selectByPrimaryKey(OdsBacknetEquipPlanTestKey key);

    int updateByPrimaryKeySelective(OdsBacknetEquipPlanTest record);

    int updateByPrimaryKey(OdsBacknetEquipPlanTest record);

    Integer insertAll(@Param("list") List<OdsBacknetEquipPlanTest> list);

    //LIMIT ${(pageNo-1)*pageSize},${pageSize};
    List<OdsBacknetEquipPlanTest> selectBackNetByPlanNumAndPlanName(@Param("planNum") String planNum,
                                                                    @Param("planName") String planName,
                                                                    @Param("pageNo") Integer pageNo,
                                                                    @Param("pageSize") Integer pageSize);

    Integer deleteByPrimaryKey(@Param("areaCity") String areaCity, @Param("neName") String neName);

    Integer countAll(@Param("planNum") String planNum, @Param("planName") String planName);

    List<Map<String, Object>> selectAllPlan(@Param("pageNo") Integer pageNo,
                                            @Param("pageSize") Integer pageSize,
                                            @Param("planNum") String planNum,
                                            @Param("areaCity") String areaCity,
                                            @Param("profession") String profession);

    Integer selectAllPlanCount(@Param("planNum") String planNum,
                               @Param("areaCity") String areaCity,
                               @Param("profession") String profession);

    Integer deleteByPlanNumAndPlanName(@Param("planNum") String planNum, @Param("planName") String planName);

//    Integer verifyBackNet(@Param("planNum") String planNum);
}