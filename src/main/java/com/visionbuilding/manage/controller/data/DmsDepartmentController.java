package com.visionbuilding.manage.controller.data;

import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.*;
import com.visionbuilding.manage.service.DmsDepartmentService;
import com.visionbuilding.manage.utill.FormDataUtils;
import com.visionbuilding.manage.utill.PojoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/data/department")
public class DmsDepartmentController  extends BaseController {

    @Autowired
    private DmsDepartmentService dmsDepartmentService;

    @RequestMapping("/department.html")
    public String user(HttpServletRequest request, Long parentId){
        return "data/department/department_list";
    }
    @RequestMapping("/edit.html")
    public String edit(HttpServletRequest request,Long id){
        DmsDepartment department = dmsDepartmentService.selectByPrimaryKey(id);
        request.setAttribute("po",department);
        return "data/department/department_edit";
    }

    @RequestMapping("/add.html")
    public String add(HttpServletRequest request){
        return "data/department/department_edit";
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(DmsDepartment department, HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsDepartment> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(department, request);
        resultPOListBean = dmsDepartmentService.queryPage(department);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }
    @ResponseBody
    @RequestMapping("/del")
    public String getDates(Long id)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            dmsDepartmentService.deleteByPrimaryKey(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("系统异常");
        }
        return JSON.toJSONString(resultBean);
    }
    @ResponseBody
    @RequestMapping("/save")
    public String save(DmsDepartment department)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            if(department.getId() == null){
                dmsDepartmentService.insertSelective(department);
            }else{
                dmsDepartmentService.updateByPrimaryKeySelective(department);
            }
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("系统异常");
        }
        return JSON.toJSONString(resultBean);
    }

}
