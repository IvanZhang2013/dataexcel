package com.zhang.ivan.imp2exp.check.normal;

public class DataCheckBean {

	private String type;
	private int[] index;
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

	public int[] getIndex() {
		return index;
	}

	public void setIndex(int[] index) {
		this.index = index;
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
