package com.zhang.ivan.imp2exp.bean.ibean;

import com.zhang.ivan.imp2exp.BaseDataConnection;
import com.zhang.ivan.imp2exp.util.DyadicArray;

public class AbstracExcelValue {

	protected DyadicArray<String> dataResult;

	protected BaseDataConnection baseDataConnection;

	public DyadicArray<String> getDataResult() {
		return dataResult;
	}

	public void setDataResult(DyadicArray<String> dataResult) {
		this.dataResult = dataResult;
	}

	public BaseDataConnection getBaseDataConnection() {
		return baseDataConnection;
	}

	public void setBaseDataConnection(BaseDataConnection baseDataConnection) {
		this.baseDataConnection = baseDataConnection;
	}

}
