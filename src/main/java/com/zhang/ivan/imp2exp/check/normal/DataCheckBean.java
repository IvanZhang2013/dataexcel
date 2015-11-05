package com.zhang.ivan.imp2exp.check.normal;

import java.util.List;

public class DataCheckBean {

	private String checkClass;
	private int[] checkColumn;
	private String operName;
	private List<Object> paramvalue;
	private String edsc;

	public String getCheckClass() {
		return checkClass;
	}

	public void setCheckClass(String checkClass) {
		this.checkClass = checkClass;
	}

	public int[] getCheckColumn() {
		return checkColumn;
	}

	public void setCheckColumn(int[] checkColumn) {
		this.checkColumn = checkColumn;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public List<Object> getParamvalue() {
		return paramvalue;
	}

	public void setParamvalue(List<Object> paramvalue) {
		this.paramvalue = paramvalue;
	}

	public String getEdsc() {
		return edsc;
	}

	public void setEdsc(String edsc) {
		this.edsc = edsc;
	}

}
