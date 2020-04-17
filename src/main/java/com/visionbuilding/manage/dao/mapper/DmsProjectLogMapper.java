package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsProjectLog;
import com.visionbuilding.manage.modle.query.QueryBean;

import java.util.List;

public interface DmsProjectLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsProjectLog record);

    int insertSelective(DmsProjectLog record);

    DmsProjectLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsProjectLog record);

    int updateByPrimaryKey(DmsProjectLog record);

    int queryByPageCount(QueryBean queryBean);

    List<DmsProjectLog> queryByPage(QueryBean queryBean);
}