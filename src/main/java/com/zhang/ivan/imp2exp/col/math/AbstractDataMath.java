package com.zhang.ivan.imp2exp.col.math;

import com.zhang.ivan.imp2exp.util.database.BaseDataConnection;

public abstract class AbstractDataMath implements DataMath{
	private BaseDataConnection baseDataConnection;

	public BaseDataConnection getBaseDataConnection() {
		return baseDataConnection;
	}

	public void setBaseDataConnection(BaseDataConnection baseDataConnection) {
		this.baseDataConnection = baseDataConnection;
	}
}
