package com.visionbuilding.manage.modle;

import com.visionbuilding.manage.annotation.ExcelName;

import java.util.Date;

public class TestModel {
    @ExcelName("名字")
    private String name;
    @ExcelName("年龄")
    private String age;
    @ExcelName("生日")
    private Date date;
}
