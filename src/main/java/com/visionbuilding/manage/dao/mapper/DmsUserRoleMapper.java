package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsUserRoleMapper {

    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(DmsUserRole record);

    int insertSelective(DmsUserRole record);

    DmsUserRole selectByPrimaryKey(@Param("id")Long id);

    int updateByPrimaryKeySelective(DmsUserRole record);

    int delByUserId(@Param("userId") Long userId);

    int saveBatch(List<DmsUserRole> userRoleList);
}
