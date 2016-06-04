package com.zhang.ivan.imp2exp.context;

import com.zhang.ivan.imp2exp.check.bean.ExcelCheckContext;

public class AppContextFactory implements ExcelImpContext {

	@Override
	public ExcelContext initContext(String xmlPatch) {
		
		
		
		
		
		
		
		

		return null;
	}

	@Override
	public ExcelContext initContext(ExcelAppContext excelAppContext, ExcelCheckContext excelCheckContext) {
		return new ExcelContext(excelAppContext, excelCheckContext);
	}

}
