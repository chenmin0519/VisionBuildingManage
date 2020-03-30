package com.visionbuilding.manage.controller;

import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.po.DmsQuestionImport;
import com.visionbuilding.manage.service.DmsQuestionService;
import com.visionbuilding.manage.service.proxy.Analysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/import")
public class DownloadController {

    @Autowired
    private DmsQuestionService dmsQuestionService;

    @RequestMapping("/download/excel")
    public void downloadEcel(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;fileName=question_import.xls");
        String url = session.getServletContext().getRealPath("/")+"试题导入模板.xls";
        File file = new File(url);
        OutputStream os=null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b = new byte[8 * 1024];
            int length = 0;
            while ((length = in.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @ResponseBody
    @RequestMapping("/importQuestion")
    public String importQuestion(@RequestParam("dbfimport") MultipartFile importFile , HttpServletRequest request){
        ResultBean resultBean = new ResultBean();
        if(importFile == null){
            resultBean.failure("文件不能为空");
        }
        String fileName = importFile.getOriginalFilename();
        //spring上下文
        Analysis excelAnalysis = null;
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        if(fileName.endsWith("xls") || fileName.endsWith("xlsx")){
            excelAnalysis = (Analysis) springContext.getBean("excelAnalysis");
        }else{
            resultBean.failure("当前不是excel文件无法解析");
            return JSON.toJSONString(resultBean);
        }
        List<List<DmsQuestionImport>> questionsList = new ArrayList<>();
        try {
            questionsList = excelAnalysis.analysisFiel(importFile);
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
            return JSON.toJSONString(resultBean);
        }
        resultBean = dmsQuestionService.batchSave(questionsList);
        resultBean.success();
        return JSON.toJSONString(resultBean);
    }
}
