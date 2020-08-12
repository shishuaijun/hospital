package com.follow.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * excel导出工具类
 * @author wangchunjun
 * @date 2020/8/11
 */
public class ExportExcelUtil<T> {

    public  Workbook export(String[] henders, String name, List<T> list) throws IOException {
        Workbook workbook=null;
        // 判断是否有文件名称
        if (name !=null && !"".equals(name)){
            if (name.endsWith(".xls")){
                // 2003 版
                workbook = new HSSFWorkbook();
            }else if(name.endsWith(".xlsx")){
                //2007版
                workbook = new XSSFWorkbook();
            }else{
                workbook = new HSSFWorkbook();
            }
        }else{
            workbook  = new HSSFWorkbook();
        }
        // 创建表格
        Sheet sheet = workbook.createSheet();
        //创建第一行数据
        firstRow(sheet,henders);
        // 添加 其他行数据
        try {
            otherRow(sheet,list);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
       return workbook;

    }

    private  void otherRow(Sheet sheet, List<T> values) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (int i = 0; i <values.size() ; i++) {
            // 从第二行 进行添加
            Row row = sheet.createRow(i + 1);
            T t = values.get(i);//获取对象类型
            Class classes = t.getClass();//获取传入类型的反射对象
            Field[] declaredFields = classes.getDeclaredFields();//获取属性
            for (int i1 = 0; i1 < declaredFields.length; i1++) {
                Cell cell = row.createCell(i1);
                String name = declaredFields[i1].getName();//获取单个属性名称
                String methodName = "get" + name.substring(0,1).toUpperCase() + name.substring(1);//获取属性的get方法名称
                Method method = classes.getMethod(methodName, null);
                Object invoke = method.invoke(t, null);
                String value = "";
                if (invoke !=null){
                    value = invoke.toString();
                }else {
                    value = "";
                }
                cell.setCellValue(value);
            }
        }

    }

    private  void firstRow(Sheet sheet,String[] henders) {
        //创建第一行数据
        Row row = sheet.createRow(0);
        for (int i = 0; i <henders.length ; i++) {
            //创建单元格数据
            Cell cell = row.createCell(i);
            //给单元格进行赋值
            cell.setCellValue(henders[i]);
        }
    }

}
