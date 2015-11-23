package com.zhang.ivan.imp2exp.check.bean;

public class DataCheckBean {

	private String type;
	private String[] colIds;
	private String regexText;
	private String sqlText;
	private String desc;
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getColIds() {
		return colIds;
	}

	public void setColIds(String[] colIds) {
		this.colIds = colIds;
	}

	public String getRegexText() {
		return regexText;
	}

	public void setRegexText(String regexText) {
		this.regexText = regexText;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
