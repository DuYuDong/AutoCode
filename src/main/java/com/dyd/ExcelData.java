package com.dyd;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelData {

    /**
     * 1、先读取num.txt中的数值
     * 2、将读取的数值当做获取Excel的行数
     * 3、将获取的行数+1重写入num.txt
     **/

    public static String numpath=".//src//main//resources//num.txt";
    public static String mainpath=".//src//main//resources//16个账号.xlsx";

    public static String getExcelData(){
        int getValue= Integer.parseInt(readTxt(numpath));
        System.out.println("当前获取第"+getValue+"行");

        int tmp1= getValue+1;
        String tmp2= String.valueOf(tmp1);
        reWriteTxt(numpath,tmp2);
        System.out.println("重新写入成功");

        FileInputStream excelfile= null;

        try {
            excelfile = new FileInputStream(mainpath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb= null;
        try {
            wb = new XSSFWorkbook(excelfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet=wb.getSheetAt(0);

        XSSFRow row=sheet.getRow(getValue);//行

        XSSFCell cell=row.getCell(0);

        String answer= String.valueOf(cell);
        //System.out.println("获取第"+getValue+"行结果是："+cell);

        return answer;
    }

    //读取txt内容
    public static String readTxt(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }


    //重写TXT文件内容
    public static void reWriteTxt(String fileName,String newTxt) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write(newTxt);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
