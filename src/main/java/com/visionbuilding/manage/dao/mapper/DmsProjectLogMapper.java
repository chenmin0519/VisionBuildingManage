package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsProjectLog;

public interface DmsProjectLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsProjectLog record);

    int insertSelective(DmsProjectLog record);

    DmsProjectLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsProjectLog record);

    int updateByPrimaryKey(DmsProjectLog record);
}