package com.zhang.ivan.imp2exp.context;

import java.util.Map;

import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ExcelConfig;
import com.zhang.ivan.imp2exp.bean.FileldInfoVO;
import com.zhang.ivan.imp2exp.product.ProcBean;
import com.zhang.ivan.imp2exp.util.DyadicArray;
import com.zhang.ivan.imp2exp.util.database.BaseDataConnection;

/**
 * 导入文件上下文， 需要存储倒入文件的配置， 需要将数据源传递进去 进行数据的提交修改和数据的数据库校验
 */
public class ExcelAppContext {
	
	/**
	 * 数据库连接池对象
	 * */
	protected BaseDataConnection baseDataConnection;
	/**
	 * 表对象
	 * */
	protected Map<String, BatchImportInfoVO> map;
	/**
	 * 列对象
	 * */
	protected Map<String, FileldInfoVO> fileMap;
	/**
	 * 配置对象
	 * */
	protected ExcelConfig excelConfig;
	/**
	 * 数据对象
	 * */
	protected Map<String, DyadicArray<String>> initArray;

	public Map<String, BatchImportInfoVO> getMap() {
		return map;
	}

	public void setMap(Map<String, BatchImportInfoVO> map) {
		this.map = map;
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

	public Map<String, DyadicArray<String>> getInitArray() {
		return initArray;
	}

	public void setInitArray(Map<String, DyadicArray<String>> initArray) {
		this.initArray = initArray;
	}

	public Map<String, FileldInfoVO> getFileMap() {
		return fileMap;
	}

	public void setFileMap(Map<String, FileldInfoVO> fileMap) {
		this.fileMap = fileMap;
	}

}
