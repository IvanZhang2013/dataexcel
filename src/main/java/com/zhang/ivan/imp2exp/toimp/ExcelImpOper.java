package com.zhang.ivan.imp2exp.toimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ColumnFieldInfoVO;
import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.bean.TableFieldInfoVO;
import com.zhang.ivan.imp2exp.check.IExcelCheck;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;
import com.zhang.ivan.imp2exp.check.normal.ExcelCheckContext;
import com.zhang.ivan.imp2exp.common.DataExcelException;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.util.DyadicArray;

public class ExcelImpOper {

	public static String initSql(ExcelAppContext excelAppContext) throws Exception {
		StringBuffer stringBufferstart = new StringBuffer();
		StringBuffer stringBufferend = new StringBuffer();
		BatchImportInfoVO batchImportInfoVO = excelAppContext.getBatchImportInfoVO();
		TableFieldInfoVO[] fields = batchImportInfoVO.getFieldInfo();
		ColumnFieldInfoVO[] columns = batchImportInfoVO.getOtherfieldInfo();
		stringBufferstart.append("INSERT INTO ").append(excelAppContext.getBatchImportInfoVO().getTableName())
				.append(" ( ");

		TableFieldInfoVO tf;

		for (int i = 0; i < fields.length; i++) {
			tf = fields[i];
			if (tf == null) {
				throw new DataExcelException("解析导入语句失败");
			}

			if (i == (fields.length - 1)) {
				stringBufferstart.append(tf.getFieldsName());
				stringBufferend.append("?");
			} else {
				stringBufferstart.append(tf.getFieldsName()).append(", ");
				stringBufferend.append("? ,");
			}

		}

		ColumnFieldInfoVO cf = null;
		;
		if (columns != null) {
			for (int i = 0; i < columns.length; i++) {
				cf = columns[i];
				if (cf == null) {
					throw new DataExcelException("解析导入语句失败");
				}
				if (i == (fields.length - 1)) {
					stringBufferstart.append(cf.getColumnName());
					stringBufferend.append("?");
				} else {
					stringBufferstart.append(cf.getColumnName()).append(", ");
					stringBufferend.append("? ,");

				}
			}
		}
		stringBufferstart.append(" ) VALUES (").append(stringBufferend.toString()).append(")");

		return stringBufferstart.toString();

	}

	public static boolean excute(ExcelCheckContext excelCheckContext, ExcelAppContext excelAppContext)
			throws Exception {
		// 默认值的计算 需要在那里进行初始化
		DyadicArray<String> dyadicArray = excelCheckContext.getDyadicArray();
		List<DataCheckBean> list = excelCheckContext.getCheckbeanlist();
		for (int i = 0; i < list.size(); i++) {
			DataCheckBean checkBean = list.get(i);
			Class<?> cl = Class.forName(checkBean.getCheckClass());
			IExcelCheck excelCheck = null;
			if (cl.newInstance() instanceof IExcelCheck) {
				IExcelCheck toImp = (IExcelCheck) cl.newInstance();
			} else {
				throw new DataExcelException("公示转化错误！");
			}
			List<ImpErrorInfo> list1 = excelCheck.excute(checkBean);
		}
		return false;
	}

	public static boolean toDataBase(String sql, ExcelCheckContext excelCheckContext, ExcelAppContext excelAppContext)
			throws Exception {

		Connection connection = null;
		PreparedStatement ps = null;
		DyadicArray<String> dyadicArray = excelCheckContext.getDyadicArray();
		if (dyadicArray == null) {
			throw new DataExcelException("数据导入读取数据为NULL！");

		}
		try {
			connection = excelAppContext.getBaseDataConnection().getDataSource().getConnection();
			ps = connection.prepareStatement(sql);
			for (int i = 0; i < dyadicArray.getRowSize(); i++) {
				for (int j = 0; j < dyadicArray.getColumnSize(); j++) {
					ps.setString(j + 1, dyadicArray.get(i, j));

					if (j == dyadicArray.getColumnSize() - 1) {
						ps.addBatch();
					}

				}
				if ((i + 1) % 100 == 0 || i == (dyadicArray.getRowSize() - 1)) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DataExcelException("数据 执行导入失败！");
		} finally {
			if (ps != null) {
				ps.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}

}
