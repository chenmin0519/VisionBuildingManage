package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsAmountLog;

public interface DmsAmountLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DmsAmountLog record);

    int insertSelective(DmsAmountLog record);

    DmsAmountLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmsAmountLog record);

    int updateByPrimaryKey(DmsAmountLog record);

    /**
     * 唯一索引增加或修改
     * @param dmsAmountLog
     */
    void insertOrUpdata(DmsAmountLog dmsAmountLog);

    /**
     * 查询一条数据
     * @param dmsAmountLog
     * @return
     */
    DmsAmountLog selectOne(DmsAmountLog dmsAmountLog);
}