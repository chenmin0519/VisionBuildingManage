package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsProjectType;
import com.visionbuilding.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsProjectTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsProjectType record);

    int insertSelective(DmsProjectType record);

    DmsProjectType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsProjectType record);

    int updateByPrimaryKey(DmsProjectType record);

    int queryPageCount(QueryBean queryBean);

    List<DmsProjectType> queryPage(QueryBean queryBean);

    List<DmsProjectType> getAll();

    int countBysCode(@Param("typeCode") String typeCode);

    DmsProjectType selectByCode(@Param("projectCode")String projectCode);
}