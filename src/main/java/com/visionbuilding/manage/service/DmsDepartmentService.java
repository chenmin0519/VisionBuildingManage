package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

public interface DmsDepartmentService {

    DmsDepartment selectByPrimaryKey(@Param("id") Long id);
    void deleteByPrimaryKey(@Param("id") Long id);
    void insertSelective(DmsDepartment param);
    void updateByPrimaryKeySelective(DmsDepartment params);
    ResultPOListBean<DmsDepartment> queryPage(DmsDepartment department);
}
