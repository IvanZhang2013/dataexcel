package com.zhang.ivan.imp2exp.check.bean;

import java.util.List;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;

public class ExcelCheckContext extends AbstractCheckContext {

	private List<ImpErrorInfo> listErrorInfo;

	public List<ImpErrorInfo> getListErrorInfo() {
		return listErrorInfo;
	}

	public void setListErrorInfo(List<ImpErrorInfo> listErrorInfo) {
		this.listErrorInfo = listErrorInfo;
	}

}
