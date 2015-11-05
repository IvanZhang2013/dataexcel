package com.zhang.ivan.imp2exp.check;

import java.util.ArrayList;
import java.util.List;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;

/**
 * 责任进行传递
 */

public class ToImpCheckBean extends AbstractExcelCheck implements IExcelCheck {

	public void init() {

	}

	/**
	 * 需要用于校验的对象或者配置文件
	 */
	public List<ImpErrorInfo> excute(DataCheckBean dataCheckBean) throws Exception {
		if (getiExcelCheck() != null) {
			if (errorinfo == null) {
				errorinfo = new ArrayList<ImpErrorInfo>();
			}
			List<ImpErrorInfo> list = getiExcelCheck().excute(dataCheckBean);
			if (list != null) {
				errorinfo.addAll(list);
			}
		}
		return errorinfo;
	}

}
