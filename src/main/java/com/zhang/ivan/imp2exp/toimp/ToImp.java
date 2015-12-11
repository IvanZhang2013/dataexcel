package com.zhang.ivan.imp2exp.toimp;

import com.zhang.ivan.imp2exp.bean.ImpExcelTemplate;
import com.zhang.ivan.imp2exp.check.bean.ExcelCheckContext;
import com.zhang.ivan.imp2exp.check.bean.ExcelResult;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;

public interface ToImp {

	public ExcelResult excute(ImpExcelTemplate imp, ExcelCheckContext e) throws Exception;

	public void setExcelAppContext(ExcelAppContext excelAppContext);

}
