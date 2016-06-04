<<<<<<< HEAD:src/main/java/com/zhang/ivan/imp2exp/col/math/AbstractDataMath.java
package com.zhang.ivan.imp2exp.col.math;
=======
package com.zhang.ivan.imp2exp.colmath;
>>>>>>> master:src/main/java/com/zhang/ivan/imp2exp/colmath/AbstractDataMath.java

import java.util.Map;

import com.zhang.ivan.imp2exp.util.database.BaseDataConnection;

public abstract class AbstractDataMath implements DataMath {
	
	private BaseDataConnection baseDataConnection;
	
	private Map<String, Object> inputParams;

	public BaseDataConnection getBaseDataConnection() {
		return baseDataConnection;
	}

	public void setBaseDataConnection(BaseDataConnection baseDataConnection) {
		this.baseDataConnection = baseDataConnection;
	}

	public Map<String, Object> getInputParams() {
		return inputParams;
	}

	public void setInputParams(Map<String, Object> inputParams) {
		this.inputParams = inputParams;
	}
}
