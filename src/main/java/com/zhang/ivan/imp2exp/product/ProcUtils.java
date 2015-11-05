package com.zhang.ivan.imp2exp.product;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.zhang.ivan.imp2exp.BaseDataConnection;

public class ProcUtils {

	private BaseDataConnection baseDataConnection;

	public ProcMessge operProc(ProcBean procBean) throws Exception {
		String procSql = this.procSql(procBean);
		CallableStatement callableStatement = null;
		ProcMessge procMessge = null;
		try {
			callableStatement = baseDataConnection.getDataSource().getConnection().prepareCall(procSql);

			for (int j = 1; j <= procBean.getSize(); j++) {
				callableStatement.setString(j, procBean.getList().get(j));
			}

			callableStatement.registerOutParameter(procBean.getSize() + 1, Types.NVARCHAR);
			callableStatement.registerOutParameter(procBean.getSize() + 2, Types.NVARCHAR);

			callableStatement.execute();

			procMessge = new ProcMessge();

			procMessge.setStatus(callableStatement.getString(procBean.getSize() + 1));
			procMessge.setMessage(callableStatement.getString(procBean.getSize() + 2));

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception();
		}

		return procMessge;

	}

	private String procSql(ProcBean procBean) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("call { ").append(procBean.getProcName()).append(" (");
		for (int j = 0; j < procBean.getSize(); j++) {
			buffer.append("? ,");
		}
		buffer.append(" ? ,?)}");
		return buffer.toString();
	}

	public BaseDataConnection getBaseDataConnection() {
		return baseDataConnection;
	}

	public void setBaseDataConnection(BaseDataConnection baseDataConnection) {
		this.baseDataConnection = baseDataConnection;
	}

}
