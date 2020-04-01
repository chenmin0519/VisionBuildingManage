package com.visionbuilding.manage.controller;


import com.visionbuilding.manage.modle.TestModel;
import com.visionbuilding.manage.utill.ExcelGenerateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/exceport")
public class ExcePortController {
    @RequestMapping("/testExcel")
    public void testExcle(HttpServletResponse response) throws Exception {
        List<TestModel> list = new ArrayList<>();
        TestModel testModel = new TestModel();
        testModel.setName("chenmin");
        testModel.setAge("18");
        testModel.setDate(new Date());
        list.add(testModel);
        testModel = new TestModel();
        testModel.setName("郑灿");
        testModel.setAge("26");
        list.add(testModel);
        ExcelGenerateUtils<TestModel> excelGenerateUtils = new ExcelGenerateUtils<>();
        excelGenerateUtils.exportExcel(response,"测试",TestModel.class,list);
    }
}
