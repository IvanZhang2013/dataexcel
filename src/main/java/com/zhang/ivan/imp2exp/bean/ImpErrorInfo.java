package com.zhang.ivan.imp2exp.bean;

public abstract class ImpErrorInfo {

	private long id;

	private long rowIndex;

	private String errorInfo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(long rowIndex) {
		this.rowIndex = rowIndex;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

}
