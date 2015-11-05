package com.zhang.ivan.imp2exp.bean;

import com.zhang.ivan.imp2exp.bean.ibean.IExcuteValue;

/**
 * 再导入文件中不存在的列的信息
 */
public class ColumnFieldInfoVO {
	// 字段名（必填）
	private String columnName;
	// 字段中文描述（必填）
	private String fieldsDesc;
	// 字段中文描述（必填） -->主要是为了应对生成策略生成数据的时候产生异常
	private String exceptionDesc;
	// 取值方法
	private IExcuteValue iExcuteValue;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getFieldsDesc() {
		return fieldsDesc;
	}

	public void setFieldsDesc(String fieldsDesc) {
		this.fieldsDesc = fieldsDesc;
	}

	public String getExceptionDesc() {
		return exceptionDesc;
	}

	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}

	public IExcuteValue getiExcuteValue() {
		return iExcuteValue;
	}

	public void setiExcuteValue(IExcuteValue iExcuteValue) {
		this.iExcuteValue = iExcuteValue;
	}

}
