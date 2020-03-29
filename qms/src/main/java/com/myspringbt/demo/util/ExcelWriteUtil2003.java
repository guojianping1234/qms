package com.myspringbt.demo.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class ExcelWriteUtil2003 implements ExcelWriteInter {

	@Override
	public void createExcel(Workbook workBook, List<String[]> list) throws Exception {

 
		          
		        HSSFSheet sheet = (HSSFSheet) workBook.createSheet();  
		        RichTextString richString =null ;  
		        Font font = null;  
		        CellStyle style = null;  
		        for (int i = 0; i < list.size(); i++) {  
		              
		            if(i == 0){  
		                font = workBook.createFont();  
		                font.setFontHeightInPoints((short)24); //字体大小  
		                font.setFontName("楷体"); 
		                font.setFontHeight(Font.COLOR_NORMAL);
		              //  font.setBoldweight(Font.COLOR_NORMAL); //粗体  
		                font.setColor(HSSFColor.GREEN.index);    //绿字  
		                style = workBook.createCellStyle();  
		                style.setFont(font);  
		                String[] values = (String[])list.get(i);  
		                HSSFRow row = (HSSFRow) sheet.createRow(i);  
		                for (int j = 0; j < values.length; j++) {  
		                    String string = values[j];  
		                    HSSFCell cell = row.createCell(j);  
		                    richString = new HSSFRichTextString(string);  
		                    cell.setCellValue(richString);  
		                    cell.setCellStyle(style);  
		                }  
		            }else{  
		                String[] values = (String[])list.get(i);  
		                HSSFRow row = (HSSFRow) sheet.createRow(i);  
		                for (int j = 0; j < values.length; j++) {  
		                    String string = values[j];  
		                    HSSFCell cell = row.createCell(j);  
		                    richString = new HSSFRichTextString(string);  
		                    cell.setCellValue(richString);  
		                }  
		            } 
		        }
	}
}
