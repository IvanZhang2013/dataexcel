package com.zhang.ivan.imp2exp.toimp;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhang.ivan.imp2exp.bean.ImpExcelTemplate;
import com.zhang.ivan.imp2exp.check.normal.ExcelCheckContext;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.util.ExcelReader;

/**
 * 生成xlsx文件的工具类
 */
public class XLSX2Data extends AbstractExcel2Data implements ToImp {

	private Workbook xlsxWorkBook;// excel实例
	private ExcelAppContext excelAppContext;

	public static Logger logger = LoggerFactory.getLogger(XLS2Data.class);

	@Override
	public void excute(ImpExcelTemplate imp ,ExcelCheckContext e) throws Exception {
		String uploadFile = imp.getUploadFilePath();
		xlsxWorkBook = new XSSFWorkbook(new FileInputStream(uploadFile));
		ExcelReader excelReader = new ExcelReader(xlsxWorkBook);
		excelReader.readExcel();
		
	}

	public void setExcelAppContext(ExcelAppContext excelAppContext) {
		this.excelAppContext = excelAppContext;
	}

	public Workbook getXlsxWorkBook() {
		return xlsxWorkBook;
	}

	public void setXlsxWorkBook(Workbook xlsxWorkBook) {
		this.xlsxWorkBook = xlsxWorkBook;
	}

	public ExcelAppContext getExcelAppContext() {
		return excelAppContext;
	}

}
