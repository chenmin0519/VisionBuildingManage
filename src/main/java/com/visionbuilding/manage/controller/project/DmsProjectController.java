package com.visionbuilding.manage.controller.project;

import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsChildProject;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.service.DmsMainProjectService;
import com.visionbuilding.manage.utill.FormDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/project/main-project")
public class DmsProjectController extends BaseController {

    @Autowired
    private DmsMainProjectService dmsMainProjectService;


    @RequestMapping("/project.html")
    public String user(HttpServletRequest request, Long parentId){
        return "project/mainProject/project_list";
    }

    @RequestMapping("/edit.html")
    public String edit(HttpServletRequest request,Long id){
        DmsMainProject dmsMainProject = dmsMainProjectService.selectByPrimaryKey(id);
        request.setAttribute("po",dmsMainProject);
        return "project/mainProject/project_edit";
    }

    @RequestMapping("/add.html")
    public String add(HttpServletRequest request){
        return "project/mainProject/project_edit";
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(DmsMainProject dmsMainProject, HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsMainProject> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(dmsMainProject, request);
        resultPOListBean = dmsMainProjectService.queryPage(dmsMainProject);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }
    @ResponseBody
    @RequestMapping("/del")
    public String del(Long id)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            dmsMainProjectService.deleteByPrimaryKey(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("系统异常");
        }
        return JSON.toJSONString(resultBean);
    }
    @ResponseBody
    @RequestMapping("/save")
    public String save(DmsMainProject dmsMainProject)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            if(dmsMainProject.getId() == null){
                dmsMainProjectService.insertSelective(dmsMainProject);
            }else{
                dmsMainProjectService.updateByPrimaryKeySelective(dmsMainProject);
            }
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("系统异常");
        }
        return JSON.toJSONString(resultBean);
    }


    @RequestMapping("/addSub.html")
    public String addSubProject(HttpServletRequest request){
        return "project/subProject/sub_project_edit";
    }

    @ResponseBody
    @RequestMapping("/save-sub-project")
    public String saveSubProject(DmsChildProject dmsChildProject)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            if(dmsChildProject.getId() == null){
                dmsMainProjectService.insertSelectiveChild(dmsChildProject);
            }else{
                dmsMainProjectService.updateByPrimaryKeySelectiveChild(dmsChildProject);
            }
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("系统异常");
        }
        return JSON.toJSONString(resultBean);
    }
}
