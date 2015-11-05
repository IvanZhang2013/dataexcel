
package com.zhang.ivan.imp2exp.bean;

/**
 * class desc:上传文件基类
 */
public class BaseUpload {
	
	private BatchImportInfoVO batchVO;

	private ImpExcelTemplate impExcelTemplate;

	public ImpExcelTemplate getImpExcelTemplate() {
		return impExcelTemplate;
	}

	public void setImpExcelTemplate(ImpExcelTemplate impExcelTemplate) {
		this.impExcelTemplate = impExcelTemplate;
	}

	public BatchImportInfoVO getBatchVO() {
		return batchVO;
	}

	public void setBatchVO(BatchImportInfoVO batchVO) {
		this.batchVO = batchVO;
	}
}
