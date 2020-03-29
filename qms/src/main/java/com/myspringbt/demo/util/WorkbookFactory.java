package com.myspringbt.demo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class WorkbookFactory {
	private Workbook	workBook		= null;
	private String		excelFilePath	= null;

	/**
	 * 创建文档WorkBook(读文档)
	 * 
	 * @param excelFilePath
	 * @return
	 * @throws Exception
	 */
	public Workbook create(String excelFilePath) throws Exception {

		this.excelFilePath = excelFilePath;
		File file = new File(this.excelFilePath);
		FileInputStream fis = new FileInputStream(file);

		// 2003版的Excel
		if (excelFilePath.endsWith(".xls")) {

			workBook = new HSSFWorkbook(fis);

			// 2007版以后的Excel
		} else if (excelFilePath.endsWith(".xlsx")) {

			workBook = new XSSFWorkbook(fis);

		}

		return workBook;

	}

	/**
	 * 创建文档WorkBook(写文档)
	 * 
	 * @param excelFilePath
	 * @return
	 * @throws Exception
	 */
	public Workbook create(String fileName, boolean flag) throws Exception {

		this.excelFilePath = fileName;

		// 2003版的Excel
		if (excelFilePath.endsWith(".xls")) {

			workBook = new HSSFWorkbook();

			// 2007版以后的Excel
		} else if (excelFilePath.endsWith(".xlsx")) {

			workBook = new XSSFWorkbook();

		}

		return workBook;

	}
}
