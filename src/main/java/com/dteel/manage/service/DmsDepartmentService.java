package com.dteel.manage.service;

import com.dteel.manage.modle.ResultBean;
import com.dteel.manage.modle.ResultPOListBean;
import com.dteel.manage.modle.entity.DmsDepartment;

import java.util.List;

public interface DmsDepartmentService {
    /**
     * 分页
     * @param department
     * @return
     */
    ResultPOListBean<DmsDepartment> queryPage(DmsDepartment department);

    DmsDepartment queryById(Long id);

    ResultBean insert(DmsDepartment department);

    ResultBean update(DmsDepartment department);

    ResultBean delete(Long id);

    ResultPOListBean<DmsDepartment> queryAll();
    List<DmsDepartment> queryDepartmentList();
}
