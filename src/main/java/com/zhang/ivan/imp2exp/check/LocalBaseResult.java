package com.zhang.ivan.imp2exp.check;

import com.zhang.ivan.imp2exp.util.DyadicArray;

/**
 * 进行本地校验的抽象类存储属性
 */
public abstract class LocalBaseResult implements IExcelCheck  {

	protected DyadicArray<String> dataResult;

	public DyadicArray<String> getDataResult() {
		return dataResult;
	}

	public void setDataResult(DyadicArray<String> dataResult) {
		this.dataResult = dataResult;
	}
}
