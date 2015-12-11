package com.zhang.ivan.imp2exp.check;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.bean.DataCheckBean;
import com.zhang.ivan.imp2exp.check.oper.DataBaseResult;
import com.zhang.ivan.imp2exp.check.oper.IExcelCheck;
import com.zhang.ivan.imp2exp.common.DataExcelException;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;

public class RelatedDataCheck extends DataBaseResult implements IExcelCheck {

	@Override
	public List<ImpErrorInfo> excute(List<ImpErrorInfo> list, DataCheckBean dataCheckBean) throws Exception {
		int[] params = getColdIndex();
		int rowsize = getDataResult().getRowSize();

		if (params == null) {
			throw new DataExcelException("校验公式colIds定义错误！");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getBaseDataConnection().getDataSource().getConnection();
			ps = connection.prepareStatement(dataCheckBean.getSqlText());

			for (int i = 0; i < rowsize; i++) {
				ps.clearParameters();
				Object[] strRow = getDataResult().getRow(i);
				for (int j = 0; j < params.length; j++) {
					ps.setString(j + 1, strRow[params[j]].toString());
				}
				rs = ps.executeQuery();
				if (!(rs.next())) {
					ImpErrorInfo impErrorInfo = new ImpErrorInfo();
					impErrorInfo.setRowIndex(i);
					impErrorInfo.setErrorInfo(dataCheckBean.getDesc());
					list.add(impErrorInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return list;
	}
}
