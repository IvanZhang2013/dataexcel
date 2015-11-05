package com.zhang.ivan.imp2exp.bean;

/**
 * 字段模版抽象类
 * 
 */
public abstract class ColumnTemplate {
	
	/**
	 * 字段代码
	 * */
	private String ctCode;
	/**
	 * 字段名称
	 * */
	private String ctNm;

	public String getCtCode() {
		return ctCode;
	}

	public void setCtCode(String ctCode) {
		this.ctCode = ctCode;
	}

	public String getCtNm() {
		return ctNm;
	}

	public void setCtNm(String ctNm) {
		this.ctNm = ctNm;
	}

}
