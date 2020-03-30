package com.visionbuilding.manage.filter;

import com.visionbuilding.manage.modle.entity.DmsMenu;
import com.visionbuilding.manage.modle.entity.DmsUser;
import com.visionbuilding.manage.myenum.SessionAttributes;
import com.visionbuilding.manage.utill.ValidateUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class StaticUrlFilter  implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;

        //left菜单
        String chirld = request.getParameter("chirld");
        String parent = request.getParameter("parent");
        request.setAttribute("chirld",chirld);
        request.setAttribute("parent",parent);

        // String name = InetAddress.getLocalHost().getHostAddress();
        String name = request.getRemoteHost();
        int port = request.getServerPort();
        String path = request.getContextPath();
        String scheme = request.getScheme();
        // 获取ip
        request.getSession().setAttribute(SessionAttributes.DOMAIN, scheme + "://" + name + ":" + port + path);
//        request.getSession().setAttribute(SessionAttributes.DOMAIN, path);
        request.getSession().setAttribute(SessionAttributes.CSSJS, path);
        request.getSession().setAttribute(SessionAttributes.BASE, path);
        request.getSession().setAttribute(SessionAttributes.GETTIME, new Date().getTime());
        // 验证是否登֤
        DmsUser user= (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);

        HttpSession session =  request.getSession();
//        session.setMaxInactiveInterval(SESSION_MAX_TIME);
        session.setAttribute(SessionAttributes.USER_SESSION_NAME,user);
        String url = request.getRequestURI();
        // 对登录页 和登录放行
        if ("/SteelManage/index.html".equals(url) || "/SteelManage/login.html".equals(url) || "/SteelManage/adduser.html".equals(url)|| "/SteelManage/saveuserinfo.html".equals(url)|| "/SteelManage/404Exc.html".equals(url)) {
            arg2.doFilter(request, response);
        } else {
            // 非登录进行登录校验
            if (ValidateUtils.isNotEmptyObjectOrString(user)) {
                String userName = user.getUserName();
                //对admin放行
                if(userName.equals("admin")){
                    arg2.doFilter(request, response);
                }else {
                    //对主页放行
                    if("/SteelManage/main.html".equals(url)){
                        arg2.doFilter(request, response);
                    }else {
                        Map<String, DmsMenu> menu = (Map<String, DmsMenu>) session.getAttribute(SessionAttributes.MENU);
                        url = initUrl(url);
                        String key = url;
                        Object obj = menu.get(key);
//                        if (ValidateUtils.isNotEmptyObjectOrString(obj)) {
                            arg2.doFilter(request, response);
//                        } else {
//                            response.sendRedirect("/cscjglxt/404Exc.html");
//                        }
                    }
                }
            } else {
                response.sendRedirect("/SteelManage/index.html");
            }
        }
    }

    private String  initUrl(String url) {
        String str = "";
        String[] urls = url.split("/");
        for(int i =2;i<urls.length;i++){
            str += "/"+urls[i];
        }
        return str;
    }

    @Override
    public void init(FilterConfig arg0) {
//        NotHaveBug.notHaveBug();

    }

}
