package com.zhang.ivan.imp2exp.check.bean;

import java.util.List;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;

public class ExcelResult {

	private boolean status;

	private List<ImpErrorInfo> listError;

	private int index;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<ImpErrorInfo> getListError() {
		return listError;
	}

	public void setListError(List<ImpErrorInfo> listError) {
		this.listError = listError;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
