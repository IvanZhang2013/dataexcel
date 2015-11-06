package com.zhang.ivan.imp2exp.check;

import java.util.List;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;

public abstract class AbstractExcelCheck {

	protected ExcelAppContext excelAppContext;
	/**
	 * 校验接口类
	 */
	protected IExcelCheck iExcelCheck;
	/**
	 * 返回错误信息
	 */
	protected List<ImpErrorInfo> errorinfo;

	/**
	 * 主要应用于责任链进行传递的时候进行数据的对象复制
	 */
	public void copyObjectParam(AbstractExcelCheck abstractExcelCheck) {
		this.errorinfo = abstractExcelCheck.getErrorinfo();
		this.excelAppContext = abstractExcelCheck.getExcelAppContext();
	}

	public List<ImpErrorInfo> getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(List<ImpErrorInfo> errorinfo) {
		this.errorinfo = errorinfo;
	}

	public IExcelCheck getiExcelCheck() {
		return iExcelCheck;
	}

	public void setiExcelCheck(IExcelCheck iExcelCheck) {
		this.iExcelCheck = iExcelCheck;
	}

	public ExcelAppContext getExcelAppContext() {
		return excelAppContext;
	}

	public void setExcelAppContext(ExcelAppContext excelAppContext) {
		this.excelAppContext = excelAppContext;
	}

}
