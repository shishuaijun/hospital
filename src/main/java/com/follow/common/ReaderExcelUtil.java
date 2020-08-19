package com.follow.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * excel导入工具类
 * @author wangchunjun
 * @date 2020/8/11
 */
public class ReaderExcelUtil {

    public static List<List<Object>> readerExcel(String path) throws Exception{
        if (path != null && !"".equals(path)){
            Workbook workbook = null;
            if (path.endsWith(".xls")){
                workbook = new HSSFWorkbook(new FileInputStream(path));
                return reader(workbook);
            }else if(path.endsWith(".xlsx")){
                workbook = new XSSFWorkbook(new FileInputStream(path));
                return reader(workbook);
            }else {
                return  null;
            }
        }else {
            return  null;
        }
    }

    private static List<List<Object>> reader(Workbook workbook){

        Sheet sheet = workbook.getSheetAt(0);
        List<List<Object>> list = new ArrayList<>();

        for (int i = sheet.getFirstRowNum()+1; i <sheet.getPhysicalNumberOfRows() ; i++) {
            List<Object> list1 = new ArrayList<>();
            Row row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j <row.getLastCellNum() ; j++) {
                Cell cell = row.getCell(j);
                String cellValue = "";
                if (cell !=null){
                    cellValue = cell.toString();
                }
                list1.add(cellValue);
            }
            list.add(list1);
        }
        return list;
    }

}
