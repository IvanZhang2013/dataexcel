package com.zhang.ivan.imp2exp.toimp;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhang.ivan.imp2exp.bean.ImpExcelTemplate;
import com.zhang.ivan.imp2exp.check.bean.ExcelCheckContext;
import com.zhang.ivan.imp2exp.check.bean.ExcelResult;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;

/**
 * 读取xls文件的工具类
 */
public class XLS2Data extends AbstractExcel2Data implements ToImp {

	private Workbook xlsWorkBook;
	private ExcelAppContext excelAppContext;

	public static Logger logger = LoggerFactory.getLogger(XLS2Data.class);

	public ExcelResult excute(ImpExcelTemplate imp, ExcelCheckContext excelCheckContext) throws Exception {
		String uploadFile = imp.getUploadFilePath();
		xlsWorkBook = new HSSFWorkbook(new FileInputStream(uploadFile));
		ExcelResult excelResult = JupiterService.jupiterLanceService(xlsWorkBook, excelAppContext, excelCheckContext);
		return excelResult;
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
