package com.dteel.manage.controller.sys;

import com.alibaba.fastjson.JSON;

import com.dteel.manage.controller.BaseController;
import com.dteel.manage.exception.LogicException;
import com.dteel.manage.modle.ResultBean;
import com.dteel.manage.modle.ResultPOBean;
import com.dteel.manage.modle.ResultPOListBean;
import com.dteel.manage.modle.entity.DmsMenu;
import com.dteel.manage.modle.entity.DmsRole;
import com.dteel.manage.modle.po.DmsMenuPo;
import com.dteel.manage.myenum.LoginLXEnum;
import com.dteel.manage.service.DmsMenuService;
import com.dteel.manage.service.DmsRoleService;
import com.dteel.manage.utill.FormDataUtils;
import com.dteel.manage.utill.PojoUtils;
import com.dteel.manage.utill.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/role")
public class DmsRoleController extends BaseController {

    @Autowired
    private DmsRoleService dmsRoleService;

    @Autowired
    private DmsMenuService dmsMenuService;

    @RequestMapping("/role.html")
    public String user(HttpServletRequest request){
        return "sys/role/role_list";
    }

    @RequestMapping("/view.html")
    public String view(HttpServletRequest request,Long id){
        ResultPOBean<DmsRole> resultPOBean = dmsRoleService.queryRoleByWid(id);
        request.setAttribute("po",resultPOBean.getValue());

        ResultPOListBean<DmsMenu> resultPOListBean = new ResultPOListBean<>();
        resultPOListBean = dmsMenuService.querMenuByParent(0L);
        List<DmsMenuPo> allList = new ArrayList<>();
        List<DmsMenu> list = resultPOListBean.getValue();

        List<DmsMenu> checkList = dmsMenuService.queryMenuByRoleWid(id);
        allList = getChackedMenu(list,checkList);
        String treeStr = getZtreeStrPo(allList,id);
        request.setAttribute("menuTree",treeStr);

        return "sys/role/role_view";
    }

    @RequestMapping("/edit.html")
    public String edit(HttpServletRequest request,Long id){
        ResultPOBean<DmsRole> resultPOBean = dmsRoleService.queryRoleByWid(id);
        request.setAttribute("po",resultPOBean.getValue());

        ResultPOListBean<DmsMenu> resultPOListBean = new ResultPOListBean<>();
        resultPOListBean = dmsMenuService.querMenuByParent(0L);
        List<DmsMenuPo> allList = new ArrayList<>();
        List<DmsMenu> list = resultPOListBean.getValue();

        List<DmsMenu> checkList = dmsMenuService.queryEditZtreeMenusByRole(id);
        allList = getChackedMenu(list,checkList);
        String treeStr = getZtreeStrPo(allList,id);
        request.setAttribute("menuTree",treeStr);

        return "sys/role/role_edit";
    }

    private String getZtreeStrPo(List<DmsMenuPo> pos,Long roleId) {
        StringBuilder zTreeStr = new StringBuilder();
        String menuTree = "";
        if (ValidateUtils.isNotEmptyCollection(pos)) {
            for (DmsMenuPo po : pos) {
                    ResultPOListBean<DmsMenu> resultPOListBean = new ResultPOListBean<>();
                    resultPOListBean = dmsMenuService.querMenuByParent(po.getId());
                    List<DmsMenu> childList = resultPOListBean.getValue();
                    List<DmsMenu> checkList = dmsMenuService.queryEditZtreeMenusByRole(roleId);
                    List<DmsMenuPo>  list= new ArrayList<>();
                    list = getChackedMenu(childList,checkList);
                    Integer by1 = po.getChecked();
                    if(by1 != null && LoginLXEnum.DL.getKey()==by1) {
                        zTreeStr.append("{ \"id\":\"").append(po.getId()).append("\",\" pId\":\"").append(po.getMenuPid())
                                .append("\", \"open\":true"). append(", \"checked\":true").append(", \"name\":\"").append(po.getMenuName()).append("\",\"children\":[");
                        zTreeStr.append(PojoUtils.getZtreeStrPo(list));
                        zTreeStr.append("]").append("},");
                    }else{
                        zTreeStr.append("{ \"id\":\"").append(po.getId()).append("\",\" pId\":\"").append(po.getMenuPid())
                                .append("\", \"open\":true").append(", \"name\":\"").append(po.getMenuName()).append("\",\"children\":[");
                        zTreeStr.append(PojoUtils.getZtreeStrPo(list));
                        zTreeStr.append("]").append("},");
                    }
            }
            if (zTreeStr.length() > 0) {
                menuTree = zTreeStr.toString().substring(0, zTreeStr.toString().length() - 1);
            }
        }
        return menuTree;
    }

    /**
     * by1字段区分开已有菜单
     * @param list
     * @param checkList
     * @return
     */
    private List<DmsMenuPo> getChackedMenu(List<DmsMenu> list, List<DmsMenu> checkList) {
        List<DmsMenuPo> allMenu = new ArrayList<>();
        list.forEach(menu -> {
            DmsMenuPo po = new DmsMenuPo();
            BeanUtils.copyProperties(menu,po);
            boolean flag = true;
            for(DmsMenu checkedMenu : checkList){
                if(flag) {
                    if (menu.getId().equals(checkedMenu.getId())) {
                        po.setChecked(LoginLXEnum.DL.getKey());
                        flag = false;
                    } else {
                        po.setChecked(LoginLXEnum.ZX.getKey());
                    }
                }
            }
            allMenu.add(po);
        });
        return allMenu;
    }

    @RequestMapping("/addRole.html")
    public String addRole(HttpServletRequest request){
        ResultPOListBean<DmsMenu> resultPOListBean = new ResultPOListBean<>();
        resultPOListBean = dmsMenuService.querMenuByParent(0L);
        List<DmsMenu> list = resultPOListBean.getValue();
        List<DmsMenuPo> lists = new ArrayList<>();
        for(DmsMenu menu : list){
            DmsMenuPo po = new DmsMenuPo();
            BeanUtils.copyProperties(menu,po);
            lists.add(po);
        }
        String treeStr = getZtreeStrPo(lists);
        request.setAttribute("menuTree",treeStr);
        return "sys/role/role_edit";
    }

    private String getZtreeStrPo(List<DmsMenuPo> pos) {
        StringBuilder zTreeStr = new StringBuilder();
        String menuTree = "";
        if (ValidateUtils.isNotEmptyCollection(pos)) {
            for (DmsMenuPo po : pos) {
                ResultPOListBean<DmsMenu> resultPOListBean = new ResultPOListBean<>();
                resultPOListBean = dmsMenuService.querMenuByParent(po.getId());
                List<DmsMenu> childLists = resultPOListBean.getValue();
                List<DmsMenuPo> childList = new ArrayList<>();
                for(DmsMenu menu : childLists){
                    DmsMenuPo ss = new DmsMenuPo();
                    BeanUtils.copyProperties(menu,ss);
                    childList.add(ss);
                }
                Integer by1 = po.getChecked();
                if(by1 != null && LoginLXEnum.DL.getKey().equals(by1)) {
                    zTreeStr.append("{ \"id\":\"").append(po.getId()).append("\",\"pId\":\"").append(po.getMenuPid())
                            .append("\", \"open\":true"). append(", \"checked\":true").append(", \"name\":\"").append(po.getMenuName()).append("\",\"children\":[");
                    zTreeStr.append(PojoUtils.getZtreeStrPo(childList));
                    zTreeStr.append("]").append("},");
                }else{
                    zTreeStr.append("{ \"id\":\"").append(po.getId()).append("\",\" pId\":\"").append(po.getMenuPid())
                            .append("\", \"open\":true").append(", \"name\":\"").append(po.getMenuName()).append("\",\"children\":[");
                    zTreeStr.append(PojoUtils.getZtreeStrPo(childList));
                    zTreeStr.append("]").append("},");
                }
            }
            if (zTreeStr.length() > 0) {
                menuTree = zTreeStr.toString().substring(0, zTreeStr.toString().length() - 1);
            }
        }
        return menuTree;
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(DmsRole role, HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsRole> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(role, request);
        resultPOListBean = dmsRoleService.queryRoleInfoByPage(role);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping("/saveRole")
    public String saveMenu(DmsRole role){
        ResultPOBean<DmsRole> resultBean = new ResultPOBean();
        try{
            resultBean = dmsRoleService.saveRole(role);
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/saveRoleAndMenus")
    public String saveRoleAndMenus(Long roleId,String menuIds){
        ResultBean resultBean = new ResultBean();
        try{
            List<Long> menus = new ArrayList<>();
            String[] menusArr = menuIds.split(",");
            for (String menuId : menusArr){
                if(menuId != null && !"".equals(menuId)){
                    menus.add(new Long(menuId));
                }
            }
            resultBean = dmsRoleService.saveRoleAndMenus(roleId,menus);
            resultBean.success();
        }catch (LogicException e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/delRole")
    public String delRole(Long id){
        ResultBean resultBean = new ResultBean();
        try{
            dmsRoleService.delRole(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("服务器异常");
        }
        return JSON.toJSONString(resultBean);
    }
}
