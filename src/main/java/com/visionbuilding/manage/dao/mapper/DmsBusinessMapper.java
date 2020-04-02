package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsBusiness;

public interface DmsBusinessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsBusiness record);

    int insertSelective(DmsBusiness record);

    DmsBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsBusiness record);

    int updateByPrimaryKey(DmsBusiness record);
}