package com.visionbuilding.manage.controller.test;

import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsAnswer;
import com.visionbuilding.manage.modle.entity.DmsQuestion;
import com.visionbuilding.manage.modle.po.DmsQuestionVo;
import com.visionbuilding.manage.modle.po.QuestionAnswer;
import com.visionbuilding.manage.modle.po.QuestionSaveVo;
import com.visionbuilding.manage.myenum.EnumQuestoinType;
import com.visionbuilding.manage.service.DmsQuestionService;
import com.visionbuilding.manage.utill.FormDataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/test/question")
public class QuestionController extends BaseController {

    @Autowired
    private DmsQuestionService dmsQuestionService;

    @RequestMapping("/question.html")
    public String department(HttpServletRequest request){
        return "test/question/question_list";
    }

    @RequestMapping("/add.html")
    public String add(HttpServletRequest request){
        return "test/question/question_edit";
    }
    @RequestMapping("/import.html")
    public String importQuestion(HttpServletRequest request){
        return "test/question/question_import";
    }

    @RequestMapping("/edit.html")
    public String edit(HttpServletRequest request,Long id){
        QuestionAnswer po =  dmsQuestionService.queryById(id);
        QuestionSaveVo result = new QuestionSaveVo();
        BeanUtils.copyProperties(po,result);
        for(DmsAnswer answer : po.getAnswers()){
            if(po.getType() == EnumQuestoinType.RADIO.getKey()){
                //单选
               if(answer.getCode().equals("A")){
                   result.setCheckA(answer.getText());
               }else if(answer.getCode().equals("B")) {
                   result.setCheckB(answer.getText());
               }else if(answer.getCode().equals("C")){
                   result.setCheckC(answer.getText());
               }else if(answer.getCode().equals("D")){
                   result.setCheckD(answer.getText());
               }
            }else if(po.getType() == EnumQuestoinType.CHECKBOX.getKey()){
                if(answer.getCode().equals("A")){
                    result.setChecksA(answer.getText());
                }else if(answer.getCode().equals("B")) {
                    result.setChecksB(answer.getText());
                }else if(answer.getCode().equals("C")){
                    result.setChecksC(answer.getText());
                }else if(answer.getCode().equals("D")){
                    result.setChecksD(answer.getText());
                }
            }
        }
        request.setAttribute("po",result);
        return "test/question/question_edit";
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(DmsQuestion question, HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsQuestionVo> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(question, request);
        resultPOListBean = dmsQuestionService.queryPage(question);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping("/save")
    public String save(QuestionSaveVo question)throws Exception{
        ResultBean resultBean = new ResultBean();
        try{
            resultBean = dmsQuestionService.save(question);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/del")
    public String del(Long id, HttpServletRequest request)throws Exception{
        ResultBean resultBean = new ResultBean();
        try{
            dmsQuestionService.delete(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }
}
