package com.visionbuilding.manage.utill;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelImportUtils<T> {

    /**
     * 解析excel文件的数据格式化成model
     * @param row 行
     * @param heardList 头
     * @return
     */
    public T initData(Row row, List<String> heardList, Class<T> clas) throws Exception{
        T instance = clas.newInstance();
        // 遍历所有的列
        Cell cell = null;
        List<Object> li = new ArrayList<Object>();
        for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
            cell = row.getCell(y);
            String fieldName = heardList.get(y);
//            fieldName = JSONUtils.initFiedName(fieldName);
            Field field = null;
            try {
                field  = clas.getDeclaredField(fieldName);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(!ValidateUtils.isEmptyObjectOrString(field)){
                if(!ValidateUtils.isEmptyObjectOrString(cell)){
                    field.setAccessible(true);
                    try {
                        if(field.getType() == Integer.class){
                            field.set(instance, new Integer(getCellStringByAll(cell).trim()));
                        }else if(field.getType() == String.class){
                            field.set(instance, getCellStringByAll(cell).trim());
                        }else if(field.getType() == Long.class){
                            field.set(instance, new Long(getCellStringByAll(cell).trim()));
                        }
                    }catch(Exception e){
                        System.out.println(cell.getStringCellValue());
                        System.out.println(cell.getRichStringCellValue());
                    }
                }
            }
        }
        return instance;
    }

    /**
     * 处理任何类型数据转成String
     * @param cell
     * @return
     */
    private String getCellStringByAll(Cell cell) {
        if(cell==null) return "";
        String cellSring="";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                cellSring = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                cellSring=String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                cellSring=String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                cellSring=String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                cellSring="";
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                cellSring="";
                break;
            default:
                cellSring="ERROR";
                break;
        }
        return cellSring;
    }

    public T initKskmData(Row row, List<String> heardList, Class<T> clas) throws Exception{
        T instance = clas.newInstance();
        // 遍历所有的列
        Cell cell = null;
        List<Object> li = new ArrayList<Object>();
        for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
            cell = row.getCell(y);
            String fieldName = heardList.get(y);
            if(fieldName.equals("科目代码")){
                fieldName = "kmdm";
            }else if(fieldName.equals("科目名称")){
                fieldName = "kmmc";
            }else if(fieldName.equals("命题性质代码")){
                fieldName = "mtxz";
            }else if(fieldName.equals("单元代码")){
                fieldName = "dy";
            }
            Field field = null;
            try {
                field  = clas.getDeclaredField(fieldName);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(!ValidateUtils.isEmptyObjectOrString(field)){
                if(!ValidateUtils.isEmptyObjectOrString(cell)){
                    field.setAccessible(true);
                    field.set(instance,cell.getRichStringCellValue().getString());
                }
            }
        }
        return instance;
    }
}