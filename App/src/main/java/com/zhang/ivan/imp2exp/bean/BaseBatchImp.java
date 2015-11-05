package com.zhang.ivan.imp2exp.bean;

public abstract class BaseBatchImp {
	/**
	 * 此类可以进行添加出入标过程中的 ID 等其他字段
	 * 
	 */
	/**
	 * 操作描述
	 */
	protected String operDesc;

	public String getOperDesc() {
		return operDesc;
	}

	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}

}
