package com.zhang.ivan.imp2exp.check;

import java.util.ArrayList;
import java.util.List;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;

/**
 * 责任进行传递
 */

public class ToImpCheckBean implements IExcelCheck {

	public void init() {

	}

	
	@Override
	public List<ImpErrorInfo> excute(List<ImpErrorInfo> list, DataCheckBean dataCheckBean,
			ExcelAppContext excelAppContext) throws Exception {
		
		return list;
	}

}
