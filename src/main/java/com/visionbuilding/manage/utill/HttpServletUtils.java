package com.visionbuilding.manage.utill;

import javax.servlet.http.HttpServletResponse;

public class HttpServletUtils {

    /**
     * 导出响应设置
     * @param response
     * @param type
     * @return
     */
    public static HttpServletResponse getResponse(HttpServletResponse response, String type,String name) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;fileName="+name+"."+type);
        return response;
    }

}
