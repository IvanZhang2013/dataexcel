package com.zhang.ivan.imp2exp.toimp;

import com.zhang.ivan.imp2exp.BaseDataConnection;


public abstract class AbstractExcel2Data {
	
	protected BaseDataConnection  baseDataConnection;

	public BaseDataConnection getBaseDataConnection() {
		return baseDataConnection;
	}

	public void setBaseDataConnection(BaseDataConnection baseDataConnection) {
		this.baseDataConnection = baseDataConnection;
	}


}
