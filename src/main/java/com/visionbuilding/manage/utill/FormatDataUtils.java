package com.visionbuilding.manage.utill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.visionbuilding.manage.modle.query.BaseQuery;

public class FormatDataUtils {
    /**
     * 
     * 获取DataTable需要的数据格式
     * 
     * @author min.chen
     * @version 1.0
     *
     * @param list 查询结果list对象
     * @param count 总条数
     * @return map
     */
    public static <T> Map<String, Object> getFormDataMap(List<T> list, int count) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("aaData", list);
        resultMap.put("iTotalRecords", count);
        resultMap.put("iTotalDisplayRecords", count);
        return resultMap;
    }

    /**
     * 
     * 将前台传来的分页参数封装到query对象
     * 
     * @author min.chen
     * @version 1.0
     *
     * @param query
     * @param request
     */
    public static void setQueryParamter(BaseQuery query, HttpServletRequest request) {
        int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
        int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
        query.setPageNo(iDisplayStart);
        query.setPageRows(iDisplayLength);
    };
}
