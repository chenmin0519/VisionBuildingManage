package com.dteel.manage.modle;

import java.util.List;

public class ResultPOListBean<T> extends ResultBean {
    private static final long serialVersionUID = 867933019328199779L;
    private int totalCount = 0;
    private int totalPages =0;
    private int pageNo = 1;
    private int pageRows = 20;
    
    private int count =0;
    
    private List<T> value;
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getValue() {
        return this.value;
    }

    public void setValue(List<T> value) {
        this.value = value;
    }
    public boolean isEmpty() {
        return this.value==null || this.value.size()==0 ? true :false ;
    }
    public void success(List<T> value,int count ) {
        setStatus(Result.success.toString());
        this.value = value;
        this.count =count ;
    }
}

