package com.visionbuilding.manage.modle;

import com.visionbuilding.manage.annotation.ExcelName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class TestModel {
    @ExcelName("名字")
    private String name;
    @ExcelName("年龄")
    private String age;
    @ExcelName("生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}
