package com.zhang.ivan.imp2exp.util.datatype;

public abstract class AbstractDataType {

	// 正则表达式
	private String regx;
	// 字符数值
	private String value;

	public String getRegx() {
		return regx;
	}

	public void setRegx(String regx) {
		this.regx = regx;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
