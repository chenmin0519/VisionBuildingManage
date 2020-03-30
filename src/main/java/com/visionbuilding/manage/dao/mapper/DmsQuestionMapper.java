package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsQuestion;
import com.visionbuilding.manage.modle.po.DmsQuestionVo;
import com.visionbuilding.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsQuestionMapper {
    DmsQuestionVo queryById(@Param("id") Long id);

    int queryPageCount(QueryBean queryBean);

    List<DmsQuestionVo> queryPage(QueryBean queryBean);

    Long insertQuestion(DmsQuestion dmsQuestion);
    int updateByPrimaryKeySelective(DmsQuestion dmsQuestion);

    void delete(@Param("id")Long id);
}
