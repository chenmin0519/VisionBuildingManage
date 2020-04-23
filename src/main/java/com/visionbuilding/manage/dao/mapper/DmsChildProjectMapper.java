package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsChildProject;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.query.QueryBean;

import java.util.List;

public interface DmsChildProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsChildProject record);

    int insertSelective(DmsChildProject record);

    DmsChildProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsChildProject record);

    int updateByPrimaryKey(DmsChildProject record);

    List<DmsChildProject> querySubPage(QueryBean queryBean);

    int queryPageCount(QueryBean queryBean);

    int updateStatusById(DmsChildProject dmsChildProject);

    List<DmsChildProject> queryAllSub(Long id);

    List<DmsChildProject> querySubProjectList(DmsChildProject dmsChildProject);
}