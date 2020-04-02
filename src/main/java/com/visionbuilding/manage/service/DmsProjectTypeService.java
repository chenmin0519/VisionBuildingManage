package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsProjectType;

public interface DmsProjectTypeService {
    DmsProjectType selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
    void insertSelective(DmsProjectType param);
    void updateByPrimaryKeySelective(DmsProjectType params);
    ResultPOListBean<DmsProjectType> queryPage(DmsProjectType department);
}
