package com.visionbuilding.manage.modle.query;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class BaseQuery implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 4051652445567742674L;
    /**
     * 此处为属性说明
     */
    private int pageNo = 1;
    private int pageRows = 20;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageRows() {
        return pageRows;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }

    /**
     * 
     * @author min.chen
     * @version 1.0
     *
     * @param beginCount 查询起始值
     * @param pageRows  页大小
     */  
    public void setPageNoRows(int beginCount,int pageRows) {
       this.pageNo =  (beginCount/pageRows)+1 ;
       this.pageRows =pageRows ;
    }   
    
    public Map<String, Object> getPagingMap() throws Exception {

        Map<String, Object> filedMap = new HashMap<String, Object>();
        Field[] filed = this.getClass().getDeclaredFields();
        for (Field fd : filed) {
            fd.setAccessible(true);
            try {
                filedMap.put(fd.getName(), fd.get(this));
            } catch (IllegalArgumentException e) {
               throw new Exception(e.getCause());
            } catch (IllegalAccessException e) {
                throw new Exception(e.getCause());
            }
        }
        return filedMap;
    }

    /**
     * 
     * 不分页查询条件非空判断公共方法. <br/> 
     * 
     * @author min.chen
     * @version 1.0
     *
     * @return
     * @throws Exception
     */
    public boolean isEmpty() throws Exception{
        Map<String, Object> map = getPagingMap();
        if (map == null)
            return true;
        Set<String> keys = map.keySet();
        boolean flag = true;
        for (String key : keys) {
            if ("serialVersionUID".contains(key))
                continue;
            if ("state".contains(key))
                continue;
            if (map.get(key) != null) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
