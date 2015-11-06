package com.zhang.ivan.imp2exp.check;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;
import com.zhang.ivan.imp2exp.common.DataExcelException;

/**
 * 对于单列的正则进行校验，int【】队列中存储队列位置 参数list中存储对应int中的正则表达式
 */
public class RegxDataCheck extends LocalExcelCheck implements IExcelCheck {

	public void init() {

	}

	public List<ImpErrorInfo> excute(DataCheckBean dataCheckBean) throws Exception {
		int columsize = getDataResult().getColumnSize();

		int[] params = dataCheckBean.getCheckColumn();

		if (params == null) {
			throw new DataExcelException("校验公式定义错误！");
		}
		for (int i = 0; i < params.length; i++) {
			if (params[i] >= columsize) {
				throw new DataExcelException("校验公式定义错误！");
			}
		}

		int j = 0;
		String[] column = null;
		for (int i = 0; i < params.length; i++) {
			j = params[i];
			column = getDataResult().getColumn(j);
			for (int q = 0; q < column.length; q++) {

				if (!this.regx((String) dataCheckBean.getParamvalue().get(j), column[q])) {

					if (errorinfo == null) {
						errorinfo = new ArrayList<ImpErrorInfo>();
					}
					ImpErrorInfo impErrorInfo = new ImpErrorInfo();
					impErrorInfo.setRowIndex(q);
					impErrorInfo.setErrorInfo(dataCheckBean.getEdsc());
					errorinfo.add(impErrorInfo);

				}
			}
		}
		return errorinfo;
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
