package com.zhang.ivan.imp2exp.check;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.bean.DataCheckBean;
import com.zhang.ivan.imp2exp.check.oper.IExcelCheck;
import com.zhang.ivan.imp2exp.check.oper.LocalBaseResult;
import com.zhang.ivan.imp2exp.common.DataExcelException;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;

/**
 * 对于单列的正则进行校验，int【】队列中存储队列位置 参数list中存储对应int中的正则表达式
 */
public class RegxDataCheck extends LocalBaseResult implements IExcelCheck {

	public List<ImpErrorInfo> excute(List<ImpErrorInfo> list, DataCheckBean dataCheckBean) throws Exception {
		int[] index = getColdIndex();
		String regexStr = dataCheckBean.getRegexText();
		if (!(index != null && index.length > 0)) {
			throw new DataExcelException("校验公式colId定义错误！");
		}

		if (!(regexStr != null && regexStr.trim().length() > 0)) {
			throw new DataExcelException("校验公式regexText定义错误！");
		}

		int j = 0;
		Object[] column = null;
		for (int i = 0; i < index.length; i++) {
			j = index[i];
			column = getDataResult().getColumn(j);
			for (int q = 0; q < column.length; q++) {

				if (!this.regx(regexStr, column[q].toString())) {

					if (list == null) {
						list = new ArrayList<ImpErrorInfo>();
					}
					ImpErrorInfo impErrorInfo = new ImpErrorInfo();
					impErrorInfo.setRowIndex(q);
					impErrorInfo.setErrorInfo(dataCheckBean.getDesc());
					list.add(impErrorInfo);
				}
			}
		}

		return list;
	}

	private boolean regx(String regx, String value) throws DataExcelException {
		if (value == null) {
			return false;
		}
		if (regx == null) {
			throw new DataExcelException("正则表达式定义错误");
		}
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

}
