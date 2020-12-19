package com.dyd;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDate {

    public static void main(String[] args) throws IOException {

        String path=".//src//resource//16个账号.xlsx";

        FileInputStream excelfile=new FileInputStream(path);

        XSSFWorkbook wb=new XSSFWorkbook(excelfile);

        XSSFSheet sheet=wb.getSheetAt(0);

        XSSFRow row=sheet.getRow(1);

        XSSFCell cell=row.getCell(1);
        System.out.println(cell);










    }

}
