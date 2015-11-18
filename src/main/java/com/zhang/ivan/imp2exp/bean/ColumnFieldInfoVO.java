package com.zhang.ivan.imp2exp.bean;

/**
 * 再导入文件中不存在的列的信息
 */
public class ColumnFieldInfoVO extends FileldInfoVO {
	// 取值方法
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
