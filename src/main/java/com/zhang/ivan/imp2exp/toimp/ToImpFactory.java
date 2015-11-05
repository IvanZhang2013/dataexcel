package com.zhang.ivan.imp2exp.toimp;

import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.context.IExcelAppContext;
import com.zhang.ivan.imp2exp.util.PoiExcelType;

public class ToImpFactory {

	private IExcelAppContext iExcelAppContext;

	public static ToImp getWorkbook(PoiExcelType pt, ExcelAppContext excelAppContext) throws Exception {
		Class<?> cl = Class.forName(pt.getExcelClassName());
		ToImp toImp = (ToImp) cl.newInstance();
		toImp.setExcelAppContext(excelAppContext);
		return toImp;
	}

	public IExcelAppContext getiExcelAppContext() {
		return iExcelAppContext;
	}

	public void setiExcelAppContext(IExcelAppContext iExcelAppContext) {
		this.iExcelAppContext = iExcelAppContext;
	}
}
