package com.zhang.ivan.imp2exp.toimp;

import java.util.ArrayList;
import java.util.List;

import com.zhang.ivan.imp2exp.bean.ColumnFieldInfoVO;
import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.bean.ibean.DataBaseInitValue;
import com.zhang.ivan.imp2exp.bean.ibean.IExcuteInitColumnValue;
import com.zhang.ivan.imp2exp.bean.ibean.LocalBaseInitValue;
import com.zhang.ivan.imp2exp.check.DataBaseResult;
import com.zhang.ivan.imp2exp.check.IExcelCheck;
import com.zhang.ivan.imp2exp.check.LocalBaseResult;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;
import com.zhang.ivan.imp2exp.check.normal.ExcelCheckContext;
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
		/**
		 * 数据校验逻辑 循环首先进行基础的校验 数据格式和数据行数进行校验 椒盐采用反射的机制 校验之后产生错误信息机型数据返回
		 * 数据校验使使用反射判断是那个类进行装填数据 然后调用
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

					if (obj instanceof DataBaseResult) {
						((DataBaseResult) obj).setBaseDataConnection(excelAppContext.getBaseDataConnection());
						((DataBaseResult) obj).setDataResult(excelAppContext.getDataArray());
						iExcelCheck = (IExcelCheck) obj;
					} else if (obj instanceof LocalBaseResult) {
						((LocalBaseResult) obj).setDataResult(excelAppContext.getDataArray());
						iExcelCheck = (IExcelCheck) obj;
					} else {
						throw new Exception("检验数据配置设置错误！");
					}

				}

				impErrorInfos.addAll(iExcelCheck.excute(impErrorInfos, checkBean, excelAppContext));
			}

		}
		return impErrorInfos;
	}

	/**
	 * 进行额外数据列的初始化
	 * 
	 * @throws Exception
	 */
	public static void minervaLanceService(ExcelCheckContext excelCheckContext, ExcelAppContext excelAppContext)
			throws Exception {
		/**
		 * 数据校验逻辑 循环首先进行基础的校验 数据格式和数据行数进行校验 椒盐采用反射的机制 校验之后产生错误信息机型数据返回
		 * 数据校验使用什么设计模式还不明白
		 */
		ColumnFieldInfoVO[] columnFieldInfoVOs = excelAppContext.getBatchImportInfoVO().getOtherfieldInfo();

		if (columnFieldInfoVOs == null) {
			return;
		}

		for (int i = 0; i < columnFieldInfoVOs.length; i++) {
			ColumnFieldInfoVO columnFieldInfoVO = columnFieldInfoVOs[i];

			if (columnFieldInfoVO == null) {
				throw new Exception("校验方法类为空无法进行校验");
			} else {
				Class<?> cl = Class.forName(columnFieldInfoVO.getClassName());
				Object obj = cl.newInstance();
				IExcuteInitColumnValue iExcuteInitColumnValue = null;
				if (obj instanceof IExcelCheck) {

					if (obj instanceof DataBaseResult) {
						((DataBaseInitValue) obj).setBaseDataConnection(excelAppContext.getBaseDataConnection());
						((DataBaseInitValue) obj).setDataResult(excelAppContext.getDataArray());
						iExcuteInitColumnValue = (IExcuteInitColumnValue) obj;
					} else if (obj instanceof LocalBaseResult) {
						((LocalBaseInitValue) obj).setDataResult(excelAppContext.getDataArray());
						iExcuteInitColumnValue = (IExcuteInitColumnValue) obj;
					} else {
						throw new Exception("检验数据配置设置错误！");
					}

				}
				excelAppContext.setDataArray(iExcuteInitColumnValue.excute());
			}

		}
	}

	/**
	 * 进行额外数据列的初始化
	 */
	public static void minervaShieldService(ExcelCheckContext excelCheckContext) {
		/**
		 * 数据校验逻辑 循环首先进行基础的校验 数据格式和数据行数进行校验 椒盐采用反射的机制 校验之后产生错误信息机型数据返回
		 * 数据校验使用什么设计模式还不明白
		 */

	}

}
