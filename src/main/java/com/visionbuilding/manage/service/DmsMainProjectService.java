package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsChildProject;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.query.DepartmentEchartQuery;

import java.text.ParseException;
import java.util.Date;

public interface DmsMainProjectService {
    // 操作大项目
    DmsMainProject selectByPrimaryKey(Long id);

    /**
     * 删除主项目
     * @param id
     */
    void deleteByPrimaryKey(Long id);
    void insertSelective(DmsMainProject dmsMainProject) throws ParseException;
    void updateByPrimaryKeySelective(DmsMainProject dmsMainProject) throws ParseException;
    ResultPOListBean<DmsMainProject> queryPage(DmsMainProject dmsMainProject);

    void insertSelectiveChild(DmsChildProject dmsChildProject);
    void updateByPrimaryKeySelectiveChild(DmsChildProject dmsChildProject);

    // 操作子项目
    ResultPOListBean<DmsChildProject> querySubPage(DmsChildProject dmsChildProject);

    /**
     * 根据id查询子项目
     * @param id
     * @return
     */
    DmsChildProject selectSubByPrimaryKey(Long id);

    /**
     * 子项目提审(通过)
     * @param id
     */
    void auditSubProject(Long id);

    /**
     * 子项目提审(不通过)
     * @param id
     */
    void noAuditSubProject(Long id);

    /**
     * 删除子项目
     * @param id
     */
    void deleteSubByPrimaryKey(Long id);

    /**
     *
     * @param uid
     * @param startTime
     * @param endTime
     * @return
     */
    Long sumAreaByUserTime(Long uid, Date startTime, Date endTime,String projectCode);

    /**
     * 获取今天要审核子项目列表
     * @param dmsChildProject
     * @return
     */
    ResultPOListBean<DmsChildProject> gettodaySubDates(DepartmentEchartQuery dmsChildProject);

    Integer counttodaySubDates();
}
