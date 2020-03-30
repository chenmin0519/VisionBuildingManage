package com.dteel.manage.service.proxy;

import com.alibaba.fastjson.JSONArray;
import com.dteel.manage.exception.LogicException;
import com.dteel.manage.modle.entity.DmsDepartment;
import com.dteel.manage.modle.po.DmsQuestionImport;
import com.dteel.manage.myenum.EnumQuestoinType;
import com.dteel.manage.service.DmsDepartmentService;
import com.dteel.manage.utill.ExcelImportUtils;
import com.dteel.manage.utill.PojoUtils;
import com.dteel.manage.utill.ValidateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("excelAnalysis")
public class ExcelAnalysis implements Analysis{

    @Autowired
    private DmsDepartmentService dmsDepartmentService;

    public List<List<DmsQuestionImport>> analysisFiel(MultipartFile importFile) {
        //将要导入的bmk数据
        List<List<DmsQuestionImport>> questionsList = new ArrayList<>();
        Map<String, DmsDepartment> departmentMap = new HashMap<>();
        List<DmsDepartment> departments = dmsDepartmentService.queryDepartmentList();
        departmentMap = getdepartmentMap(departments);
        Workbook work = null;
        InputStream is = null;
        try {
            is = importFile.getInputStream();
            work = this.getWorkbook(is,importFile.getOriginalFilename());
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;
            sheet = work.getSheetAt(0);
            if (sheet == null) {
                return null;
            }
            List<DmsQuestionImport> values = new ArrayList<>();
            //仅仅支持导入一个sheet
            row = sheet.getRow(0);
            List<String> heardList = new ArrayList<String>();
            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                cell = row.getCell(y);
                heardList.add(cell.getStringCellValue().trim());
            }
            if(heardList.size() != 9){
                throw new LogicException("请导入正确的模板！");
            }
            //初始化列名
            heardList = PojoUtils.initImportRowName(heardList);
            // 遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum() + 1; j++) {
                // 这里的加一是因为下面的循环跳过取第一行表头的数据内容了
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                //获取数据
                ExcelImportUtils<DmsQuestionImport> excelImportUtils = new ExcelImportUtils<>();
                DmsQuestionImport questionImport =excelImportUtils.initData(row,heardList,DmsQuestionImport.class);
                if(questionImport.getType().equals(EnumQuestoinType.CHECKBOX.getKey())) {
                    questionImport.setAnswer(questionImport.getAnswer().replace("+",","));
                }
                values.add(questionImport);
                //获取考试科目
                PojoUtils.getDepartment(departmentMap, questionImport);
                //100条数据一次
                if (j % 100 == 0) {
                    questionsList.add(values);
                    values = new ArrayList<>();
                }
            }
            //最后多余的数据
            questionsList.add(values);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LogicException("解析导入文件失败，请导入正确的模板！");
        }finally {
            try {
                is.close();
                work.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return questionsList;
    }

    private Map<String, DmsDepartment> getdepartmentMap(List<DmsDepartment> departments) {
        if(departments == null || departments.size() <= 0){
            throw new LogicException("不存在部门信息！");
        }
        Map<String,DmsDepartment> result = new HashMap<>();
        for(DmsDepartment dmsDepartment : departments){
            result.put(dmsDepartment.getCode(),dmsDepartment);
        }
        return result;
    }

    //便小写
    private List<String> initheardList(List<String> heardList) {
        List<String> list = new ArrayList<>();
        heardList.forEach(heard ->{
            String str = heard.toLowerCase();
            list.add(str);
        });
        return list;
    }


    /**	 * 描述：对表格中数值进行格式化	 * 	 * @param cell	 * @return	 */
    public Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");
        // 格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        // 日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");
        // 格式化数字
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * 将对象序列化为JSON文本
     * @author min.chen
     * @date: 2017年8月12日 上午10:39:40
     * @version 1.0
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSONArray.toJSONString(object);
    }

    /**
     * 初始化字段名规范
     * @param filedName
     * @return
     */
    public static String initFiedName(String filedName) {
        String result = "";
        if(!ValidateUtils.isEmptyObjectOrString(filedName)){
            String[] filedNameArr = filedName.split("_");
            if(filedNameArr.length > 1){
                result = filedNameArr[0].toLowerCase();
                for(int i = 1; i < filedNameArr.length; i ++){
                    String str = filedNameArr[i].toLowerCase();
                    result += str.substring(0,1).toUpperCase()+str.substring(1,str.length());
                }
            }else{
                result = filedNameArr[0].toLowerCase();
            }
        }
        return result;
    }

    /**
     * 解析excel对应版本获取WorkBook
     * @param inputStream
     * @param name
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String name) throws IOException {
        Workbook wb = null;
        String fileType = name.substring(name.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            wb = new HSSFWorkbook(inputStream); // 2003-
        }
        else if (".xlsx".equals(fileType)) {
            wb = new XSSFWorkbook(inputStream); // 2007+
        }
        return wb;
    }
}