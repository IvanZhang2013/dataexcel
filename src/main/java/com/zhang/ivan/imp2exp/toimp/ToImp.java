package com.zhang.ivan.imp2exp.toimp;

import com.zhang.ivan.imp2exp.bean.ImpExcelTemplate;
import com.zhang.ivan.imp2exp.check.IExcelCheck;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;

public interface ToImp {

	public void excute(ImpExcelTemplate exp, IExcelCheck obj) throws Exception;

	public void setExcelAppContext(ExcelAppContext excelAppContext);

}
