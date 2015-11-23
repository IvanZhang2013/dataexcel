package com.zhang.ivan.imp2exp.check.oper;

import com.zhang.ivan.imp2exp.util.database.BaseDataConnection;

/**
 * 进行数据库校验的抽象类存储属性
 */

public abstract class DataBaseResult extends AbstractDataBaseResult implements IExcelCheck {

	private BaseDataConnection baseDataConnection;

	public BaseDataConnection getBaseDataConnection() {
		return baseDataConnection;
	}

	public void setBaseDataConnection(BaseDataConnection baseDataConnection) {
		this.baseDataConnection = baseDataConnection;
	}

}
