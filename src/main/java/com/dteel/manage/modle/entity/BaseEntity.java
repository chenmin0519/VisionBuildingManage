package com.dteel.manage.modle.entity;

import com.dteel.manage.exception.LogicException;
import com.dteel.manage.modle.query.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class BaseEntity extends BaseQuery implements  Serializable {
    private static final long serialVersionUID = -5076054687358856804L;
    private int limit;
    private int offset;

    private Long id;
    private Integer status;
    private Date addTime;
    private Date updateTime;
    private String addUser;
    private String updateUser;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Map<String, Object> getPagingMap() throws LogicException {

        Map<String, Object> filedMap = new HashMap<String, Object>();
        Field[] filed = this.getClass().getDeclaredFields();
        for (Field fd : filed) {
            fd.setAccessible(true);
            try {
                filedMap.put(fd.getName(), fd.get(this));
            } catch (IllegalArgumentException e) {
                throw new LogicException(e.getCause());
            } catch (IllegalAccessException e) {
                throw new LogicException(e.getCause());
            }
        }
        return filedMap;
    }
}