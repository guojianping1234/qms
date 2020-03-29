package com.myspringbt.demo.util;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReadExcelTes {
	/** 
     * @param args 
     */  
    public static void main(String[] args) {  
  
        String excelPath = "C:\\Users\\Administrator\\Desktop\\temp\\test.xlsx";  
        WorkbookFactory factory = new WorkbookFactory();  
        Map map = null;  
        try {  
            Workbook workBook= factory.create(excelPath);  
            ExcelReadUtil util = new ExcelReadUtil();  
            map = util.readSheet(workBook);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        Set set = map.keySet();  
        for(Iterator it=set.iterator();it.hasNext();){  
  
            String key = (String)it.next();  
            System.out.println("Sheet名称：" + key);   
            List<String[]> list = (List<String[]>)map.get(key);  
            for (int i = 0; i < list.size(); i++) {  
                String[] str = list.get(i);  
                String obj = "";  
                for (int j = 0; j < str.length; j++) {  
                    obj = obj + str[j] + ",";  
                }  
                System.out.println("第"+(i+1)+"行:"+obj);  
            }  
  
        }  
  
    }
}
