package com.zhang.ivan.imp2exp.file;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelFileUtils {
	
	private WritableWorkbook writeBook;// 可读写的excel实例
	private WritableSheet writeSheet;// 可读写的excel工作簿实例
	private Workbook book;// excel实例
	@SuppressWarnings("unused")
	private Sheet sheet;// excel工作簿的实例

	public void fileFormate(String filePath) throws BiffException, IOException, WriteException {
		File file = new File(filePath);
		book = Workbook.getWorkbook(file);
		writeBook = Workbook.createWorkbook(file, book);
		writeSheet = writeBook.getSheet(0);

		/* 删除空行 */
		int row = writeSheet.getRows();
		int column = writeSheet.getColumns();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (!"".equals(writeSheet.getCell(j, i).getContents().trim()))
					break;
				else if (j == column - 1) {
					writeSheet.removeRow(i);
					row--;
					i--;
				}
			}
		}

		/* 删除空列 */
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				if (!"".equals(writeSheet.getCell(i, j).getContents().trim()))
					break;
				else if (j == row - 1) {
					writeSheet.removeColumn(i);
					column--;
					i--;
				}
			}
		}

		writeBook.write();
		writeBook.close();
		book.close();
	}
}
