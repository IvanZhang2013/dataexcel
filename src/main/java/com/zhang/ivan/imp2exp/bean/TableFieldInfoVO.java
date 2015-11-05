package com.zhang.ivan.imp2exp.bean;

/**
 * class desc:待导入的数据表字段信息VO类
 */
public class TableFieldInfoVO {
	private String fieldsName;//字段名（必填）
	private String fieldsDesc;//字段中文描述（必填）
	private boolean isKey;//主键
	private boolean isUnique;//是否唯一约束
	private String regex;//正则表达式
	private String limitDesc;//限制描述
	private String defaultValue;//如果可以为空，为空时的默认值
	private String fieldValue;//值
	//校验数据 正确性（数据与表关联）
	private boolean isTableValue;
	//校验数据 正确性（数据与表关联）检验数据关联性表语句
	private String isTableSql;
	/**
	 * 设置默认值
	 */
	public TableFieldInfoVO(){
		isKey = false;//非主键
		regex = "";//可为空
		isUnique = false;//可以不唯一
		limitDesc = "";//无限制
		defaultValue = "";//默认值为空
		isTableValue=false;
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

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getLimitDesc() {
		return limitDesc;
	}

	public void setLimitDesc(String limitDesc) {
		this.limitDesc = limitDesc;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	public boolean isTableValue() {
		return isTableValue;
	}

	public void setTableValue(boolean isTableValue) {
		this.isTableValue = isTableValue;
	}

	public String getIsTableSql() {
		return isTableSql;
	}

	public void setIsTableSql(String isTableSql) {
		this.isTableSql = isTableSql;
	}

}
