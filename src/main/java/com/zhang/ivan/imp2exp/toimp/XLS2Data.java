package com.zhang.ivan.imp2exp.toimp;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhang.ivan.imp2exp.bean.ImpExcelTemplate;
import com.zhang.ivan.imp2exp.check.IExcelCheck;
import com.zhang.ivan.imp2exp.check.normal.ExcelCheckContext;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.util.DyadicArray;
import com.zhang.ivan.imp2exp.util.ExcelReader;

/**
 * 生成xlsx文件的工具类
 */
public class XLS2Data extends AbstractExcel2Data implements ToImp {
	
	private Workbook xlsWorkBook;
	private ExcelAppContext excelAppContext;

	public static Logger logger = LoggerFactory.getLogger(XLS2Data.class);

	public void excute(ImpExcelTemplate imp,ExcelCheckContext e) throws Exception {
		String uploadFile = imp.getUploadFilePath();
		xlsWorkBook = new HSSFWorkbook(new FileInputStream(uploadFile));
		ExcelReader excelReader = new ExcelReader(xlsWorkBook);
		DyadicArray<String> dyadicArray =excelReader.readExcel();
		 
	}

	public void setExcelAppContext(ExcelAppContext excelAppContext) {
		this.excelAppContext = excelAppContext;
	}

	public Workbook getXlsWorkBook() {
		return xlsWorkBook;
	}

	public void setXlsWorkBook(Workbook xlsWorkBook) {
		this.xlsWorkBook = xlsWorkBook;
	}

	public ExcelAppContext getExcelAppContext() {
		return excelAppContext;
	}

}
