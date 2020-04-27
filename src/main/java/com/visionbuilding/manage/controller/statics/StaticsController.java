package com.visionbuilding.manage.controller.statics;

import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.po.DepartmentStaticsPo;
import com.visionbuilding.manage.modle.po.ExportDepartmentExclePo;
import com.visionbuilding.manage.modle.po.ExportExclePo;
import com.visionbuilding.manage.modle.query.StaticsQuery;
import com.visionbuilding.manage.service.DmsMainProjectService;
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

    @Autowired
    private DmsMainProjectService dmsMainProjectService;

    @RequestMapping("/department_statics.html")
    public String departmentStatics(HttpServletRequest request, Long parentId){
        return "statics/department_statics_list";
    }

    @RequestMapping("/all_statics.html")
    public String allStatics(HttpServletRequest request, Long parentId){
        return "statics/statics_list";
    }

    @RequestMapping("/department_statics_detail.html")
    public String edit(HttpServletRequest request,Long id){
        DmsMainProject dmsMainProject = dmsMainProjectService.selectByPrimaryKey(id);
        request.setAttribute("po",dmsMainProject);
        return "statics/department_statics_detail";
    }

    @RequestMapping("/statics_detail.html")
    public String staticsDetail(HttpServletRequest request,Long id){
        DmsMainProject dmsMainProject = dmsMainProjectService.selectByPrimaryKey(id);
        request.setAttribute("po",dmsMainProject);
        return "statics/statics_detail";
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
        Map<String,Object> map = staticsService.getData(department,2);
        ExcelGenerateUtils<ExportDepartmentExclePo> excleGenerateUtils = new ExcelGenerateUtils<>();
        try {
            List<String> names = (List<String>) map.get("names");
            List<Map<String,String>> datas = (List<Map<String, String>>) map.get("resultList");
            excleGenerateUtils.exportExcelByMap(response,"设计院统计结果",names,datas);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/export_statics.html")
    public void exportStatics(StaticsQuery department, HttpServletRequest request, HttpServletResponse response){
//        List<ExportExclePo> lists = staticsService.getAllData(department);
        Map<String,Object> map = staticsService.getData(department,1);
        ExcelGenerateUtils<ExportExclePo> excleGenerateUtils = new ExcelGenerateUtils<>();
        try {
            String name = LocalDate.now().getYear()+"年设计费用汇总";
            List<String> names = (List<String>) map.get("names");
            List<Map<String,String>> datas = (List<Map<String, String>>) map.get("resultList");
            excleGenerateUtils.exportExcelByMap(response,name,names,datas);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
