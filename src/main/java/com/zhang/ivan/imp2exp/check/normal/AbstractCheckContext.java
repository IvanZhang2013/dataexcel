package com.zhang.ivan.imp2exp.check.normal;

import java.util.List;

import com.zhang.ivan.imp2exp.util.DyadicArray;

public abstract class AbstractCheckContext {

	protected List<DataCheckBean> checkbeanlist;

	private DyadicArray<String> dyadicArray;

	public List<DataCheckBean> getCheckbeanlist() {
		return checkbeanlist;
	}

	public void setCheckbeanlist(List<DataCheckBean> checkbeanlist) {
		this.checkbeanlist = checkbeanlist;
	}

	public DyadicArray<String> getDyadicArray() {
		return dyadicArray;
	}

	public void setDyadicArray(DyadicArray<String> dyadicArray) {
		this.dyadicArray = dyadicArray;
	}

}
