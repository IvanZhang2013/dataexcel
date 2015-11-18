package com.zhang.ivan.imp2exp.bean;

/**
 * class desc:待导入的数据表字段信息VO类
 */
public class TableFieldInfoVO extends FileldInfoVO {

	private String defaultValue;// 如果可以为空，为空时的默认值
	private int colIndex;

	/**
	 * 设置默认值
	 */
	public TableFieldInfoVO() {
		defaultValue = "";// 默认值为空

	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getColIndex() {
		return colIndex;
	}

	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}

}
