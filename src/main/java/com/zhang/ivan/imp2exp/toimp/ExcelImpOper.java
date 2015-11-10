package com.zhang.ivan.imp2exp.toimp;

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

		ColumnFieldInfoVO cf;
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

		stringBufferstart.append(" ) VALUES (").append(stringBufferend.toString()).append(")");

		return stringBufferstart.toString();

	}

	public boolean excute(ExcelCheckContext excelCheckContext,ExcelAppContext excelAppContext ,String sql) throws Exception{
		//默认值的计算  需要在那里进行初始化
		 DyadicArray<String> dyadicArray  =  excelCheckContext.getDyadicArray();
		List<DataCheckBean>  list  = excelCheckContext.getCheckbeanlist();
		for (int i = 0; i < list.size(); i++) {
			DataCheckBean  checkBean =list.get(i);
			Class<?> cl = Class.forName(checkBean.getCheckClass());
			IExcelCheck excelCheck = null;
			if(cl.newInstance()instanceof  IExcelCheck ){
				IExcelCheck toImp = (IExcelCheck) cl.newInstance();
			}else{
				throw new DataExcelException("公示转化错误！");
			}
		List<ImpErrorInfo> list1 =	excelCheck.excute(checkBean);
		}
		return false;
	}

}
