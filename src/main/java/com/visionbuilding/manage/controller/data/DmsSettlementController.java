package com.visionbuilding.manage.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.visionbuilding.manage.modle.ResultPOBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.entity.DmsUser;
import com.visionbuilding.manage.modle.po.EchartPo;
import com.visionbuilding.manage.modle.po.StaticsBusinessPo;
import com.visionbuilding.manage.modle.po.StaticsMouthsPo;
import com.visionbuilding.manage.modle.po.StaticsUserPo;
import com.visionbuilding.manage.modle.query.DepartmentEchartQuery;
import com.visionbuilding.manage.myenum.SessionAttributes;
import com.visionbuilding.manage.service.DmsSettlementService;
import com.visionbuilding.manage.utill.FormDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/data/settlement")
public class DmsSettlementController {

    @Autowired
    private DmsSettlementService dmsSettlementService;

    @RequestMapping("/getEchartInfo")
    @ResponseBody
    public String getEchartInfo(){
        ResultPOBean<EchartPo> result = new ResultPOBean<>();
//        EchartPo echartPo = dmsSettlementService.getEchartInfo();
        EchartPo echartPo = dmsSettlementService.getEchartInfoBussiness();
        result.success(echartPo);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping("/getBusinessEchartInfo")
    @ResponseBody
    public String getBusinessEchartInfo(DmsMainProject dmsProjectType){
        ResultPOBean<EchartPo> result = new ResultPOBean<>();
//        EchartPo echartPo = dmsSettlementService.getEchartInfo();
        EchartPo echartPo = dmsSettlementService.getBusinessEchartInfo(dmsProjectType);
        result.success(echartPo);
        return JSONObject.toJSONString(result);
    }
    @RequestMapping("/getBusinessEchartInfoPage")
    @ResponseBody
    public String getBusinessEchartInfoPage(DmsMainProject dmsProjectType, HttpServletRequest request){
        ResultPOListBean<StaticsMouthsPo> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(dmsProjectType, request);
        resultPOListBean = dmsSettlementService.getBusinessEchartInfoPage(dmsProjectType);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSONObject.toJSONString(map);
    }

    @RequestMapping("/getdepartmentEchartInfo")
    @ResponseBody
    public String getdepartmentEchartInfo(@RequestParam(name = "startTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                                          @RequestParam(name = "endTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime){
        ResultPOBean<EchartPo> result = new ResultPOBean<>();
//        EchartPo echartPo = dmsSettlementService.getEchartInfo();
        EchartPo echartPo = dmsSettlementService.getdepartmentEchartInfo(startTime,endTime);
        result.success(echartPo);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping("/getdepartmentEchartInfoPage")
    @ResponseBody
    public String getdepartmentEchartInfoPage(DepartmentEchartQuery dmsProjectType,HttpServletRequest request){
        ResultPOListBean<StaticsBusinessPo> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(dmsProjectType, request);
        resultPOListBean = dmsSettlementService.getdepartmentEchartInfoPage(dmsProjectType);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSONObject.toJSONString(map);
    }

    @RequestMapping("/getUserEchartInfo")
    @ResponseBody
    public String getUserEchartInfo(@RequestParam(name = "startTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                                    @RequestParam(name = "endTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
                                    HttpServletRequest request){
        ResultPOBean<EchartPo> result = new ResultPOBean<>();
        DmsUser user = (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);
//        EchartPo echartPo = dmsSettlementService.getEchartInfo();
        EchartPo echartPo = dmsSettlementService.getUserEchartInfo(startTime,endTime,user.getDepartment());
        result.success(echartPo);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping("/getUserEchartInfoPage")
    @ResponseBody
    public String getUserEchartInfoPage(DepartmentEchartQuery dmsProjectType,HttpServletRequest request){
        ResultPOListBean<StaticsUserPo> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(dmsProjectType, request);
        DmsUser user = (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);
        dmsProjectType.setDepartment(user.getDepartment());
        resultPOListBean = dmsSettlementService.getUserEchartInfoPage(dmsProjectType);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSONObject.toJSONString(map);
    }
}
