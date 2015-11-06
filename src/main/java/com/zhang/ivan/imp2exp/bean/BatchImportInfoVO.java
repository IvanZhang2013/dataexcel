package com.zhang.ivan.imp2exp.bean;

/**
 * author: kin wong
 *
 * class desc:批量导入信息VO类
 */
public class BatchImportInfoVO {
	private String tableName;// 表名
	private String tabledesc;
	private TableFieldInfoVO[] fieldInfo;// 字段信息
	private ColumnFieldInfoVO[] otherfieldInfo;// 其他字段信息

	public String getTabledesc() {
		return tabledesc;
	}

	public void setTabledesc(String tabledesc) {
		this.tabledesc = tabledesc;
	}

	public ColumnFieldInfoVO[] getOtherfieldInfo() {
		return otherfieldInfo;
	}

	public void setOtherfieldInfo(ColumnFieldInfoVO[] otherfieldInfo) {
		this.otherfieldInfo = otherfieldInfo;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public TableFieldInfoVO[] getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(TableFieldInfoVO[] fieldInfo) {
		this.fieldInfo = fieldInfo;
	}

}
