package com.visionbuilding.manage.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsRole;
import com.visionbuilding.manage.modle.entity.DmsUser;
import com.visionbuilding.manage.modle.entity.DmsUserRole;
import com.visionbuilding.manage.service.DmsRoleService;
import com.visionbuilding.manage.service.DmsUserService;
import com.visionbuilding.manage.utill.FormDataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/user")
public class DmsUserController extends BaseController {

    @Autowired
    private DmsUserService dmsUserService;

    @Autowired
    private DmsRoleService dmsRoleService;


    @RequestMapping("/user.html")
    public String user(HttpServletRequest request){
        return "sys/user/user_list";
    }

    @RequestMapping("/edit.html")
    public String edit(HttpServletRequest request,Long id){
        List<DmsRole> allRoles = new ArrayList<>();
        DmsUserRole userRole = new DmsUserRole();
        try {
            ResultPOBean<DmsUser> resultPOBean = dmsUserService.queryUserInfoByWid(id);
            resultPOBean.getValue().setPassword("******");
            allRoles = dmsRoleService.queryRoleByuser(id).getValue();
            userRole.setRoles(allRoles);
            BeanUtils.copyProperties(resultPOBean.getValue(),userRole);
            request.setAttribute("po",userRole);
            request.setAttribute("userpo",resultPOBean.getValue());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "sys/user/user_edit";
    }

    @RequestMapping("/add.html")
    public String add(HttpServletRequest request){
        List<DmsRole> allRoles = new ArrayList<>();
        DmsUserRole userRole = new DmsUserRole();
        try {
            allRoles = dmsRoleService.queryAllRole();
            userRole.setRoles(allRoles);
            request.setAttribute("po",userRole);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "sys/user/user_edit";
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(DmsUser user,HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsUser> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(user, request);
        resultPOListBean = dmsUserService.queryUserInfoByPage(user);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping("/saveuser")
    public String saveuser(DmsUser user,String roles)throws Exception{
        ResultBean resultBean = new ResultBean();
        try{
            dmsUserService.saveUserRoles(user,roles);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @RequestMapping("/deluser")
    @ResponseBody
    public String delUser(Long id){
        ResultBean resultBean = new ResultBean();
        try{
            dmsUserService.delUser(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("服务器异常");
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/resetZt")
    public String resetZt(Long id,Integer status){
        ResultBean resultBean = new ResultBean();
        try{
            DmsUser user = new DmsUser();
            user.setId(id);
            user.setStatus(status);
            dmsUserService.resetZt(user);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("服务器异常");
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/resetPassword")
    public String resetPassword(Long id){
        ResultBean resultBean = new ResultBean();
        try{
            dmsUserService.resetPassword(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("服务器异常");
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/getUserByDepartment")
    public String getUserByDepartment(Long department){
        List<DmsUser> users = new ArrayList<>();
        try{
            users = dmsUserService.getUserByDepartment(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(users);
    }
}
