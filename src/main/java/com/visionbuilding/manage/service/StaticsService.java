package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.po.DepartmentStaticsPo;
import com.visionbuilding.manage.modle.po.ExportDepartmentExclePo;
import com.visionbuilding.manage.modle.po.ExportExclePo;
import com.visionbuilding.manage.modle.query.StaticsQuery;

import java.util.List;
import java.util.Map;

public interface StaticsService {
    ResultPOListBean<DepartmentStaticsPo> queryPage(StaticsQuery par) throws Exception;

    /**
     * 获取设计院excle数据
     * @param department
     * @param role  1运营中心 2设计院
     * @return
     */
    Map<String,Object> getData(StaticsQuery department,Integer role);


}
