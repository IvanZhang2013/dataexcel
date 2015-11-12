package com.zhang.ivan.imp2exp.bean;

/**
 * class desc:待导入的数据表字段信息VO类
 */
public class TableFieldInfoVO {
	private String fieldsName;// 字段名（必填）
	private String fieldsDesc;// 字段中文描述（必填）
	private String defaultValue;// 如果可以为空，为空时的默认值
	private String className;

	/**
	 * 设置默认值
	 */
	public TableFieldInfoVO() {
		defaultValue = "";// 默认值为空

	}

	public String getFieldsName() {
		return fieldsName;
	}

	public void setFieldsName(String fieldsName) {
		this.fieldsName = fieldsName;
	}

	public String getFieldsDesc() {
		return fieldsDesc;
	}

	public void setFieldsDesc(String fieldsDesc) {
		this.fieldsDesc = fieldsDesc;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
