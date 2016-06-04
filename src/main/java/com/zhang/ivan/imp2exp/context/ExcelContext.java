package com.zhang.ivan.imp2exp.context;

import com.zhang.ivan.imp2exp.check.bean.ExcelCheckContext;

public class ExcelContext {
	private ExcelAppContext excelAppContext;
	private ExcelCheckContext excelCheckContext;

	public ExcelContext() {

	}

	public ExcelContext(ExcelAppContext excelAppContext, ExcelCheckContext excelCheckContext) {
		super();
		this.excelAppContext = excelAppContext;
		this.excelCheckContext = excelCheckContext;
	}

	public ExcelAppContext getExcelAppContext() {
		return excelAppContext;
	}

	public void setExcelAppContext(ExcelAppContext excelAppContext) {
		this.excelAppContext = excelAppContext;
	}

	public ExcelCheckContext getExcelCheckContext() {
		return excelCheckContext;
	}

	public void setExcelCheckContext(ExcelCheckContext excelCheckContext) {
		this.excelCheckContext = excelCheckContext;
	}

}
