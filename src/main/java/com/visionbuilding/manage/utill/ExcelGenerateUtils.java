package com.visionbuilding.manage.utill;

import com.visionbuilding.manage.annotation.ExcelName;
import com.visionbuilding.manage.exception.LogicException;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.TestModel;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelGenerateUtils<T> {

    /**
     * 导出excel
     * @param response 响应
     * @param excelName 导出文件名
     * @param claz 泛型对象
     * @param list 数据
     * @throws Exception
     */
    public void exportExcel(HttpServletResponse response,String excelName, Class<T> claz, List<T> list) throws Exception {
        response = HttpServletUtils.getResponse(response,"xls",excelName);
        OutputStream out=response.getOutputStream();
        ResultBean resultBean = new ResultBean();
        HSSFWorkbook workbook = null;
        try {
            workbook = this.generateWorkbook(claz, list);
            try {
                workbook.write(out);
            } catch (Exception e) {
                e.printStackTrace();
                throw new LogicException("导出失败");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new LogicException("生成失败");
        }finally {
            try {
                workbook.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成excel
     * @return
     */
    private HSSFWorkbook generateWorkbook(Class<T> claz,List<T> ts) throws IllegalAccessException {
        Map<String,List<String>> map = getTitles(claz);
        List<String> names = map.get("names");
        List<String> titles = map.get("titles");
        // 第一步，创建一个workbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet hssfSheet = workbook.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow hssfRow = hssfSheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
        //居中样式
        hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell hssfCell = null;
        for (int i = 0; i < titles.size(); i++) {
            hssfCell = hssfRow.createCell(i);//列索引从0开始
            hssfCell.setCellValue(titles.get(i));//列名1
            hssfCell.setCellStyle(hssfCellStyle);//列居中显示
        }
        if(ts != null && !ts.isEmpty()){
            for (int i = 0; i < ts.size(); i++) {
                hssfRow = hssfSheet.createRow(i+1);
                   for(int j = 0;j<names.size();j++){
                       Field field = null;
                       try {
                           field = claz.getDeclaredField(names.get(j));
                           field.setAccessible(true);
                           T t = ts.get(i);
                           if(field.getType() == Date.class){
                               Date date = (Date) Optional.ofNullable(field.get(t)).orElse(null);
                               DateTimeFormat dateTimeFormat = field.getAnnotation(DateTimeFormat.class);
                               SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                               if(dateTimeFormat != null && dateTimeFormat.pattern() != null){
                                   sdf = new SimpleDateFormat(dateTimeFormat.pattern());
                               }
                               if(date != null){
                                   hssfRow.createCell(j).setCellValue(sdf.format(date));
                               }else{
                                   hssfRow.createCell(j).setCellValue("");
                               }
                           }else {
                               String value = Optional.ofNullable(field.get(t)).orElse("").toString();
                               hssfRow.createCell(j).setCellValue(value);
                           }
                       } catch (NoSuchFieldException e) {
                           e.printStackTrace();
                       }
                   }
                }
            }
        return workbook;
    }


    /**
     * 获取导出对应的excel中文名 和实体字段
     * @param claz
     * @return
     */
    private Map<String,List<String>> getTitles(Class<T> claz) {
        Map<String,List<String>> map = new HashMap<>();
        List<String> titles = new ArrayList<>();
        List<String> names = new ArrayList<>();
        Field[] fields = claz.getDeclaredFields();
        for(Field field : fields){
            names.add(field.getName());
            ExcelName excelName = field.getAnnotation(ExcelName.class);
            if(excelName != null && StringUtils.isNotEmpty(excelName.value())){
                titles.add(excelName.value());
            }else {
                titles.add(field.getName());
            }
        }
        map.put("names",names);
        map.put("titles",titles);
        return map;
    }

}
