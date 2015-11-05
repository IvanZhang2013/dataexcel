package com.zhang.ivan.imp2exp.totemp;

import com.zhang.ivan.imp2exp.context.ExcelAppContext;

public abstract class AbstractWriteTemplate {

	protected ExcelAppContext excelAppContext;

	public ExcelAppContext getExcelAppContext() {
		return excelAppContext;
	}

	public void setExcelAppContext(ExcelAppContext excelAppContext) {
		this.excelAppContext = excelAppContext;
	}

}
