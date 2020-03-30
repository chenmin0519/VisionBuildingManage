package com.visionbuilding.manage.utill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.visionbuilding.manage.modle.query.BaseQuery;


/**
 *	数据格式化
 * @author min.chen
 * @date: 2017年8月12日 上午9:05:37
 * @version 1.0
 * @since JDK 1.8
 */
public class FormDataUtils {
	  /**
     * 
     * 获取DataTable需要的数据格式
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
     */
    public static void setQueryParamter(BaseQuery query, HttpServletRequest request) {
        int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
        int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
        query.setPageNoRows(iDisplayStart, iDisplayLength);
    };

}
