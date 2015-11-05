package com.zhang.ivan.imp2exp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;

public class ExcelReader {
	private Workbook wb;
	private Sheet sheet;
	private Row row;
	private DyadicArray<String> dyadicArray;
	
	
	public ExcelReader(Workbook wb) {
		this.wb = wb;
	}

	public DyadicArray<String> readExcel() {
		sheet = wb.getSheetAt(0);
		/**
		 * 导入表格的时候为标题头和列项目
		 */
		int rowSize = sheet.getLastRowNum() - 1;
		int columnSize = sheet.getRow(2).getPhysicalNumberOfCells();
		dyadicArray = new DyadicArray<String>(rowSize, columnSize);

		for (int i = 0; i <= rowSize-1; i++) {
			row = sheet.getRow(i + 2);
			int j = 0;
			while (j < columnSize) {
				if(row==null){
					dyadicArray.set(i, j, null);
				}else{
					dyadicArray.set(i, j, getCellFormatValue(row.getCell((short) j)).trim());
				}
				
				j++;
			}
		}
		return dyadicArray;
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
					cellvalue = String.valueOf(cell.getNumericCellValue());
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
}
