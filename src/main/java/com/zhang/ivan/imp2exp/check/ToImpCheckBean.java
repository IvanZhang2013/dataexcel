package com.zhang.ivan.imp2exp.check;

import java.util.List;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;

/**
 * 责任进行传递
 */

public class ToImpCheckBean extends AbstractExcelCheck implements IExcelCheck {
	/**
	 * 需要用于校验的对象或者配置文件
	 * */
	public List<ImpErrorInfo> excute() {
		if (getiExcelCheck() != null) {
			getiExcelCheck().excute();
		}
		return null;
	}

}
