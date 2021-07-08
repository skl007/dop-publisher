package com.unicom.dop.doppublisher.mapper;

import com.unicom.dop.doppublisher.entity.OdsResourceRoom;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OdsResourceRoomMapper {
    int deleteByPrimaryKey(String roomSid);

    int insert(OdsResourceRoom record);

    int insertSelective(OdsResourceRoom record);

    OdsResourceRoom selectByPrimaryKey(String roomSid);

    int updateByPrimaryKeySelective(OdsResourceRoom record);

    int updateByPrimaryKey(OdsResourceRoom record);
}