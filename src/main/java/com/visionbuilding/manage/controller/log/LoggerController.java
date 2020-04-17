package com.visionbuilding.manage.controller.log;

import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.po.LoggerVo;
import com.visionbuilding.manage.modle.query.LoggerQuery;
import com.visionbuilding.manage.service.LoggerService;
import com.visionbuilding.manage.utill.FormDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/log/log")
public class LoggerController extends BaseController {

    @Autowired
    private LoggerService loggerService;

    @RequestMapping("/log.html")
    public String user(HttpServletRequest request, Long parentId){
        return "log/log_list";
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(LoggerQuery query, HttpServletRequest request)throws Exception{
        ResultPOListBean<LoggerVo> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(query, request);
        resultPOListBean = loggerService.queryPage(query);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }

}
