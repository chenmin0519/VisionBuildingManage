package com.dteel.manage.utill;


import com.dteel.manage.modle.entity.DmsDepartment;
import com.dteel.manage.modle.po.DmsQuestionImport;
import com.dteel.manage.myenum.LoginLXEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

public class PojoUtils {

    private static final ConcurrentMap<String, Method> NAME_METHODS_CACHE = new ConcurrentHashMap<>();

    /**
     *
     * 将list 转成前台ztree所需要的json
     *
     * @author chenmin
     * @date: 2018年9月19日 下午5:12:31
     * @version 1.0
     *
     * @param pos
     * @return
     */
    public static <T> String getZtreeStr4Po(List<T> pos) {
        StringBuilder zTreeStr = new StringBuilder();
        String menuTree = "";
        if (ValidateUtils.isNotEmptyCollection(pos)) {
            for (T po : pos) {
                try {
                    Method getId = getGetterMethod(po.getClass(), "id");
                    Method getParentid = getGetterMethod(po.getClass(), "menuPid");
                    Method getName = getGetterMethod(po.getClass(), "menuName");
                    zTreeStr.append("{ \"id\":\"").append(getId.invoke(po)).append("\",\" pId\":\"").append(getParentid.invoke(po))
                            .append("\", \"open\":false").append(", \"name\":\"").append(getName.invoke(po)).append("\"},");
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (zTreeStr.length() > 0) {
                menuTree = zTreeStr.toString().substring(0, zTreeStr.toString().length() - 1);
            }
        }
        return menuTree;
    }

    public static <T> String getZtreeStrPo(List<T> pos) {
        StringBuilder zTreeStr = new StringBuilder();
        String menuTree = "";
        if (ValidateUtils.isNotEmptyCollection(pos)) {
            for (T po : pos) {
                try {
                    Method getId = getGetterMethod(po.getClass(), "id");
                    Method getParentid = getGetterMethod(po.getClass(), "menuPid");
                    Method getName = getGetterMethod(po.getClass(), "menuName");
                    Method by1Method = getGetterMethod(po.getClass(), "checked");
                    Integer by1 = (Integer)by1Method.invoke(po);
                    if(by1 != null && LoginLXEnum.DL.getKey().equals(by1)) {
                        zTreeStr.append("{ \"id\":\"").append(getId.invoke(po)).append("\",\" pId\":\"").append(getParentid.invoke(po))
                                .append("\", \"open\":true"). append(", \"checked\":true").append(", \"name\":\"").append(getName.invoke(po)).append("\"},");
                    }else{
                        zTreeStr.append("{ \"id\":\"").append(getId.invoke(po)).append("\",\" pId\":\"").append(getParentid.invoke(po))
                                .append("\", \"open\":true").append(", \"name\":\"").append(getName.invoke(po)).append("\"},");
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (zTreeStr.length() > 0) {
                menuTree = zTreeStr.toString().substring(0, zTreeStr.toString().length() - 1);
            }
        }
        return menuTree;
    }


    /**
     * Description:获取字段的get方法
     *
     * @author chenmin
     * @version 1.0
     */
    private static Method getGetterMethod(Class<?> cls, String property) {
        String name = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
        Method method = NAME_METHODS_CACHE.get(cls.getName() + "." + name + "()");
        if (method == null) {
            try {
                method = cls.getMethod(name);
            } catch (NoSuchMethodException e) {
                for (Method m : cls.getMethods()) {
                    if (m.getName().equals(name)) {
                        method = m;
                    }
                }
            }
            if (method != null) {
                NAME_METHODS_CACHE.put(cls.getName() + "." + name + "()", method);
            }
        }
        return method;
    }

    public static List<String> initImportRowName(List<String> parm) {
        String test = "^(?:(?i)[a-z]+)$";
        if(Pattern.matches(parm.get(0), test)){
            return parm;
        }
        List<String> result = new ArrayList<>();
        result.add("departmentCode");
        result.add("question");
        result.add("score");
        result.add("type");
        result.add("answer");
        result.add("checkA");
        result.add("checkB");
        result.add("checkC");
        result.add("checkD");
        return result;
    }

    public static void getDepartment(Map<String, DmsDepartment> departmentMap, DmsQuestionImport departmentImport) {
        DmsDepartment department = departmentMap.get(departmentImport.getDepartmentCode());
        if(department == null){
            for(String key : departmentMap.keySet()){
                department = departmentMap.get(key);
                break;
            }
        }
        departmentImport.setDepartmentId(department.getId());
    }

}

