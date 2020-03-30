package com.dteel.manage.controller.test;

import com.alibaba.fastjson.JSON;
import com.dteel.manage.controller.BaseController;
import com.dteel.manage.modle.ResultBean;
import com.dteel.manage.modle.ResultPOListBean;
import com.dteel.manage.modle.entity.DmsDepartment;
import com.dteel.manage.service.DmsDepartmentService;
import com.dteel.manage.utill.FormDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/test/department")
public class DepartmentController extends BaseController {
    @Autowired
    private DmsDepartmentService dmsDepartmentService;

    @RequestMapping("/department.html")
    public String department(HttpServletRequest request){
        return "test/department/department_list";
    }
    @RequestMapping("/add.html")
    public String add(HttpServletRequest request){
        return "test/department/department_edit";
    }
    @RequestMapping("/edit.html")
    public String edit(HttpServletRequest request,Long id){
        DmsDepartment po =  dmsDepartmentService.queryById(id);
        request.setAttribute("po",po);
        return "test/department/department_edit";
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
    @RequestMapping("/save")
    public String save(DmsDepartment department)throws Exception{
        ResultBean resultBean = new ResultBean();
        try{
            if(department.getId() == null) {
                resultBean = dmsDepartmentService.insert(department);
                resultBean.success();
            }else{
                resultBean = dmsDepartmentService.update(department);
                resultBean.success();
            }
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
            dmsDepartmentService.delete(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/departmentAll")
    public String departmentAll()throws Exception{
        ResultPOListBean<DmsDepartment> resultPOListBean = new ResultPOListBean<>();
        try{
            resultPOListBean = dmsDepartmentService.queryAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(resultPOListBean);
    }
}
