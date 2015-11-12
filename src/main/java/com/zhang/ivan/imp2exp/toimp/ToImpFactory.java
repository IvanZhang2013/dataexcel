package com.zhang.ivan.imp2exp.toimp;

import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.util.PoiExcelType;

public class ToImpFactory {

	public static ToImp getWorkbook(PoiExcelType pt, ExcelAppContext excelAppContext) throws Exception {
		Class<?> cl = Class.forName(pt.getExcelClassName());
		ToImp toImp = (ToImp) cl.newInstance();
		toImp.setExcelAppContext(excelAppContext);
		return toImp;
	}

}
