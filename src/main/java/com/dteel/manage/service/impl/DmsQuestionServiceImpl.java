package com.dteel.manage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dteel.manage.dao.mapper.DmsAnswerMapper;
import com.dteel.manage.dao.mapper.DmsQuestionMapper;
import com.dteel.manage.exception.LogicException;
import com.dteel.manage.modle.ResultBean;
import com.dteel.manage.modle.ResultPOListBean;
import com.dteel.manage.modle.entity.DmsAnswer;
import com.dteel.manage.modle.entity.DmsDepartment;
import com.dteel.manage.modle.entity.DmsQuestion;
import com.dteel.manage.modle.po.DmsQuestionImport;
import com.dteel.manage.modle.po.DmsQuestionVo;
import com.dteel.manage.modle.po.QuestionAnswer;
import com.dteel.manage.modle.po.QuestionSaveVo;
import com.dteel.manage.modle.query.QueryBean;
import com.dteel.manage.myenum.EnumQuestoinType;
import com.dteel.manage.service.DmsQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DmsQuestionServiceImpl implements DmsQuestionService {

    @Autowired
    private DmsQuestionMapper dmsQuestionMapper;

    @Autowired
    private DmsAnswerMapper dmsAnswerMapper;

    @Override
    public ResultBean batchSave(List<List<DmsQuestionImport>> questionsList) {
        ResultBean resultBean = new ResultBean();
        for(List<DmsQuestionImport> questions : questionsList){
            for(DmsQuestionImport question : questions){
                try{
                    QuestionSaveVo questionSaveVo = new QuestionSaveVo();
                    BeanUtils.copyProperties(question,questionSaveVo);
                    if(question.getType().equals(EnumQuestoinType.CHECKBOX.getKey())){
                        questionSaveVo.setChecksA(question.getCheckA());
                        questionSaveVo.setChecksB(question.getCheckB());
                        questionSaveVo.setChecksC(question.getCheckC());
                        questionSaveVo.setChecksD(question.getCheckD());
                    }
                    save(questionSaveVo);
                }catch (Exception e){
                    System.out.println("导入失败"+ JSONObject.toJSONString(question));
                }
            }
        }
        resultBean.success();
        return resultBean;
    }

    @Override
    public void delete(Long id) {
        dmsQuestionMapper.delete(id);
        dmsAnswerMapper.deleteByQuestionId(id);
    }

    @Override
    public QuestionAnswer queryById(Long id) {
        DmsQuestionVo dmsQuestionVo = dmsQuestionMapper.queryById(id);
        List<DmsAnswer> answers = dmsAnswerMapper.queryByQuestion(id);
        QuestionAnswer result = new QuestionAnswer();
        BeanUtils.copyProperties(dmsQuestionVo,result);
        result.setAnswers(answers);
        return result;
    }

    @Override
    public ResultPOListBean<DmsQuestionVo> queryPage(DmsQuestion question) {
        ResultPOListBean<DmsQuestionVo> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(question.getPageNo());
        queryBean.setPageRows(question.getPageRows());
        queryBean.setF(question.getPagingMap());

        int count = 0;
        try {
            count = dmsQuestionMapper.queryPageCount(queryBean);
            queryBean.resetTotalCount(count);
            List<DmsQuestionVo> menus = new ArrayList<>();
            if(count > 0){
                menus = dmsQuestionMapper.queryPage(queryBean);
            }
            result.success(menus,count);
            //分页信息
            BeanUtils.copyProperties(queryBean, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setValue(null);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean save(QuestionSaveVo question) {
        ResultBean resultBean = new ResultBean();
        validateParam(question);
        DmsQuestion dmsQuestion = new DmsQuestion();
        BeanUtils.copyProperties(question,dmsQuestion);
        if(question.getId() == null){
            Long id = dmsQuestionMapper.insertQuestion(dmsQuestion);
            List<DmsAnswer> answers = initAnswers(question,dmsQuestion.getId());
            if(answers != null && answers.size() > 0){
                for(DmsAnswer answer : answers){
                    dmsAnswerMapper.insert(answer);
                }
            }
        }else{
            int id = dmsQuestionMapper.updateByPrimaryKeySelective(dmsQuestion);
            //先删除答案
            dmsAnswerMapper.deleteByQuestionId(dmsQuestion.getId());
            List<DmsAnswer> answers = initAnswers(question,dmsQuestion.getId());
            if(answers != null && answers.size() > 0){
                for(DmsAnswer answer : answers){
                    dmsAnswerMapper.insert(answer);
                }
            }
        }
        return resultBean;
    }

    private List<DmsAnswer> initAnswers(QuestionSaveVo question,Long id) {
        List<DmsAnswer> answers = new ArrayList<>();
        if(question.getType().equals(EnumQuestoinType.RADIO.getKey())){
            DmsAnswer answer = null;
            if(question.getCheckA() != null) {
                answer = new DmsAnswer();
                answer.setCode("A");
                answer.setQuestionId(id);
                answer.setText(question.getCheckA());
                answers.add(answer);
            }
            if(question.getCheckB() != null) {
                answer = new DmsAnswer();
                answer.setCode("B");
                answer.setQuestionId(id);
                answer.setText(question.getCheckB());
                answers.add(answer);
            }
            if(question.getCheckC() != null) {
                answer = new DmsAnswer();
                answer.setCode("C");
                answer.setQuestionId(id);
                answer.setText(question.getCheckC());
                answers.add(answer);
            }
            if(question.getCheckD() != null) {
                answer = new DmsAnswer();
                answer.setCode("D");
                answer.setQuestionId(id);
                answer.setText(question.getCheckD());
                answers.add(answer);
            }
        }else if(question.getType().equals(EnumQuestoinType.CHECKBOX.getKey())){
            DmsAnswer answer = null;
            if(question.getChecksA() != null) {
                answer = new DmsAnswer();
                answer.setCode("A");
                answer.setQuestionId(id);
                answer.setText(question.getChecksA());
                answers.add(answer);
            }
            if(question.getChecksB() != null) {
                answer = new DmsAnswer();
                answer.setCode("B");
                answer.setQuestionId(id);
                answer.setText(question.getChecksB());
                answers.add(answer);
            }
            if(question.getChecksC() != null) {
                answer = new DmsAnswer();
                answer.setCode("C");
                answer.setQuestionId(id);
                answer.setText(question.getChecksC());
                answers.add(answer);
            }
            if(question.getChecksD() != null) {
                answer = new DmsAnswer();
                answer.setCode("D");
                answer.setQuestionId(id);
                answer.setText(question.getChecksD());
                answers.add(answer);
            }
        }
        return answers;
    }

    private void validateParam(QuestionSaveVo question) {
        if(question.getDepartmentId() == null){
            throw new LogicException("部门不能为空");
        }
        if(question.getScore() == null){
            throw new LogicException("分数不能为空");
        }
        if(question.getType() == EnumQuestoinType.RADIO.getKey()){
            if(question.getCheckA() == null && question.getCheckB() == null && question.getCheckC() == null && question.getCheckD() == null){
                throw new LogicException("答案类型不一致");
            }
        }
        if(question.getType() == EnumQuestoinType.CHECKBOX.getKey()){
            if(question.getChecksA() == null && question.getChecksB() == null && question.getChecksC() == null && question.getChecksD() == null){
                throw new LogicException("答案类型不一致");
            }
        }
        if(question.getAnswer() == null){
            throw new LogicException("正确答案不能为空");
        }
    }
}
