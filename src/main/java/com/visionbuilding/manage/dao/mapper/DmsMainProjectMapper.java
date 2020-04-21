package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.query.QueryBean;

import java.util.List;

public interface DmsMainProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsMainProject record);

    int insertSelective(DmsMainProject record);

    DmsMainProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsMainProject record);

    int updateByPrimaryKey(DmsMainProject record);

    List<DmsMainProject> queryPage(QueryBean queryBean);

    int queryPageCount(QueryBean queryBean);

    DmsMainProject getMaxCode(DmsMainProject dmsMainProject);

    int updateMoneyById(DmsMainProject dmsMainProject);
}