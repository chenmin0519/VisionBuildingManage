package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsQuestion;
import com.visionbuilding.manage.modle.po.DmsQuestionImport;
import com.visionbuilding.manage.modle.po.DmsQuestionVo;
import com.visionbuilding.manage.modle.po.QuestionAnswer;
import com.visionbuilding.manage.modle.po.QuestionSaveVo;

import java.util.List;

public interface DmsQuestionService {
    QuestionAnswer queryById(Long id);

    ResultPOListBean<DmsQuestionVo> queryPage(DmsQuestion question);

    ResultBean save(QuestionSaveVo question);

    void delete(Long id);

    ResultBean batchSave(List<List<DmsQuestionImport>> questionsList);
}
