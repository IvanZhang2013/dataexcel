package com.zhang.ivan.imp2exp.check;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.bean.DataCheckBean;
import com.zhang.ivan.imp2exp.check.oper.IExcelCheck;
import com.zhang.ivan.imp2exp.check.oper.LocalBaseResult;
import com.zhang.ivan.imp2exp.common.DataExcelException;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;

/**
 * 本方法进行判断数据在本地的是否重复性 判断内容包括为null的情况
 */
public class SingleDataCheck extends LocalBaseResult implements IExcelCheck {

	@Override
	/**
	 * 对空的重复性不进行判断
	 * 
	 */
	public List<ImpErrorInfo> excute(List<ImpErrorInfo> list, DataCheckBean dataCheckBean) throws Exception {
		int[] params = getColdIndex();
		int rowsize = getDataResult().getRowSize();

		if (params == null) {
			throw new DataExcelException("校验公式colids定义错误！");
		}
		Object[] rows = null;
		Set<String> set = new HashSet<String>();
		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < rowsize; i++) {
			rows = getDataResult().getRow(i);
			for (int j = 0; j < params.length; j++) {
				if (rows[j] == null) {
					stringBuffer.append("|");
				} else {
					stringBuffer.append(rows[j].toString()).append("|");
				}

			}
			if (!set.add(stringBuffer.toString())) {
				if (list == null) {
					list = new ArrayList<ImpErrorInfo>();
				}
				ImpErrorInfo impErrorInfo = new ImpErrorInfo();
				impErrorInfo.setRowIndex(i);
				impErrorInfo.setErrorInfo(dataCheckBean.getDesc());
				list.add(impErrorInfo);
			}

		}

		return list;
	}

}
