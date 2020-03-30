package com.dteel.manage.dao.mapper;

import com.dteel.manage.modle.entity.DmsDepartment;
import com.dteel.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsDepartmentMapper {

    int queryPageCount(QueryBean queryBean);
    List<DmsDepartment> queryPage(QueryBean queryBean);

    DmsDepartment queryById(@Param("id") Long id);

    int insertSelective(DmsDepartment department);

    void updateByPrimaryKeySelective(DmsDepartment department);

    void delete(@Param("id")Long id);

    List<DmsDepartment> queryAll();
}
