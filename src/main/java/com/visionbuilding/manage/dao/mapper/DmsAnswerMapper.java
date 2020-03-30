package com.visionbuilding.manage.dao.mapper;

import com.visionbuilding.manage.modle.entity.DmsAnswer;
import com.visionbuilding.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsAnswerMapper {
    DmsAnswer queryById(@Param("id") Long id);

    int queryPageCount(QueryBean queryBean);

    List<DmsAnswer> queryPage(QueryBean queryBean);

    Long insert(DmsAnswer dmsQuestion);
    int updateByPrimaryKeySelective(DmsAnswer dmsQuestion);
    int delete(@Param("id") Long id);
    int deleteByQuestionId(@Param("questionId") Long questionId);

    List<DmsAnswer> queryByQuestion(@Param("questionId") Long questionId);
}
