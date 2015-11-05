package com.zhang.ivan.imp2exp.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模版内容抽象类
 */
public abstract class ExpExcelTemplate {

	public static Logger logger = LoggerFactory.getLogger(ExpExcelTemplate.class);

	/**
	 * 模版名称--下载的文件名称
	 */
	private String tempNm;
	/**
	 * 模版存放的路径
	 */
	private String tempPath;
	/**
	 * 文件生成路径
	 */
	private String entityPath;

	/**
	 * 传入参数模版映射
	 */
	private String objectName;
	/**
	 * 生成文件的名称包括路径
	 */
	private String excelPatch;

	public ExpExcelTemplate() {

	};

	public String getExcelPatch() {
		return excelPatch;
	}

	public void setExcelPatch(String excelPatch) {
		this.excelPatch = excelPatch;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getEntityPath() {
		return entityPath;
	}

	public void setEntityPath(String entityPath) {
		this.entityPath = entityPath;
	}

	public String getTempNm() {
		return tempNm;
	}

	public void setTempNm(String tempNm) {
		this.tempNm = tempNm;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public boolean checkBean() {
		int t = 1;

		if (this.getEntityPath() == null) {
			t = t * 0;
			logger.debug("文件声称路径为NULL");
		}
		if (this.getTempNm() == null) {
			t = t * 0;
			logger.debug("模版文件名称为NULL");
		}
		if (this.getTempPath() == null) {
			t = t * 0;
			logger.debug("模版路径为NULL");
		}
		if (this.getObjectName() == null) {
			t = t * 0;
			logger.debug("传入参数映射为NULL");
		}

		if (t == 1) {
			logger.info("导出文件类验证通过");
			return true;
		} else {
			logger.info("导出文件类验证不通过");
			return false;
		}
	}
}
