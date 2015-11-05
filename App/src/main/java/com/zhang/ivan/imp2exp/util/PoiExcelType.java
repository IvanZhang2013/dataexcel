package com.zhang.ivan.imp2exp.util;

public enum PoiExcelType {
	EXCEL_XLS("xls", "com.zhang.ivan.imp2exp.toimp.XLS2Data"), EXCEL_XLSX("xlsx",
			"com.zhang.ivan.imp2exp.toimp.XLSX2Data");
	private String excelType;
	private String excelClassName;

	PoiExcelType(String excelType, String excelClassName) {
		this.setExcelClassName(excelClassName);
		this.setExcelType(excelType);
	}

	public static PoiExcelType getExcelType(String eType) {
		if (eType.equals(EXCEL_XLS.getExcelType())) {
			return EXCEL_XLS;
		} else if (eType.equals(EXCEL_XLS.getExcelType())) {
			return EXCEL_XLSX;
		} else {
			return null;
		}
	}

	public String loadClassName() {
		return this.excelClassName;
	}

	public String getExcelClassName() {
		return excelClassName;
	}

	public void setExcelClassName(String excelClassName) {
		this.excelClassName = excelClassName;
	}

	public String getExcelType() {
		return excelType;
	}

	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}

}
