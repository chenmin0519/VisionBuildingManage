package com.dteel.manage.dao.mapper;

import com.dteel.manage.modle.entity.DmsMenu;
import com.dteel.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsMenuMapper {
    int deleteByPrimaryKey(@Param("id")Long id);
    int deleteByPrentId(@Param("id") Long id);
    int insertSelective(DmsMenu record);

    DmsMenu selectByPrimaryKey(@Param("id")Long id);

    int updateByPrimaryKeySelective(DmsMenu record);



    /**
     * 分页查询
     * @param queryBean
     * @return
     * @
     */
    List<DmsMenu> queryMenuInfoByPage(QueryBean queryBean);
    Integer queryMenuInfoByPageCount(QueryBean queryBean);

    List<DmsMenu> querMenuByParent(@Param("parentId") Long parentId);

    int updateByParentId(DmsMenu parentMenu);

    List<DmsMenu> queryUserMenu(@Param("userId") Long userId);

    List<DmsMenu> queryMenusByRole(@Param("roleId") Long roleId);

    List<DmsMenu> queryEditZtreeMenusByRole(@Param("roleId") Long roleId);

    List<DmsMenu> queryAllMenu();
}
