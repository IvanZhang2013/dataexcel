package com.zhang.ivan.imp2exp.check.normal;

import java.util.List;
import java.util.Map;

import com.zhang.ivan.imp2exp.util.DyadicArray;

public abstract class AbstractCheckContext {

	protected Map<String, List<DataCheckBean>> checkMap;

	private DyadicArray<String> dyadicArray;

	public Map<String, List<DataCheckBean>> getCheckMap() {
		return checkMap;
	}

	public void setCheckMap(Map<String, List<DataCheckBean>> checkMap) {
		this.checkMap = checkMap;
	}

	public DyadicArray<String> getDyadicArray() {
		return dyadicArray;
	}

	public void setDyadicArray(DyadicArray<String> dyadicArray) {
		this.dyadicArray = dyadicArray;
	}

}
