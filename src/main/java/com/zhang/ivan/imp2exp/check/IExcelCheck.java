package com.zhang.ivan.imp2exp.check;

import java.util.List;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;

public interface IExcelCheck {

	public List<ImpErrorInfo> excute(List<ImpErrorInfo> list, DataCheckBean dataCheckBean,
			ExcelAppContext excelAppContext) throws Exception;

}
