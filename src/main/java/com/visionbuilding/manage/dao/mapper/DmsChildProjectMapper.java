package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsChildProject;

public interface DmsChildProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsChildProject record);

    int insertSelective(DmsChildProject record);

    DmsChildProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsChildProject record);

    int updateByPrimaryKey(DmsChildProject record);
}