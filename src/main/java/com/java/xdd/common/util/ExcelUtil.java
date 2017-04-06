package com.java.xdd.common.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    /**
     * 读取Excel数据，将所有数据以字符串读取
     * @param excel 表格文件
     * @param index 从第几行开始读取
     * @return
     * @throws Exception
     */
    public static List<List<String>> readExcel(File excel, Integer index) throws Exception{
        /** 通过指定excel文件创建工作簿 */
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(excel));
        /** 获取第一个工作单 */
        HSSFSheet sheet = workbook.getSheetAt(0);
        /** 获取最后一行的索引号 */
        int lastRowNum = sheet.getLastRowNum();

        /** 创建List集合封装Excel中一个工作单的数据 */
        List<List<String>> excelData = new ArrayList<>();

        /** 迭代时(第一行不要) */
        for (int i = 1; i <= lastRowNum; i++){
            /** 获取一行 */
            HSSFRow row = sheet.getRow(i);
            /** 获取一行中最后一列的索引号 */
            int lastCellNum = row.getLastCellNum();

            /** 创建List集合封装一行数据 */
            List<String> rowData = new ArrayList<>(lastCellNum);

            /** 迭代当前行中所有的列  lastCellNum 是它的列的长度 */
            for (int j = 0; j < lastCellNum; j++){
                /** 获取列 */
                HSSFCell cell = row.getCell(j);
                /** 判断该列中内容的数据类型，获取cell中的内容 */
                if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){ // boolean
                    rowData.add(cell.getBooleanCellValue() + "");
                }else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){ // number(数字)
                    /** 判断是不是日期 */
                    if (DateUtil.isCellDateFormatted(cell)){ // date
                        rowData.add(cell.getDateCellValue().toString());
                    }else{
                        rowData.add(cell.getNumericCellValue() + "");
                    }
                }else{ // string
                    rowData.add(cell.getStringCellValue());
                }
            }
            excelData.add(rowData);
        }

        return excelData;
    }

    public static <T> List<T> readExcel(File excel, Integer index, String[][] fieldArr, Class<T> clz) throws Exception{
        /** 通过指定excel文件创建工作簿 */
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(excel));
        /** 获取第一个工作单 */
        HSSFSheet sheet = workbook.getSheetAt(0);
        /** 获取最后一行的索引号 */
        int lastRowNum = sheet.getLastRowNum();

        /** 创建List集合封装Excel中一个工作单的数据 */
        List<T> excelData = new ArrayList<>();

        /** 迭代时(第一行不要) */
        for (int i = 1; i <= lastRowNum; i++){
            /** 获取一行 */
            HSSFRow row = sheet.getRow(i);
            /** 获取一行中最后一列的索引号 */
            int lastCellNum = row.getLastCellNum();

            /** 创建List集合封装一行数据 */
            T t = clz.newInstance();
            /** 迭代当前行中所有的列  lastCellNum 是它的列的长度 */
            for (int j = 0; j < fieldArr.length; j++){
                /** 获取列 */
                HSSFCell cell = row.getCell(j);

                String field = fieldArr[j][0]; //获取封装的字段
                String fieldType = fieldArr[j][1]; //获取需要封装的类型
                String newField = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
                Method method = clz.getMethod(newField, Class.forName(fieldType));



                /** 判断该列中内容的数据类型，获取cell中的内容 */
                if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){ // boolean
                    method.invoke(t, cell.getBooleanCellValue() + "");
                }else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){ // number(数字)
                    /** 判断是不是日期 */
                    if (DateUtil.isCellDateFormatted(cell)){ // date
                        method.invoke(t, cell.getDateCellValue().toString());
                    }else{
                        method.invoke(t, cell.getNumericCellValue() + "");
                    }
                }else{ // string
                    method.invoke(t, cell.getStringCellValue());
                }
            }
            excelData.add(t);
        }

        return null;
    }
}
