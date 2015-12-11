package com.zhang.ivan.imp2exp.toimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ColumnFieldInfoVO;
import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.bean.TableFieldInfoVO;
import com.zhang.ivan.imp2exp.check.bean.DataCheckBean;
import com.zhang.ivan.imp2exp.check.bean.ExcelCheckContext;
import com.zhang.ivan.imp2exp.check.oper.DataBaseResult;
import com.zhang.ivan.imp2exp.check.oper.IExcelCheck;
import com.zhang.ivan.imp2exp.check.oper.LocalBaseResult;
import com.zhang.ivan.imp2exp.common.DataExcelException;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.util.CheckType;
import com.zhang.ivan.imp2exp.util.DyadicArray;

public class AresService {

	/**
	 * 数据校验
	 */
	public static List<ImpErrorInfo> aresPretextService(ExcelCheckContext excelCheckContext,
			ExcelAppContext excelAppContext) throws Exception {
		List<ImpErrorInfo> impErrorInfos = new ArrayList<ImpErrorInfo>();
		Map<String, List<DataCheckBean>> map = excelCheckContext.getCheckMap();
		Set<String> set = map.keySet();

		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String str = iterator.next();
			List<DataCheckBean> list = map.get(str);
			for (int i = 0; i < list.size(); i++) {
				DataCheckBean checkBean = list.get(i);
				BatchImportInfoVO batchImportInfoVO = excelAppContext.getMap().get(str);
				int[] index = aresTankService(checkBean, batchImportInfoVO);
				if (checkBean == null) {
					throw new Exception("校验方法类为空无法进行校验");
				} else {
					String className = "";
					CheckType checkType = CheckType.getExcelType(checkBean.getType());
					if (checkType == null) {
						className = checkBean.getClassName();
					} else {
						className = checkType.getCheckClassName();
					}
					Class<?> cl = Class.forName(className);
					Object obj = cl.newInstance();

					IExcelCheck iExcelCheck = null;
					if (obj instanceof IExcelCheck) {

						if (obj instanceof DataBaseResult) {
							((DataBaseResult) obj).setBaseDataConnection(excelAppContext.getBaseDataConnection());
							// 校验的数据集合
							((DataBaseResult) obj).setDataResult(excelAppContext.getInitArray().get(str));
							((DataBaseResult) obj).setColdIndex(index);
							// 校验列
							iExcelCheck = (IExcelCheck) obj;
						} else if (obj instanceof LocalBaseResult) {
							((LocalBaseResult) obj).setDataResult(excelAppContext.getInitArray().get(str));
							((LocalBaseResult) obj).setColdIndex(index);
							iExcelCheck = (IExcelCheck) obj;
						} else {
							throw new Exception("检验数据配置设置错误！");
						}

					}

					iExcelCheck.excute(impErrorInfos, checkBean);
				}

			}
		}

		return impErrorInfos;

	}

	/**
	 * 数据导入
	 */
	public static boolean aresSabreService(ExcelAppContext excelAppContext) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
		Map<String, BatchImportInfoVO> map = excelAppContext.getMap();
		Map<String, DyadicArray<String>> dataMap = excelAppContext.getInitArray();
		Set<String> set = map.keySet();
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String str = iterator.next();
			DyadicArray<String> dyadicArray = dataMap.get(str);
			if (dyadicArray == null) {
				throw new DataExcelException("数据导入读取数据为NULL！");
			}
			try {
				connection = excelAppContext.getBaseDataConnection().getDataSource().getConnection();
				ps = connection.prepareStatement(map.get(str).getInsertSql());
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
		return true;
	}

	public static int[] aresTankService(DataCheckBean dataCheckBean, BatchImportInfoVO batchImportInfoVO) {

		String[] strCols = dataCheckBean.getColIds();
		String[] columns = batchImportInfoVO.getFileStr();
		int[] index = new int[strCols.length];
		for (int i = 0; i < strCols.length; i++) {
			String str = strCols[i];
			for (int j = 0; j < columns.length; j++) {
				if (str != null && str.length() > 0 && str.equals(columns[j].trim())) {
					index[i] = j;
					break;
				}
			}
		}
		return index;
	}
}
