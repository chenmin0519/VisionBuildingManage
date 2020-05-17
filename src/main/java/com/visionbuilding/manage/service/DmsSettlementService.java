package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.po.EchartPo;
import com.visionbuilding.manage.modle.po.StaticsBusinessPo;
import com.visionbuilding.manage.modle.po.StaticsMouthsPo;
import com.visionbuilding.manage.modle.po.StaticsUserPo;
import com.visionbuilding.manage.modle.query.DepartmentEchartQuery;

import java.util.Date;

public interface DmsSettlementService {
    /**
     * echart信息查询
     * @return
     */
    EchartPo getEchartInfo();

    /**
     * 公司利润统计
     * @return
     */
    EchartPo getEchartInfoBussiness();

    /**
     * 公司利润招商公司维度
     * @return
     */
    EchartPo getBusinessEchartInfo(DmsMainProject dmsProjectType);

    /**
     * 分页公司利润招商公司维度
     * @param dmsProjectType
     * @return
     */
    ResultPOListBean<StaticsMouthsPo> getBusinessEchartInfoPage(DmsMainProject dmsProjectType);

    /**
     * 部门维度设计院利润计算
     * @param startTime
     * @param endTime
     * @return
     */
    EchartPo getdepartmentEchartInfo(Date startTime, Date endTime);

    /**
     * 分页部门维度设计院利润计算
     * @param dmsProjectType
     * @return
     */
    ResultPOListBean<StaticsBusinessPo> getdepartmentEchartInfoPage(DepartmentEchartQuery dmsProjectType);

    /**
     * 分设计师维度来算
     * @param startTime
     * @param endTime
     * @return
     */
    EchartPo getUserEchartInfo(Date startTime, Date endTime,Long department);

    /**
     * 分页设计师维度来算
     * @param dmsProjectType
     * @return
     */
    ResultPOListBean<StaticsUserPo> getUserEchartInfoPage(DepartmentEchartQuery dmsProjectType);
}
