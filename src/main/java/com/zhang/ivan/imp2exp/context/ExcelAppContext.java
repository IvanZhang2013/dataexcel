package com.zhang.ivan.imp2exp.context;

import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ExcelConfig;

/**
 * 
 * 导入文件上下文，需要存储倒入文件的配置，
 */
public class ExcelAppContext implements IExcelAppContext {
	protected BatchImportInfoVO batchImportInfoVO;
	protected ExcelConfig excelConfig;

	public BatchImportInfoVO getBatchImportInfoVO() {
		return batchImportInfoVO;
	}

	public void setBatchImportInfoVO(BatchImportInfoVO batchImportInfoVO) {
		this.batchImportInfoVO = batchImportInfoVO;
	}

	/**
	 * 进行导入信息文件的初始化功能，
	 */
	@SuppressWarnings("unused")
	private void init() {
		initExcelAppContext();
	}

	public ExcelAppContext initExcelAppContext() {
		return null;
	}

	public ExcelConfig getExcelConfig() {
		return excelConfig;
	}

	public void setExcelConfig(ExcelConfig excelConfig) {
		this.excelConfig = excelConfig;
	}

}
