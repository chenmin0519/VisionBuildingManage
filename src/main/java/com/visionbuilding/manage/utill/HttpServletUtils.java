package com.visionbuilding.manage.utill;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class HttpServletUtils {

    /**
     * 导出响应设置
     * @param response
     * @param type
     * @return
     */
    public static HttpServletResponse getResponse(HttpServletResponse response, String type,String name) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;fileName="+new String(name.getBytes("utf-8"),"ISO-8859-1" )+"."+type);
        return response;
    }

}
