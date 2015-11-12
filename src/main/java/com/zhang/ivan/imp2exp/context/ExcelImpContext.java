package com.zhang.ivan.imp2exp.context;

import com.zhang.ivan.imp2exp.check.normal.ExcelCheckContext;

public interface ExcelImpContext {

	public ExcelContext initContext(String xmlPatch);

	public ExcelContext initContext(ExcelAppContext excelAppContext, ExcelCheckContext excelCheckContext);
}
