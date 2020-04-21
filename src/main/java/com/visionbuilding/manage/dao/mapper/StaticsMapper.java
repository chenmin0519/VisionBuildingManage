package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.po.DepartmentStaticsPo;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.modle.query.StaticsQuery;

import java.util.List;

public interface StaticsMapper {
    public List<DepartmentStaticsPo> queryList(QueryBean query);
    Integer querycount(QueryBean query);

    List<DepartmentStaticsPo> queryListByParam(StaticsQuery department);
}
