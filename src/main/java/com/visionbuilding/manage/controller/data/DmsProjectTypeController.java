package com.visionbuilding.manage.controller.data;

import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.entity.DmsProjectType;
import com.visionbuilding.manage.service.DmsProjectTypeService;
import com.visionbuilding.manage.utill.FormDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/data/projectType")
public class DmsProjectTypeController extends BaseController {
    @Autowired
    private DmsProjectTypeService dmsProjectTypeService;

    @RequestMapping("/projectType.html")
    public String user(HttpServletRequest request, Long parentId){
        return "data/projectType/projectType_list";
    }
    @RequestMapping("/edit.html")
    public String edit(HttpServletRequest request,Long id){
        DmsProjectType dmsProjectType = dmsProjectTypeService.selectByPrimaryKey(id);
        request.setAttribute("po",dmsProjectType);
        return "data/projectType/projectType_edit";
    }

    @RequestMapping("/add.html")
    public String add(HttpServletRequest request){
        return "data/projectType/projectType_edit";
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(DmsProjectType dmsProjectType, HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsProjectType> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(dmsProjectType, request);
        resultPOListBean = dmsProjectTypeService.queryPage(dmsProjectType);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }
    @ResponseBody
    @RequestMapping("/del")
    public String getDates(Long id)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            dmsProjectTypeService.deleteByPrimaryKey(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("系统异常");
        }
        return JSON.toJSONString(resultBean);
    }
    @ResponseBody
    @RequestMapping("/save")
    public String save(DmsProjectType dmsProjectType)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            if(dmsProjectType.getId() == null){
                dmsProjectTypeService.insertSelective(dmsProjectType);
            }else{
                dmsProjectTypeService.updateByPrimaryKeySelective(dmsProjectType);
            }
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("系统异常");
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public String getAll()throws Exception{
        List<DmsProjectType> resultBean = new ArrayList<>();
        try {
            resultBean = dmsProjectTypeService.getAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(resultBean);
    }
}
