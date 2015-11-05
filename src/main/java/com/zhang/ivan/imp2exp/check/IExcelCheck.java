package com.zhang.ivan.imp2exp.check;

import java.util.List;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;

public interface IExcelCheck {

	public void init();

	public List<ImpErrorInfo> excute(DataCheckBean dataCheckBean) throws Exception;

}
