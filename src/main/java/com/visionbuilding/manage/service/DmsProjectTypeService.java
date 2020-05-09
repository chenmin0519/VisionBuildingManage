package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.entity.DmsProjectType;

import java.util.List;

public interface DmsProjectTypeService {
    DmsProjectType selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
    void insertSelective(DmsProjectType param);
    void updateByPrimaryKeySelective(DmsProjectType params);
    ResultPOListBean<DmsProjectType> queryPage(DmsProjectType department);

    List<DmsProjectType> getAll();

    DmsProjectType selectByCode(String projectCode);
}
