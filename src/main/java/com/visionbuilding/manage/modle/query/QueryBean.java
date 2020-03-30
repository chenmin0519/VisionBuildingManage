package com.visionbuilding.manage.modle.query;

import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

/**
 * 
 * Description: TODO {一句话描述类是干什么的}<br/>
 *
 * @author min.chen
 * @date: 2018年9月17日 下午11:55:24
 * @version 1.0
 * @since JDK 1.8
 */
public class QueryBean{
	
    private Map<String, Object> f = new LinkedHashMap<String, Object>();

    private int pageNo = 1;

    private int pageRows = 20;

    private int totalCount = 0;
    
    private int totalPages;

     

    public void addF(String paramKey, Object value) {
        if ((null != value) && (StringUtils.isEmpty(value.toString())))
            this.f.put(paramKey, StringUtils.trimAllWhitespace(value.toString()));
    }

    public int getPageRows() {
        return this.pageRows;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNo() {
        if (this.pageNo < 1) {
            this.pageNo = 1;
        }

        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getFirst() {
        return (getPageNo() - 1) * this.pageRows + 1;
    }

    public int getLast() {
        return getFirst() + this.pageRows > getTotalCount() ? getTotalCount() : getFirst() + this.pageRows - 1;
    }

    public int getTotalPages() {
        this.totalPages = (this.totalCount + this.pageRows - 1) / this.pageRows;
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        if (totalPages == 0)
            totalPages = 1;
        this.totalPages = totalPages;
    }

    public void resetTotalCount(int totalCount) {
        if (totalCount < 0) {
            totalCount = 0;
        }
        int count = (totalCount + this.pageRows - 1) / this.pageRows;
        setTotalCount(totalCount);
        setTotalPages(count);
       if (getPageNo() > getTotalPages())
            setPageNo(getTotalPages()); 
    }

    public boolean isHasNext() {
        return this.pageNo + 1 <= getTotalPages();
    }

    public int getNextPage() {
        if (isHasNext()) {
            return this.pageNo + 1;
        }
        return this.pageNo;
    }

    public boolean isHasPre() {
        return this.pageNo - 1 >= 1;
    }

    /**
     * 结束行
     * @return
     */
    public int getEndPage() {
        return this.pageNo*this.pageRows;
    }

    /**
     * 开始行
     * @return
     */
    public int getStartPage() {
        if (isHasPre()) {
            return (this.pageNo-1)*this.pageRows;
        }
        return 0;
    }

     
    public Map<String, Object> getF() {
        return this.f;
    }

    public void setF(Map<String, Object> f) {
        this.f = f;
    }

    public String getParaValue(String key) {
        Object value = this.f.get(key);
        if (null != value) {
            return value.toString();
        }
        return null;
    }
    public String getKey(String key){
        String result =key+"_"+JSON.toJSONString(f);
        return result;
    }

}

