package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.po.StaticsBusinessPo;
import com.visionbuilding.manage.modle.po.StaticsMouthsPo;
import com.visionbuilding.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsMainProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsMainProject record);

    int insertSelective(DmsMainProject record);

    DmsMainProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsMainProject record);

    int updateByPrimaryKey(DmsMainProject record);

    List<DmsMainProject> queryPage(QueryBean queryBean);

    int queryPageCount(QueryBean queryBean);

    DmsMainProject getMaxCode(DmsMainProject dmsMainProject);

    int updateMoneyById(DmsMainProject dmsMainProject);

    int countByBussiness(@Param("pinyin") String pinyin);

    List<StaticsMouthsPo> selectOnYearData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<StaticsMouthsPo> getBusinessEchartInfo(@Param("code") String code);

    int getBusinessEchartInfoPageCount(QueryBean queryBean);

    List<StaticsMouthsPo> getBusinessEchartInfoPage(QueryBean queryBean);
}