package com.zhang.ivan.imp2exp.check.oper;

import com.zhang.ivan.imp2exp.util.DyadicArray;

public class AbstractDataBaseResult {

	protected DyadicArray<String> dataResult;

	protected int[] coldIndex;

	public DyadicArray<String> getDataResult() {
		return dataResult;
	}

	public void setDataResult(DyadicArray<String> dataResult) {
		this.dataResult = dataResult;
	}

	public int[] getColdIndex() {
		return coldIndex;
	}

	public void setColdIndex(int[] coldIndex) {
		this.coldIndex = coldIndex;
	}

}
