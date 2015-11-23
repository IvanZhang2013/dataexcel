package com.zhang.ivan.imp2exp.bean;

import java.util.Map;

import com.zhang.ivan.imp2exp.product.ProcBean;

/**
 * author: kin wong
 *
 * class desc:批量导入信息VO类
 */
public class BatchImportInfoVO {

	private String insertSql;
	private int[] index;
	private String[] fileStr;
	private ProcBean procBean;

	public ProcBean getProcBean() {
		return procBean;
	}

	public void setProcBean(ProcBean procBean) {
		this.procBean = procBean;
	}

	public int[] getIndex() {
		return index;
	}

	public void setIndex(int[] index) {
		this.index = index;
	}

	public String[] getFileStr() {
		return fileStr;
	}

	public void setFileStr(String[] fileStr) {
		this.fileStr = fileStr;
	}

	public String getInsertSql() {
		return insertSql;
	}

	public void setInsertSql(String insertSql) {
		this.insertSql = insertSql;
	}

}
