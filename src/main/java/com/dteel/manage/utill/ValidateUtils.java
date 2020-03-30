package com.dteel.manage.utill;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

/**
 *	校验类
 * @author min.chen
 * @date: 2017年8月12日 上午9:05:37
 * @version 1.0
 * @since JDK 1.8
 */
public class ValidateUtils {

    private static String STR_EMPTY = "";

    /**
     * 校验是否为空字符串
     * 
     * @param validateStr 待校验的字符串
     * @return boolean
     */
    public static boolean isEmptyString(String validateStr) {
        return null == validateStr || STR_EMPTY.equals(validateStr);
    }

    /**
     * 校验是否不为空字符串
     * 
     * @param validateStr 待校验的字符串
     * @return boolean
     */
    public static boolean isNotEmptyString(String validateStr) {
        return !isEmptyString(validateStr);
    }

    /**
     * 校验: 为空对象或者空字符串
     * 
     * @param validateObj 待校验的对象
     * @return boolean
     */
    public static boolean isEmptyObjectOrString(Object validateObj) {
        return null == validateObj || STR_EMPTY.equals(validateObj.toString().trim());
    }

    /**
     * 校验: 不为为空对象或者空字符串
     * 
     * @param validateObj 待校验的对象
     * @return boolean
     */
    public static boolean isNotEmptyObjectOrString(Object validateObj) {
        return !isEmptyObjectOrString(validateObj);
    }

    /**
     * 校验是否为空collection
     * 
     * @param validateColl 待校验的collection
     * @return boolean
     */
    public static <E> boolean isEmptyCollection(Collection<E> validateColl) {
        return null == validateColl || validateColl.size() == 0;
    }

    /**
     * 校验是否不为空collection
     * 
     * @param validateColl 待校验的collection
     * @return boolean
     */
    public static <E> boolean isNotEmptyCollection(Collection<E> validateColl) {
        return !isEmptyCollection(validateColl);
    }

    /**
     * 校验：为空JSONArray
     * 
     * @param validateJSONArray 待校验的JSONArray
     * @return boolean
     */
    public static boolean isEmptyJSONArray(JSONArray validateJSONArray) {
        return null == validateJSONArray || validateJSONArray.size() == 0;
    }

    /**
     * 校验：不为空JSONArray
     * 
     * @param validateJSONArray 待校验的collection
     * @return boolean
     */
    public static boolean isNotEmptyJSONArray(JSONArray validateJSONArray) {
        return !isEmptyJSONArray(validateJSONArray);
    }

    /**
     * 校验：为空Map
     * 
     * @param validateMap 待校验的Map
     * @return boolean
     */
    public static boolean isEmptyMap(Map<?, ?> validateMap) {
        return null == validateMap || 0 == validateMap.entrySet().size();
    }

    /**
     * 校验: 不为空Map
     * 
     * @param validateMap 待校验的Map
     * @return boolean
     */
    public static boolean isNotEmptyMap(Map<?, ?> validateMap) {
        return !isEmptyMap(validateMap);
    }

    /**
     * 判断任何对象的数组是否为空数组
     * 
     * @param arrayObj　数组对象
     * @return　boolean
     */
    public static boolean isEmptyArray(Object arrayObj) {
        return null == arrayObj || Array.getLength(arrayObj) == 0;
    }

    /**
     * 判断任何对象的数组是否不为空数组
     * 
     * @param arrayObj　数组对象
     * @return　boolean
     */
    public static boolean isNotEmptyArray(Object arrayObj) {
        return !isEmptyArray(arrayObj);
    }
}
