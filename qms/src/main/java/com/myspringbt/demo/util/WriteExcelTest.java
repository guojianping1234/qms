package com.myspringbt.demo.util;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WriteExcelTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String[]> list = new ArrayList<String[]>();
		String[] title = new String[5];
		title[0] = "标题1";
		title[1] = "标题2";
		title[2] = "标题3";
		title[3] = "标题4";
		title[4] = "标题5";
		list.add(title);
		for (int i = 1; i < 10; i++) {
			String[] str = new String[5];
			str[0] = "张三" + i;
			str[1] = "李四" + i;
			str[2] = "王五" + i;
			str[3] = "赵六" + i;
			str[4] = "冯七" + i;
			list.add(str);
		}
		ExcelWriteInter write = new ExcelWriteUtil2007();
		String excelFilePath = "C:\\Users\\Administrator\\Desktop\\temp\\";
		String excelFileName = "test.xlsx";
		try {
			WorkbookFactory workBookFactory = new WorkbookFactory();
			Workbook workbook = workBookFactory.create(excelFileName, false);
			write.createExcel(workbook, list);
			workbook.write(new FileOutputStream(new File(excelFilePath + excelFileName)));
			System.out.println("创建成功!!!");
		} catch (Exception e) {
			System.out.println("创建失败!!!");
			e.printStackTrace();
		}

	}

}
