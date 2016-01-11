package com.zhang.ivan.imp2exp.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;

import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;

public class ExcelReader {
	private Workbook wb;
	private Sheet sheet;
	private Row row;

	public ExcelReader(Workbook wb) {
		this.wb = wb;
	}

	public Map<Integer, DyadicArray<String>> readExcel(
			ExcelAppContext excelAppContext) {
		Map<String, BatchImportInfoVO> map = excelAppContext.getMap();
		Set<String> set = map.keySet();
		BatchImportInfoVO batchImportInfoVO = null;
		Set<Integer> sheets = new HashSet<Integer>();
		String str = null;
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			str = iterator.next();
			batchImportInfoVO = map.get(str);
			int[] s = batchImportInfoVO.getIndex();
			for (int i = 0; i < s.length; i++) {
				sheets.add(s[i]);
			}
		}

		Map<Integer, DyadicArray<String>> dyMap = new HashMap<Integer, DyadicArray<String>>();
		for (Iterator<Integer> iterator = sheets.iterator(); iterator.hasNext();) {
			int object = iterator.next();

			sheet = wb.getSheetAt(object - 1);
			/**
			 * 导入表格的时候为标题头和列项目
			 */
			int rowSize = sheet.getLastRowNum() - 1;
			int columnSize = sheet.getRow(1).getPhysicalNumberOfCells();
			DyadicArray<String> dyadicArray = new DyadicArray<String>(rowSize,
					columnSize);

			for (int i = 0; i <= rowSize - 1; i++) {
				row = sheet.getRow(i + 2);
				int j = 0;
				while (j < columnSize) {
					if (row == null) {
						dyadicArray.set(i, j, null);
					} else {
						dyadicArray.set(i, j,
								getCellFormatValue(row.getCell((short) j))
										.trim());
					}

					j++;
				}
			}

			dyMap.put(object, dyadicArray);
		}

		return dyMap;
	}

	private String getStringCellValue(Cell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}

	@SuppressWarnings("unused")
	private String getDateCellValue(Cell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == Cell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (new DateTime(date).toString("yyyy-MM-dd"));
			} else if (cellType == Cell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == Cell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	private String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
			case Cell.CELL_TYPE_FORMULA: {
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				} else {
					cellvalue = this.doubleToString(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING:
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}

	 public String doubleToString(double d)  
	    {  
	        String i = DecimalFormat.getInstance().format(d);  
	        String result = i.replaceAll(",", "");  
	        return result;  
	  
	    } 

}
