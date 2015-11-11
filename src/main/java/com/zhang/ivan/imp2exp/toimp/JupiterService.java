package com.zhang.ivan.imp2exp.toimp;

import org.apache.poi.ss.usermodel.Workbook;

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
		excelCheckContext.setDyadicArray(dyadicArray);
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
