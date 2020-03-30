package com.dteel.manage.modle;

import java.io.Serializable;
import java.math.BigInteger;

public class ResultBean implements Serializable {
    private static final long serialVersionUID = 8400341083688596453L;
    private String status;
    private String message;
    private String id ;//返回插入主键
     

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void success() {
        this.message="操作成功";
        this.status = Result.success.toString();
    }

    public void failure(String message) {
        this.status = Result.failure.toString();
        this.message = message;
    }

    public void error(String message) {
        this.status = Result.Error.toString();
        this.message = message;
    }

    public boolean isSuccess() {
        return Result.success.compare(this.status);
    }

    public boolean isFailure() {
        return Result.failure.compare(this.status);
    }

    public boolean isError() {
        return Result.Error.compare(this.status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    
}

