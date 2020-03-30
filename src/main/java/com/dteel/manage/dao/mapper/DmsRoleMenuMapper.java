package com.dteel.manage.dao.mapper;

import com.dteel.manage.modle.entity.DmsRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsRoleMenuMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insertSelective(DmsRoleMenu record);

    DmsRoleMenu selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(DmsRoleMenu record);

    int deleteByRoleIds(@Param("roleId") Long roleId);

    int saveBatch(List<DmsRoleMenu> roleMenus);
}