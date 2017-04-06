package com.java.xdd.common.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtil {
    private ExcelUtil() {}

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

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
        for (int i = index; i <= lastRowNum; i++){
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
        workbook.close();
        return excelData;
    }

    /**
     * 读取Excel数据，将所有数据以字符串读取
     * @param excel 表格文件
     * @param index 从第几行开始读取
     * @param fieldArr 封装数据字段名称与字段类型，二维数组，数组第一个是字段名称，数组第二个是字段类型
     * @param clz 封装需要的对象class
     * @param <T> 封装数据的真实对象
     * @return
     * @throws Exception
     */
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
        for (int i = index; i <= fieldArr.length; i++){
            /** 获取一行 */
            HSSFRow row = sheet.getRow(i);
            /** 获取一行中最后一列的索引号 */
            int lastCellNum = row.getLastCellNum();

            /** 创建List集合封装一行数据 */
            T t = clz.newInstance();
            /** 迭代当前行中所有的列  lastCellNum 是它的列的长度 */
            for (int j = 0; j < fieldArr.length; j++){
                try {
                    /** 获取列 */
                    HSSFCell cell = row.getCell(j);

                    String field = fieldArr[j][0]; //获取封装的字段
                    String fieldType = fieldArr[j][1]; //获取需要封装的类型
                    String newField = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
                    Method method = clz.getMethod(newField, Class.forName(fieldType));


                    if ("java.lang.String".equals(fieldType)) { //字符串
                        method.invoke(t, cell.getStringCellValue());
                    } else if ("java.lang.Boolean".equals(fieldType)) { //布尔类型
                        method.invoke(t, cell.getBooleanCellValue());
                    } else if ("java.util.Date".equals(fieldType)) { //日期类型
                        method.invoke(t, cell.getDateCellValue());
                    } else { //数字类型
                        double value = cell.getNumericCellValue();
                        String tempValue = value + "";
                        if ("java.lang.Integer".equals(field)) {
                            method.invoke(t, Integer.valueOf(tempValue));
                        } else if ("java.lang.Long".equals(field)) {
                            method.invoke(t, Long.valueOf(tempValue));
                        } else if ("java.lang.Double".equals(field)) {
                            method.invoke(t, Double.valueOf(tempValue));
                        } else if ("java.lang.Float".equals(field)) {
                            method.invoke(t, Float.valueOf(tempValue));
                        } else {
                            method.invoke(t, value);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("封装第【{}】列出错，错误信息【{}】！", j, e.getMessage());
                }
            }
            excelData.add(t);
        }
        workbook.close();
        return excelData;
    }

    public static void exportExcel(List<?> data, String[] titles,
                                   String sheelName, String fileName, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception{
		/*标题行数据格式： 编号，姓名，性别，手机号码，邮箱，QQ号码，生日，组名 */
        /** 创建工作簿 */
        HSSFWorkbook workbook = new HSSFWorkbook();
        /** 创建工作单 */
        HSSFSheet sheet = workbook.createSheet(sheelName);
        /** 创建第一行，即标题栏 */
        HSSFRow row = sheet.createRow(0);
        /** 给第一行添加标题 */
        for (int i = 0; i < titles.length; i++) {
            /** 创建列，并添加列中的数据 */
            row.createCell(i).setCellValue(titles[i]);
        }

        /** ########### 中间行中的数据 ########## */
        for (int i = 0; i < data.size(); i++) {
            /** 创建数据需要的行 */
            HSSFRow rows = sheet.createRow(i+1);
            /** 获取集合中的数据 */
            Object object = data.get(i);
            /** 获取数据的所有字段 */
            Field[] fields = object.getClass().getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                /** 添加列 */
                HSSFCell cell = rows.createCell(j);
                Field field = fields[j];
                /** 暴力反射 */
                field.setAccessible(true);
                /** 获取filed值 */
                Object res = field.get(object);
                /** 设置列中的值*/
                cell.setCellValue(res == null ? "" : res.toString());
            }
        }
        /** ########### 中间行中的数据 ########## */

        /** 解决不同浏览器的乱码问题 */
        String encoding = "utf-8";
        /** 获取浏览器的类型 */
        String userAgent = request.getHeader("user-agent");
        /** 判断是否是IE浏览器 */
        if(userAgent.toLowerCase().contains("msie")){
            encoding = "gbk";
        }
		/*if (userAgent.toLowerCase().indexOf("msie") != -1){
			encoding = "gbk";
		}*/
        /** 编码 */
        fileName = new String(fileName.getBytes(encoding),"iso8859-1");
        /** 设置响应头，让浏览器使用附件下载形式下载该文件 */
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName +".xls");

        ServletOutputStream outputStream = response.getOutputStream();
        /** 将表中的数据写出到浏览器 */
        workbook.write(outputStream);
        workbook.close();
    }
}
