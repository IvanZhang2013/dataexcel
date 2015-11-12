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
	 * 默认情况下进行我完全导入
	 */
	protected boolean isAll;
	/**
	 * 单Sheet页还是多Sheet也导入 默认情况下是单页导入
	 */
	protected boolean isOneSheet;
	/**
	 * 从第几个Sheet页开始读取数据 默认情况下从第一个Sheet页开始 即从0开始
	 */
	protected int isStartSheet;

	public ExcelConfig() {
		this.sizeKB = 0;
		this.row = (long) 5000;
		this.isAll = true;
		this.isOneSheet = true;
		this.isStartSheet = 0;
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

	public boolean isAll() {
		return isAll;
	}

	public void setAll(boolean isAll) {
		this.isAll = isAll;
	}

	public boolean isOneSheet() {
		return isOneSheet;
	}

	public void setOneSheet(boolean isOneSheet) {
		this.isOneSheet = isOneSheet;
	}

	public int getIsStartSheet() {
		return isStartSheet;
	}

	public void setIsStartSheet(int isStartSheet) {
		this.isStartSheet = isStartSheet;
	}

}
