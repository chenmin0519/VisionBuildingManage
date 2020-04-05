package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsDepartmentMapper {
    DmsDepartment selectByPrimaryKey(@Param("id") Long id);
    void deleteByPrimaryKey(@Param("id") Long id);
    void insertSelective(DmsDepartment param);
    void updateByPrimaryKeySelective(DmsDepartment params);
    List<DmsDepartment> queryPage(QueryBean queryBean);
    int queryPageCount(QueryBean queryBean);

    List<DmsDepartment> getAll();
}
