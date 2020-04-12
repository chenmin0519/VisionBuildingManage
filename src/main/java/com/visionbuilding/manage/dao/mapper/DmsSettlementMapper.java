package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsSettlement;
import com.visionbuilding.manage.modle.po.StaticsMouthsPo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface DmsSettlementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsSettlement record);

    int insertSelective(DmsSettlement record);

    DmsSettlement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsSettlement record);

    int updateByPrimaryKey(DmsSettlement record);

    List<StaticsMouthsPo> selectOnYearData(@Param("startTime") String startTime, @Param("endTime") String endTime);
}