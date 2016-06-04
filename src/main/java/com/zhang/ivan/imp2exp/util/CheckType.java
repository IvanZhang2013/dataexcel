package com.zhang.ivan.imp2exp.util;

public enum CheckType {
	SINGLE_CHECK("single", "com.zhang.ivan.imp2exp.check.SingleDataCheck"), REGEX_CHECK("regex",
			"com.zhang.ivan.imp2exp.check.RegxDataCheck"), DATABASE_SINGLE_CHECK("database-single",
					"com.zhang.ivan.imp2exp.check.SingleDataBaeCheck"), DATABASE_RELATED_CHECK("database-related",
							"com.zhang.ivan.imp2exp.check.RelatedDataCheck");

	private String checkType;
	private String checkClassName;

	CheckType(String checkType, String checkClassName) {
		this.setCheckClassName(checkClassName);
		this.setCheckType(checkType);
	}

	public static CheckType getExcelType(String eType) {
		if (eType.equals(SINGLE_CHECK.getCheckType())) {
			return SINGLE_CHECK;
		} else if (eType.equals(REGEX_CHECK.getCheckType())) {
			return REGEX_CHECK;
		} else if (eType.equals(DATABASE_SINGLE_CHECK.getCheckType())) {
			return DATABASE_SINGLE_CHECK;
		} else if (eType.equals(DATABASE_RELATED_CHECK.getCheckType())) {
			return DATABASE_RELATED_CHECK;
		} else {
			return null;
		}
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getCheckClassName() {
		return checkClassName;
	}

	public void setCheckClassName(String checkClassName) {
		this.checkClassName = checkClassName;
	}

}
