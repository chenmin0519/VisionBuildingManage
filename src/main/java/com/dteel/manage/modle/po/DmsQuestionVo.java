package com.dteel.manage.modle.po;

import com.dteel.manage.modle.entity.DmsQuestion;

public class DmsQuestionVo extends DmsQuestion {

    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
