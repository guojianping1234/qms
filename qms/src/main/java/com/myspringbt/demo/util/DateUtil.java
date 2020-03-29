package com.myspringbt.demo.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 判断是否是日期格式数据
	 * 
	 * @param cell
	 * @return
	 */
	public static boolean isCellDateFormatted(Cell cell) {

		if (HSSFDateUtil.isCellDateFormatted(cell)) {
			return true;
		}
		return false;

	}

	/**
	 * 将日期格式的数据按照预定的格式进行转换
	 * 
	 * @param date
	 * @param formatGeshi
	 * @return
	 */
	public static String formatDate(Date date, String formatGeshi) {

		SimpleDateFormat format = new SimpleDateFormat(formatGeshi);

		return format.format(date);

	}
}
