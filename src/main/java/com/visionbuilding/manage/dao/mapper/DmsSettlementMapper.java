package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsSettlement;
import com.visionbuilding.manage.modle.po.StaticsBusinessPo;
import com.visionbuilding.manage.modle.po.StaticsMouthsPo;
import com.visionbuilding.manage.modle.po.StaticsUserPo;
import com.visionbuilding.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DmsSettlementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsSettlement record);

    int insertSelective(DmsSettlement record);

    DmsSettlement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsSettlement record);

    int updateByPrimaryKey(DmsSettlement record);

    List<StaticsMouthsPo> selectOnYearData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<StaticsBusinessPo> getdepartmentEchartInfo(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

    int getdepartmentEchartInfoPageCount(QueryBean queryBean);

    List<StaticsBusinessPo> getdepartmentEchartInfoPage(QueryBean queryBean);

    List<StaticsUserPo> getUserEchartInfo(@Param("startTime")Date startTime,  @Param("endTime")Date endTime,  @Param("department")Long department);

    int getUserEchartInfoPageCount(QueryBean queryBean);

    List<StaticsUserPo> getUserEchartInfoPage(QueryBean queryBean);
}