package com.visionbuilding.manage.utill;

import com.alibaba.fastjson.JSONArray;

public class JSONUtils {

	 /**
     * 将对象序列化为JSON文本
     * @author min.chen
     * @date: 2017年8月12日 上午10:39:40
     * @version 1.0
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSONArray.toJSONString(object);
    }


}