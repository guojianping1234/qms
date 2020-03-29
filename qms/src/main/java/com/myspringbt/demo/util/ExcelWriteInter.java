package com.myspringbt.demo.util;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

;

public interface ExcelWriteInter {
	/**
	 * 创建Excel文档
	 * 
	 * @param excelFilePath
	 */
	public void createExcel(Workbook workBook, List<String[]> list) throws Exception;
}
