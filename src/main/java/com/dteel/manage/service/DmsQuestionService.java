package com.dteel.manage.service;

import com.dteel.manage.modle.ResultBean;
import com.dteel.manage.modle.ResultPOListBean;
import com.dteel.manage.modle.entity.DmsQuestion;
import com.dteel.manage.modle.po.DmsQuestionImport;
import com.dteel.manage.modle.po.DmsQuestionVo;
import com.dteel.manage.modle.po.QuestionAnswer;
import com.dteel.manage.modle.po.QuestionSaveVo;

import java.util.List;

public interface DmsQuestionService {
    QuestionAnswer queryById(Long id);

    ResultPOListBean<DmsQuestionVo> queryPage(DmsQuestion question);

    ResultBean save(QuestionSaveVo question);

    void delete(Long id);

    ResultBean batchSave(List<List<DmsQuestionImport>> questionsList);
}
