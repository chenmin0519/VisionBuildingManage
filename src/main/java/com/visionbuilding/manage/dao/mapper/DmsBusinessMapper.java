package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsBusiness;
import com.visionbuilding.manage.modle.entity.DmsProjectType;
import com.visionbuilding.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsBusinessMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(DmsBusiness record);

    int insertSelective(DmsBusiness record);

    DmsBusiness selectByPrimaryKey(@Param("id")Long id);

    int updateByPrimaryKeySelective(DmsBusiness record);

    int updateByPrimaryKey(DmsBusiness record);

    int queryPageCount(QueryBean queryBean);

    List<DmsBusiness> queryPage(QueryBean queryBean);

    List<DmsBusiness> getAll();
}