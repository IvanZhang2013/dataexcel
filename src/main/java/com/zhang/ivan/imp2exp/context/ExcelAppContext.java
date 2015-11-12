package com.zhang.ivan.imp2exp.context;

import com.zhang.ivan.imp2exp.BaseDataConnection;
import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ExcelConfig;
import com.zhang.ivan.imp2exp.product.ProcBean;
import com.zhang.ivan.imp2exp.util.DyadicArray;

/**
 * 
 * 导入文件上下文， 需要存储倒入文件的配置， 需要将数据源传递进去 进行数据的提交修改和数据的数据库校验
 */
public class ExcelAppContext {

	protected BaseDataConnection baseDataConnection;
	protected BatchImportInfoVO batchImportInfoVO;
	protected ExcelConfig excelConfig;
	protected DyadicArray<String> dataArray;
	protected ProcBean procBean;

	public BatchImportInfoVO getBatchImportInfoVO() {
		return batchImportInfoVO;
	}

	public void setBatchImportInfoVO(BatchImportInfoVO batchImportInfoVO) {
		this.batchImportInfoVO = batchImportInfoVO;
	}

	public ExcelConfig getExcelConfig() {
		return excelConfig;
	}

	public void setExcelConfig(ExcelConfig excelConfig) {
		this.excelConfig = excelConfig;
	}

	public BaseDataConnection getBaseDataConnection() {
		return baseDataConnection;
	}

	public void setBaseDataConnection(BaseDataConnection baseDataConnection) {
		this.baseDataConnection = baseDataConnection;
	}

	public DyadicArray<String> getDataArray() {
		return dataArray;
	}

	public void setDataArray(DyadicArray<String> dataArray) {
		this.dataArray = dataArray;
	}

	public ProcBean getProcBean() {
		return procBean;
	}

	public void setProcBean(ProcBean procBean) {
		this.procBean = procBean;
	}

}
