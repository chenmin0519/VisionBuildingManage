package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsChildProject;
import com.visionbuilding.manage.modle.entity.DmsMainProject;

public interface DmsMainProjectService {
    // 操作大项目
    DmsMainProject selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
    void insertSelective(DmsMainProject dmsMainProject);
    void updateByPrimaryKeySelective(DmsMainProject dmsMainProject);
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
}
