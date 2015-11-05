package com.zhang.ivan.imp2exp.bean;

/**
 * author: kin wong
 *
 * class desc:批量导入信息VO类
 */
public class BatchImportInfoVO extends BaseBatchImp {
	private String tableName;// 表名
	private TableFieldInfoVO[] fieldInfo;// 字段信息
	private int otherFieldLength; // 其它字段个数

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

	public int getOtherFieldLength() {
		return otherFieldLength;
	}

	public void setOtherFieldLength(int otherFieldLength) {
		this.otherFieldLength = otherFieldLength;
	}
}
