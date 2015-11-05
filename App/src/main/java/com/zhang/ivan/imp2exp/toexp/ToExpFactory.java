package com.zhang.ivan.imp2exp.toexp;

import com.zhang.ivan.imp2exp.util.ExcelType;
/**
 * 导出Excel工厂
 * */
public class ToExpFactory {
	private String execlType;

	public String getExeclType() {
		return execlType;
	}

	public void setExeclType(String execlType) {
		this.execlType = execlType;
	}

	public static ToExp getToExp(ExcelType em) throws Exception {
		Class<?> cl = Class.forName("com.zhang.ivan.imp2exp.toexp.Data2Excel");
		ToExp toexp = (ToExp) cl.newInstance();
		toexp.setExcelType(em);
		return toexp;
	}

}
