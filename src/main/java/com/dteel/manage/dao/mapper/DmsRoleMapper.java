package com.dteel.manage.dao.mapper;


import com.dteel.manage.modle.entity.DmsRole;
import com.dteel.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsRoleMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(DmsRole record);

    int insertSelective(DmsRole record);

    DmsRole selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(DmsRole record);

    /**
     * 分页查询
     * @param queryBean
     * @return
     * @
     */
    List<DmsRole> queryRoleInfoByPage(QueryBean queryBean);
    Integer queryRoleInfoByPageCount(QueryBean queryBean);

    List<DmsRole> queryRoleByuser(@Param("userId") Long userId);

    List<DmsRole> queryAllRole();
}