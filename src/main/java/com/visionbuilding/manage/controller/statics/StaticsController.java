package com.visionbuilding.manage.controller.statics;

import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.po.DepartmentStaticsPo;
import com.visionbuilding.manage.modle.po.ExportDepartmentExclePo;
import com.visionbuilding.manage.modle.po.ExportExclePo;
import com.visionbuilding.manage.modle.query.StaticsQuery;
import com.visionbuilding.manage.service.StaticsService;
import com.visionbuilding.manage.utill.ExcelGenerateUtils;
import com.visionbuilding.manage.utill.FormDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/statics/statics")
public class StaticsController extends BaseController {

    @Autowired
    private StaticsService staticsService;

    @RequestMapping("/department_statics.html")
    public String departmentStatics(HttpServletRequest request, Long parentId){
        return "statics/department_statics_list";
    }

    @RequestMapping("/all_statics.html")
    public String allStatics(HttpServletRequest request, Long parentId){
        return "statics/statics_list";
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(StaticsQuery department, HttpServletRequest request)throws Exception{
        ResultPOListBean<DepartmentStaticsPo> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(department, request);
        resultPOListBean = staticsService.queryPage(department);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }

    @RequestMapping("/export_department_statics.html")
    public void exportDepartmentStatics(StaticsQuery department, HttpServletRequest request, HttpServletResponse response){
        List<ExportDepartmentExclePo> lists = staticsService.getData(department);
        ExcelGenerateUtils<ExportDepartmentExclePo> excleGenerateUtils = new ExcelGenerateUtils<>();
        try {
            excleGenerateUtils.exportExcel(response,"设计院统计结果",ExportDepartmentExclePo.class,lists);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/export_statics.html")
    public void exportStatics(StaticsQuery department, HttpServletRequest request, HttpServletResponse response){
        List<ExportExclePo> lists = staticsService.getAllData(department);
        ExcelGenerateUtils<ExportExclePo> excleGenerateUtils = new ExcelGenerateUtils<>();
        try {
            String name = LocalDate.now().getDayOfYear()+"年设计费用汇总";
            excleGenerateUtils.exportExcel(response,name,ExportExclePo.class,lists);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
