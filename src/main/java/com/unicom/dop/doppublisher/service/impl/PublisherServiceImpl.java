package com.unicom.dop.doppublisher.service.impl;

import com.unicom.dop.doppublisher.mapper.DauEquipMapper;

import com.unicom.dop.doppublisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PublisherServiceImpl implements PublisherService {


    @Autowired
    DauEquipMapper dauEquipMapper;

    @Override
    public List<Map> getNationalTaskCount() {
        return dauEquipMapper.getNationalTaskCount();
    }

    @Override
    public List<Map> getEquipPlanAnalyse(String area_city, String grid_name, String profession, String equip_status_new, String dt_month) {
        return dauEquipMapper.getEquipPlanAnalyse(area_city,grid_name,profession,equip_status_new,dt_month);
    }

    @Override
    public List<Map> getEquipPlanAnalyseDetial(String neName) {
        return dauEquipMapper.getEquipPlanAnalyseDetial(neName);
    }
}
