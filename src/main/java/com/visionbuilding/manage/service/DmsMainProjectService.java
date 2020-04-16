package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsChildProject;
import com.visionbuilding.manage.modle.entity.DmsMainProject;

public interface DmsMainProjectService {

    DmsMainProject selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
    void insertSelective(DmsMainProject dmsMainProject);
    void updateByPrimaryKeySelective(DmsMainProject dmsMainProject);
    ResultPOListBean<DmsMainProject> queryPage(DmsMainProject dmsMainProject);

    void insertSelectiveChild(DmsChildProject dmsChildProject);
    void updateByPrimaryKeySelectiveChild(DmsChildProject dmsChildProject);

    ResultPOListBean<DmsChildProject> querySubPage(DmsChildProject dmsChildProject);
}
