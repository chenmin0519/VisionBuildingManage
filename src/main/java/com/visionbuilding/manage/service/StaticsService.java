package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.po.DepartmentStaticsPo;
import com.visionbuilding.manage.modle.po.ExportDepartmentExclePo;
import com.visionbuilding.manage.modle.po.ExportExclePo;
import com.visionbuilding.manage.modle.query.StaticsQuery;

import java.util.List;

public interface StaticsService {
    ResultPOListBean<DepartmentStaticsPo> queryPage(StaticsQuery par) throws Exception;

    /**
     * 获取设计院excle数据
     * @param department
     * @return
     */
    List<ExportDepartmentExclePo> getData(StaticsQuery department);

    /**
     * 获取运营中心excle数据
     * @param department
     * @return
     */
    List<ExportExclePo> getAllData(StaticsQuery department);
}
