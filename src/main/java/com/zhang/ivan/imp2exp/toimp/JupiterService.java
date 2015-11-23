package com.zhang.ivan.imp2exp.toimp;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.normal.ExcelCheckContext;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.util.DyadicArray;
import com.zhang.ivan.imp2exp.util.ExcelReader;

/**
 * 总控程序用来进行取得workbook后的后续操作；
 */
public class JupiterService {
	public static void jupiterLanceService(Workbook workbook, ExcelAppContext excelAppContext,
			ExcelCheckContext excelCheckContext) throws Exception {

		ExcelReader excelReader = new ExcelReader(workbook);
		Map<Integer, DyadicArray<String>> dyadicArrayMap = excelReader.readExcel(excelAppContext);
		excelCheckContext.setDyadicArray(dyadicArrayMap);
		/**
		 * 数据生成之后对数据进行整理 就是进行数据表块的运算
		 */
		// 校验之后就是进行数据块的生成
		MinervaService.minervaLanceService(excelCheckContext, excelAppContext);
		
		// 校验服务因为为5中类型所以需要分开方法  校验方法还没做
		AresService.aresPretextService(excelCheckContext, excelAppContext);

		// 数据块成后进行数据的录入  数据导入
		AresService.aresSabreService(excelAppContext);
		
		
		
		// 数据块生成后进行数据逻辑的业务运算 //业务逻辑还没处理
		MinervaService.minervaMaceService(excelCheckContext, excelAppContext);
	}

}
