package com.myspringbt.demo.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReadUtil {
	/**
	 * 读取每一个Sheet的数据
	 * 
	 * @param workBook
	 * @return
	 */
	public Map<String, Object> readSheet(Workbook workBook) {

		Map<String, Object> sheetMap = new HashMap<>();
		int _sheetNUM = workBook.getNumberOfSheets();
		for (int i = 0; i < _sheetNUM; i++) {
			Sheet sheet = workBook.getSheetAt(i);
			String sheetName = sheet.getSheetName();
			List<String[]> list = null;
			if (null != sheet) {
				list = readRow(sheet);
			}
			sheetMap.put(sheetName, list);
		}

		return sheetMap;

	}

	/**
	 * 读取每一行数据
	 * 
	 * @param workBook
	 * @return
	 */
	public List<String[]> readRow(Sheet sheet) {

		// 获取到最后一行的的行号, 行号是从0开始的
		int _rowNum = sheet.getLastRowNum();
		List<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i <= _rowNum; i++) {
			Row row = sheet.getRow(i);
			String[] obj = null;
			if (null != row) {
				obj = readCells(row);
			}
			list.add(obj);

		}

		return list;

	}

	/**
	 * 读取每一行的单元格数据
	 * 
	 * @param row
	 * @return
	 */
	private String[] readCells(Row row) {

		int _cellCount = row.getPhysicalNumberOfCells();
		String[] values = new String[_cellCount];
		for (int i = 0; i < _cellCount; i++) {
			Cell cell = row.getCell(i);
			if (null != cell) {
				String value = null;
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_FORMULA:
						value = "" + cell.getCellFormula();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						// 判断是否是日期格式数据
						if (DateUtil.isCellDateFormatted(cell)) {
							value = DateUtil.formatDate(cell.getDateCellValue(), "yyyy-MM-dd");
						} else {
							value = "" + cell.getNumericCellValue();
						}
						break;
					case Cell.CELL_TYPE_STRING:
						value = "" + cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						value = "" + cell.getBooleanCellValue();
						break;
					case Cell.CELL_TYPE_BLANK:
						value = "";
					default:
				}
				values[i] = value;
			}
		}
		return values;

	}
}
