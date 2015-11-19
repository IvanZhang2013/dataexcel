package com.zhang.ivan.imp2exp.bean;

public class ExcelConfig {

	/**
	 * 默认情况下不限制文件的大小
	 */
	protected double sizeKB;
	/**
	 * 默认情况下限制文件的导入行数为 5000；
	 */
	protected long row;

	/**
	 * xml 配置文件路径
	 */

	protected String xPatch;

	public ExcelConfig() {
		this.sizeKB = 0;
		this.row = (long) 5000;
	}

	public String getxPatch() {
		return xPatch;
	}

	public void setxPatch(String xPatch) {
		this.xPatch = xPatch;
	}

	public double getSizeKB() {
		return sizeKB;
	}

	public void setSizeKB(double sizeKB) {
		this.sizeKB = sizeKB;
	}

	public long getRow() {
		return row;
	}

	public void setRow(long row) {
		this.row = row;
	}

}
