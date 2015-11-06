package com.zhang.ivan.imp2exp.check.normal;

import java.util.List;

public abstract class AbstractCheckContext {

	protected List<DataCheckBean> checkbeanlist;

	public List<DataCheckBean> getCheckbeanlist() {
		return checkbeanlist;
	}

	public void setCheckbeanlist(List<DataCheckBean> checkbeanlist) {
		this.checkbeanlist = checkbeanlist;
	}

}
