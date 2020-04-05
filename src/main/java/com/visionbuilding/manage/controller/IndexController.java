package com.visionbuilding.manage.controller;



import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.exception.LogicException;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.entity.DmsMenu;
import com.visionbuilding.manage.modle.entity.DmsUser;
import com.visionbuilding.manage.modle.po.DmsUserMenus;
import com.visionbuilding.manage.myenum.SessionAttributes;
import com.visionbuilding.manage.service.DmsMenuService;
import com.visionbuilding.manage.service.DmsUserService;
import com.visionbuilding.manage.utill.EncryptionUtils;
import com.visionbuilding.manage.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoginService loginService;

    @Autowired
    private DmsMenuService dmsMenuService;

    @Autowired
    private DmsUserService dmsUserService;

    @RequestMapping("/index.html")
    public String index() {
        return "login";
    }

    @RequestMapping("/main.html")
    public String main() {
        return "sys/main";
    }

    @RequestMapping("/404Exc.html")
    public String notFund(){
        return "common/404Exc";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        DmsUser user = (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);
        if(user == null){
            request.getSession().removeAttribute(SessionAttributes.USER_SESSION_NAME);
            return "login";
        }
        request.getSession().removeAttribute(SessionAttributes.USER_SESSION_NAME);
        return "login";
    }

    @RequestMapping("/resetPwd.html")
    public String resetPwd(HttpServletRequest request){
        return "resetPwd";
    }

    @ResponseBody
    @RequestMapping("/resetPwd")
    public String saveResetPwd(HttpServletRequest request,String oldPwd,String newPwd,String newPwdAgin){
        ResultBean resultBean = new ResultBean();
        try {
            DmsUser user = (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);
            String userName = user.getUserName();
//            String oldPassword = user.getPassword();
            oldPwd = EncryptionUtils.getCiphertext(oldPwd);
            user = loginService.login(userName,oldPwd);
            if (user == null) {
                throw new LogicException("密码错误！");
            }
            if (newPwd == null || "".equals(newPwd) || newPwdAgin == null) {
                throw new LogicException("新密码不能为空！");
            }
            if (!newPwd.equals(newPwdAgin)) {
                throw new LogicException("两次密码不一致！");
            }
            dmsUserService.resetPwd(userName, EncryptionUtils.getCiphertext(newPwd));
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, String userName, String password){
        ResultBean resultBean = new ResultBean();
        DmsUser user;
        try {
            password = EncryptionUtils.getCiphertext(password);
            user = loginService.login(userName, password);
            logger.info("哈哈哈哈哈哈哈哈哈哈哈哈");
            if(user == null){
                resultBean.failure("用户名密码错误");
                return JSON.toJSONString(resultBean);
            }
            HttpSession session =  request.getSession();
//            session.setMaxInactiveInterval(SESSION_MAX_TIME);
            session.setAttribute(SessionAttributes.USER_SESSION_NAME,user);
            initUserRoleMenu(request,user);
            resultBean.success();
            return JSON.toJSONString(resultBean);
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("服务器异常");
            return JSON.toJSONString(resultBean);
        }
    }

    /**
     * 加载用户资源
     * @param user
     */
    private void initUserRoleMenu(HttpServletRequest request,DmsUser user) {
        //权限判断
        List<DmsMenu> menus = new ArrayList<>();
        if("admin".equals(user.getUserName())) {
            menus = dmsMenuService.queryAllMenu();
        }else{
            menus = dmsMenuService.queryUserMenu(user.getId());
        }
        Map<String,DmsMenu> map = new HashMap<>();
        menus.forEach(menu -> {
            map.put(menu.getMenuUrl(),menu);
        });
        request.getSession().setAttribute(SessionAttributes.MENU,map);
        //菜单初始化
        List<DmsUserMenus> dmsUserMenus = initUserMenus(menus);
        request.getSession().setAttribute(SessionAttributes.MENUSINFO, dmsUserMenus);
    }

    /**
     * 格式化用户菜单数据格式
     * @param menus
     * @return
     */
    private List<DmsUserMenus> initUserMenus(List<DmsMenu> menus) {
        List<DmsUserMenus> result = new ArrayList<>();
        menus.forEach(menu -> {
            DmsUserMenus dmsUserMenus = new DmsUserMenus();
            if("1".equals(menu.getLv())){
                BeanUtils.copyProperties(menu, dmsUserMenus);
                //二级菜单
                List<DmsMenu> chrildMenus = new ArrayList<>();
                menus.forEach(chrildMenu ->{
                    if(chrildMenu.getMenuPid().equals(menu.getId())){
                        chrildMenus.add(chrildMenu);
                    }
                });
                dmsUserMenus.setChrildMenus(chrildMenus);
                result.add(dmsUserMenus);
            }
        });
        return result;
    }
}
