package com.zhang.ivan.imp2exp.toexp;

import com.zhang.ivan.imp2exp.bean.ExpExcelTemplate;
import com.zhang.ivan.imp2exp.util.ExcelType;

public interface ToExp {
	
	public void excute(ExpExcelTemplate exp, Object obj) throws Exception;
	
	public void setExcelType(ExcelType em) throws Exception;
	
}
