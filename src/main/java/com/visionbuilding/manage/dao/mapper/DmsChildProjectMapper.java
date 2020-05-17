package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsChildProject;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DmsChildProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsChildProject record);

    int insertSelective(DmsChildProject record);

    DmsChildProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsChildProject record);

    int updateByPrimaryKey(DmsChildProject record);

    List<DmsChildProject> querySubPage(QueryBean queryBean);

    int queryPageCount(QueryBean queryBean);

    int updateStatusById(DmsChildProject dmsChildProject);

    List<DmsChildProject> queryAllSub(@Param(value="id")Long id);

    List<DmsChildProject> querySubProjectList(DmsChildProject dmsChildProject);

    Long sumAreaByUserTime(@Param(value="uid")Long uid, @Param(value="projectCode")String projectCode,
                           @Param(value="startTime")Date startTime, @Param(value="endTime")Date endTime);

    int gettodaySubDatesCount(QueryBean queryBean);

    List<DmsChildProject> qgettodaySubDatesPage(QueryBean queryBean);
}