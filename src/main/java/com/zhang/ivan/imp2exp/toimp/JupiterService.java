package com.zhang.ivan.imp2exp.toimp;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.bean.ExcelCheckContext;
import com.zhang.ivan.imp2exp.check.bean.ExcelResult;
import com.zhang.ivan.imp2exp.common.DataExcelException;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.util.DyadicArray;
import com.zhang.ivan.imp2exp.util.ExcelReader;

/**
 * 总控程序用来进行取得workbook后的后续操作；
 */
public class JupiterService {
	public static ExcelResult jupiterLanceService(Workbook workbook, ExcelAppContext excelAppContext,
			ExcelCheckContext excelCheckContext) throws Exception {
		ExcelResult excelResult = new ExcelResult();
		ExcelReader excelReader = new ExcelReader(workbook);
		Map<Integer, DyadicArray<String>> dyadicArrayMap = excelReader.readExcel(excelAppContext);
		excelCheckContext.setDyadicArray(dyadicArrayMap);
		/**
		 * 数据生成之后对数据进行整理 就是进行数据表块的运算
		 */
		// 校验之后就是进行数据块的生成
		try {
			MinervaService.minervaLanceService(excelCheckContext, excelAppContext);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataExcelException("数据块生成错误");
		}

		// 校验服务
		List<ImpErrorInfo> list = AresService.aresPretextService(excelCheckContext, excelAppContext);

		if (list != null && list.size() > 0) {
			excelResult.setStatus(false);
			excelResult.setListError(list);
			excelResult.setIndex(1);
			return excelResult;
		}
		// 数据块成后进行数据的录入 数据导入
		boolean status = AresService.aresSabreService(excelAppContext);

		if (!status) {
			excelResult.setStatus(false);
			excelResult.setListError(list);
			excelResult.setIndex(2);
			return excelResult;
		}

		// 数据块生成后进行数据逻辑的业务运算 //业务逻辑还没处理
		List<ImpErrorInfo> list2 = MinervaService.minervaMaceService(excelCheckContext, excelAppContext);

		excelResult.setStatus(true);
		excelResult.setListError(list2);
		excelResult.setIndex(3);
		return excelResult;

	}

}
