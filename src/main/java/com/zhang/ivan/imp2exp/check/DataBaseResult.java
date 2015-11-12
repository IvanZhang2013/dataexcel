package com.zhang.ivan.imp2exp.check;

import com.zhang.ivan.imp2exp.BaseDataConnection;
import com.zhang.ivan.imp2exp.util.DyadicArray;

/**
 * 进行数据库校验的抽象类存储属性
 */

public abstract class DataBaseResult  {

	protected BaseDataConnection baseDataConnection;

	protected DyadicArray<String> dataResult;

	public BaseDataConnection getBaseDataConnection() {
		return baseDataConnection;
	}

	public void setBaseDataConnection(BaseDataConnection baseDataConnection) {
		this.baseDataConnection = baseDataConnection;
	}

	public DyadicArray<String> getDataResult() {
		return dataResult;
	}

	public void setDataResult(DyadicArray<String> dataResult) {
		this.dataResult = dataResult;
	}

}
