package com.dteel.manage.controller.sys;

import com.alibaba.fastjson.JSON;
import com.dteel.manage.controller.BaseController;
import com.dteel.manage.exception.LogicException;
import com.dteel.manage.modle.ResultBean;
import com.dteel.manage.modle.ResultPOBean;
import com.dteel.manage.modle.ResultPOListBean;
import com.dteel.manage.modle.entity.DmsMenu;
import com.dteel.manage.modle.po.ZTreePojo;
import com.dteel.manage.service.DmsMenuService;
import com.dteel.manage.utill.FormDataUtils;
import com.dteel.manage.utill.PojoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/menu")
public class DmsMenuController extends BaseController {

    @Autowired
    private DmsMenuService dmsMenuService;

    @RequestMapping("/menu.html")
    public String user(HttpServletRequest request,Long parentId){
        ResultPOListBean<DmsMenu> resultPOListBean = new ResultPOListBean<>();
        if(parentId == null){
            parentId = 0L;
        }
        resultPOListBean = dmsMenuService.querMenuByParent(parentId);
        List<DmsMenu> list = resultPOListBean.getValue();
        String treeStr = PojoUtils.getZtreeStr4Po(list);
        request.setAttribute("menuTree",treeStr);
        return "sys/menu/menu_list";
    }

    @RequestMapping("/addMenu.html")
    public String addMenu(HttpServletRequest request,String parentId){
        request.setAttribute("menuPid",parentId);
        return "sys/menu/menu_edit";
    }

    @RequestMapping("/edit")
    public String editMenu(HttpServletRequest request,Long id,Long parentId){
        ResultPOBean<DmsMenu> resultPOBean = dmsMenuService.queryMenuById(id);
        request.setAttribute("po",resultPOBean.getValue());
        request.setAttribute("menuPid",parentId);
        return "sys/menu/menu_edit";
    }


    @ResponseBody
    @RequestMapping("/getDatas")
    public String getDatas(DmsMenu menu,HttpServletRequest request){
        ResultPOListBean<DmsMenu> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(menu, request);
        resultPOListBean = dmsMenuService.queryMenuInfoByPage(menu);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping("/saveMenu")
    public String saveMenu(DmsMenu menu){
        ResultBean resultBean = new ResultBean();
        try{
            if(menu.getId() == null) {
                if(menu.getMenuPid() != null){
                }else{
                    menu.setMenuPid(0L);
                }
                resultBean = dmsMenuService.insertMenu(menu);
                resultBean.success();
            }else{
                resultBean = dmsMenuService.updateMenu(menu);
                resultBean.success();
            }
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/queryChildByParentId")
    public String queryChildByParentId(Long parentId){
        if(parentId == null){
            return JSON.toJSONString("");
        }
        ResultPOListBean<DmsMenu> resultPOListBean = dmsMenuService.querMenuByParent(parentId);
        List<DmsMenu> list = new ArrayList<>();
        list = resultPOListBean.getValue();
        List<ZTreePojo> pojos = new ArrayList<>();
        list.forEach( menu ->{
            ZTreePojo zTreePojo = new ZTreePojo();
            zTreePojo.setId(menu.getId());
            zTreePojo.setPId(menu.getMenuPid());
            zTreePojo.setName(menu.getMenuName());
            pojos.add(zTreePojo);
        });
        return JSON.toJSONString(pojos);
    }

    @ResponseBody
    @RequestMapping("/queryMenuById")
    public String queryMenuById(Long id){
        ResultPOBean<DmsMenu> resultPOBean = new ResultPOBean<>();
        try{
            resultPOBean = dmsMenuService.queryMenuById(id);
        }catch (LogicException e){
            e.printStackTrace();
            resultPOBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultPOBean);
    }

    @ResponseBody
    @RequestMapping("/deluMenu")
    public String deluMenu(Long id){
        ResultBean resultBean = new ResultBean();
        try{
            resultBean = dmsMenuService.delMenu(id);
            resultBean.success();
        }catch (LogicException e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/resetMenuZt")
    public String resetMenuZt(Long id,Integer status){
        ResultBean resultBean = new ResultBean();
        try{
            resultBean = dmsMenuService.resetMenuZt(id,status);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }
}
