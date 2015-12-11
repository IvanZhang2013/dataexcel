package com.zhang.ivan.imp2exp.toimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ColumnFieldInfoVO;
import com.zhang.ivan.imp2exp.bean.FileldInfoVO;
import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.bean.TableFieldInfoVO;
import com.zhang.ivan.imp2exp.check.bean.ExcelCheckContext;
import com.zhang.ivan.imp2exp.col.math.AbstractDataMath;
import com.zhang.ivan.imp2exp.common.DataExcelException;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.util.DyadicArray;

/**
 * 规则检查程序进行规则的检查
 */
public class MinervaService {

	/**
	 * 进行数据检查则总控制类
	 * 
	 * @throws Exception
	 */
	public static List<ImpErrorInfo> minervaMaceService(ExcelCheckContext excelCheckContext,
			ExcelAppContext excelAppContext) throws Exception {
		Map<String, List<String>> paramMap = excelAppContext.getParamStr();
		Set<String> paramset = paramMap.keySet();
		List<ImpErrorInfo> impErrorInfos = new ArrayList<ImpErrorInfo>();
		Connection connection = null;
		CallableStatement callableStatement = null;
		for (Iterator<String> iterator = paramset.iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			try {
				connection = excelAppContext.getBaseDataConnection().getDataSource().getConnection();
				callableStatement = connection
						.prepareCall(excelAppContext.getMap().get("str").getProcBean().getProcText());
				List<String> list = paramMap.get(str);
				for (int i = 0; i < list.size() - 2; i++) {
					callableStatement.setString(i + 1, list.get(i));
				}
				callableStatement.registerOutParameter(list.size() - 1, Types.VARCHAR);
				callableStatement.registerOutParameter(list.size(), Types.VARCHAR);
				callableStatement.executeUpdate();

				String status = callableStatement.getString(list.size() - 1);
				String error = callableStatement.getString(list.size());
				if (status.trim().equals("0")) {
					throw new DataExcelException("存储过程执行失败！");
				} else {
					ImpErrorInfo impErrorInfo = new ImpErrorInfo();
					impErrorInfo.setErrorInfo(error);
					impErrorInfos.add(impErrorInfo);
				}
			} catch (Exception e) {
				throw new DataExcelException("存储过程执行失败");
			} finally {
				if (callableStatement != null) {
					callableStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			}
		}

		return impErrorInfos;

	}

	/**
	 * 进行数据块的数据生成
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static void minervaLanceService(ExcelCheckContext excelCheckContext, ExcelAppContext excelAppContext)
			throws Exception {

		Map<String, BatchImportInfoVO> initMap = excelAppContext.getMap();
		Set<String> set = initMap.keySet();
		Map<String, DyadicArray<String>> datamap = new HashMap<String, DyadicArray<String>>();
		BatchImportInfoVO batchImportInfoVO = null;
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String str = iterator.next();
			batchImportInfoVO = initMap.get(str);
			int[] sheetIndex = batchImportInfoVO.getIndex();
			int[] colIndex = minervaWheelService(batchImportInfoVO, excelAppContext);
			DyadicArray<String> dyadicArrayData = new DyadicArray<String>();
			DyadicArray<String> dyadicArray = null;
			for (int i = 0; i < sheetIndex.length; i++) {
				dyadicArray = excelCheckContext.getDyadicArray().get(sheetIndex[i]);
				int xpath = dyadicArrayData.getRowSize();
				dyadicArrayData.resetInit(dyadicArrayData.getRowSize() + dyadicArray.getRowSize(), colIndex.length);
				for (int j = 0; j < colIndex.length; j++) {
					int s = colIndex[j];
					AbstractDataMath abstractDataMath = null;
					if (s == 0) {
						abstractDataMath = minervaShieldService(excelAppContext, batchImportInfoVO.getFileStr()[j]);
					}
					for (int j2 = 0; j2 < dyadicArray.getRowSize(); j2++) {
						if (s > 0) {
							dyadicArrayData.set(xpath + j2, j, dyadicArray.get(j2, s - 1));
						} else if (s == 0) {
							dyadicArrayData.set(xpath + j2, j, abstractDataMath.math(dyadicArray.getRow(j2)));
						}
					}
				}
			}

			datamap.put(str, dyadicArrayData);

		}

		excelAppContext.setInitArray(datamap);

	}

	/**
	 * 进行反射出进行计算的抽象类
	 * 
	 */
	public static AbstractDataMath minervaShieldService(ExcelAppContext excelAppContext, String colName) throws Exception {
		AbstractDataMath abstractDataMath = null;
		FileldInfoVO fileldInfoVO = excelAppContext.getFileMap().get(colName);
		if (fileldInfoVO instanceof ColumnFieldInfoVO) {
			ColumnFieldInfoVO columnFieldInfoVO = (ColumnFieldInfoVO) fileldInfoVO;
			String className = columnFieldInfoVO.getClassName();
			Class<?> cl = Class.forName(className);
			Object obj = cl.newInstance();
			if (obj instanceof AbstractDataMath) {
				abstractDataMath = (AbstractDataMath) obj;
				abstractDataMath.setBaseDataConnection(excelAppContext.getBaseDataConnection());
			} else {
				throw new Exception("公示定义类的类型错误！");
			}
		} else {
			throw new Exception("类型定义错误！");
		}
		return abstractDataMath;
	}

	/**
	 * 数据块的取数列从1开始0为需要进行计算的列
	 * 
	 * 先取块 在计算 先计算再取块
	 */
	public static int[] minervaWheelService(BatchImportInfoVO batchImportInfoVO, ExcelAppContext excelAppContext)
			throws Exception {

		Map<String, FileldInfoVO> filemap = excelAppContext.getFileMap();
		String[] strArray = batchImportInfoVO.getFileStr();
		int[] fileIndex = new int[strArray.length];
		FileldInfoVO fileldInfoVO = null;
		for (int i = 0; i < strArray.length; i++) {
			fileldInfoVO = filemap.get(strArray[i]);
			if (fileldInfoVO == null) {
				throw new DataExcelException("配置文件出错，列不匹配！");
			}
			if (fileldInfoVO instanceof TableFieldInfoVO) {
				fileIndex[i] = ((TableFieldInfoVO) fileldInfoVO).getColIndex();
			} else if (fileldInfoVO instanceof ColumnFieldInfoVO) {
				fileIndex[i] = 0;
			} else {
				throw new DataExcelException("配置文件出错，列定义类型不匹配！");
			}
		}
		return fileIndex;
	}

}
