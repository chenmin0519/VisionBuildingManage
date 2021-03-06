package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;

public interface DmsDepartmentService {

    DmsDepartment selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
    void insertSelective(DmsDepartment param);
    void updateByPrimaryKeySelective(DmsDepartment params);
    ResultPOListBean<DmsDepartment> queryPage(DmsDepartment department);
}
