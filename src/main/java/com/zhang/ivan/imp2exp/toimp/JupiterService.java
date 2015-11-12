package com.zhang.ivan.imp2exp.toimp;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.zhang.ivan.imp2exp.bean.ColumnFieldInfoVO;
import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.bean.TableFieldInfoVO;
import com.zhang.ivan.imp2exp.bean.ibean.IExcuteInitColumnValue;
import com.zhang.ivan.imp2exp.check.IExcelCheck;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;
import com.zhang.ivan.imp2exp.check.normal.ExcelCheckContext;
import com.zhang.ivan.imp2exp.common.DataExcelException;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.product.ProcUtils;
import com.zhang.ivan.imp2exp.util.DyadicArray;
import com.zhang.ivan.imp2exp.util.ExcelReader;

/**
 * 总控程序用来进行取得workbook后的后续操作；
 */
public class JupiterService {
	public static void jupiterLanceService(Workbook workbook, ExcelAppContext excelAppContext,
			ExcelCheckContext excelCheckContext) throws Exception {

		ExcelReader excelReader = new ExcelReader(workbook);
		DyadicArray<String> dyadicArray = excelReader.readExcel();
		ColumnFieldInfoVO[] columnFieldInfoVOs = excelAppContext.getBatchImportInfoVO().getOtherfieldInfo();
		TableFieldInfoVO[] tableFieldInfoVOs = excelAppContext.getBatchImportInfoVO().getFieldInfo();

		int newColumnSize = tableFieldInfoVOs.length + columnFieldInfoVOs.length;
		dyadicArray.resetInit(dyadicArray.getRowSize(), newColumnSize);
		excelCheckContext.setDyadicArray(dyadicArray);
		excelAppContext.setDataArray(dyadicArray);
		/**
		 * 上面已经将读取到的数据进行过扩充<br/>
		 * 调用数据格式化接口 进行数据的格式化
		 */
		for (int i = 0; i < columnFieldInfoVOs.length; i++) {
			Class<?> cl = Class.forName(columnFieldInfoVOs[i].getClassName());
			Object obj = cl.newInstance();

			IExcuteInitColumnValue columnValue = null;
			if (obj instanceof IExcuteInitColumnValue) {
				columnValue = (IExcuteInitColumnValue) obj;
			} else {
				throw new Exception("数据格式化方法失败！");
			}
			excelAppContext.setDataArray(columnValue.excute(dyadicArray, excelAppContext.getBaseDataConnection()));
		}

		/**
		 * 进行数据的校验方法
		 */
		List<ImpErrorInfo> impErrorInfos = new ArrayList<ImpErrorInfo>();
		List<DataCheckBean> list = excelCheckContext.getCheckbeanlist();
		for (int i = 0; i < list.size(); i++) {
			DataCheckBean checkBean = list.get(i);

			if (checkBean == null) {
				throw new Exception("校验方法类为空无法进行校验");
			} else {
				Class<?> cl = Class.forName(checkBean.getCheckClass());
				Object obj = cl.newInstance();

				IExcelCheck iExcelCheck = null;
				if (obj instanceof IExcelCheck) {
					throw new Exception("检验数据配置设置错误！");
				} else {
					iExcelCheck = (IExcelCheck) obj;
				}
				impErrorInfos.addAll(iExcelCheck.excute(checkBean));
			}

		}

		/**
		 * 主要进行业务逻辑的算法处理
		 * 
		 */
		for (int i = 0; i < tableFieldInfoVOs.length; i++) {
			Class<?> cl = Class.forName(tableFieldInfoVOs[i].getClassName());
			Object obj = cl.newInstance();
			IExcuteInitColumnValue columnValue = null;
			if (obj instanceof IExcuteInitColumnValue) {
				columnValue = (IExcuteInitColumnValue) obj;
			} else {
				throw new Exception("数据格式化方法失败！");
			}
			excelAppContext.setDataArray(columnValue.excute(dyadicArray, excelAppContext.getBaseDataConnection()));
		}

		String sql;
		try {
			sql = ExcelImpOper.initSql(excelAppContext);
			ExcelImpOper.toDataBase(sql, excelCheckContext, excelAppContext);
			if (excelAppContext.getProcBean() != null) {
				// 需要执行存储过程
				ProcUtils.operProc(excelAppContext.getProcBean());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataExcelException("调用插入数据表JanusService服务出错！");
		}
	}

}
