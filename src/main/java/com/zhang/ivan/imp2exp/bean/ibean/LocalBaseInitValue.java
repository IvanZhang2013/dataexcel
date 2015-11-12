package com.zhang.ivan.imp2exp.bean.ibean;

import com.zhang.ivan.imp2exp.util.DyadicArray;

/**
 * 进行本地校验的抽象类存储属性
 */
public abstract class LocalBaseInitValue implements IExcuteInitColumnValue {

	protected DyadicArray<String> dataResult;

	public DyadicArray<String> getDataResult() {
		return dataResult;
	}

	public void setDataResult(DyadicArray<String> dataResult) {
		this.dataResult = dataResult;
	}
}
