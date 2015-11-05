package com.zhang.ivan.imp2exp.totemp;

import com.zhang.ivan.imp2exp.context.ExcelAppContext;

public class ToExccelTemplate extends AbstractWriteTemplate {

	public static void excuteTemplate(ExcelAppContext excelAppContext) {
		WriteTemplate writeTemplate = new WriteTemplate();
		writeTemplate.setExcelAppContext(excelAppContext);
		writeTemplate.excuteTemplate();
	}

}
