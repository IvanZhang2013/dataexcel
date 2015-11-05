package com.zhang.ivan.imp2exp.bean.ibean;

import java.util.List;

import com.zhang.ivan.imp2exp.BaseDataConnection;

public class SqlExcelValue extends AbstracExcelValue implements IExcuteValue {

	private List<Integer> intlist;
	private List<String> otherList;
	private String sql;
	private BaseDataConnection baseDataConnection;

	public String excute() {
		
		return null;
	}

	public List<Integer> getIntlist() {
		return intlist;
	}

	public void setIntlist(List<Integer> intlist) {
		this.intlist = intlist;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public BaseDataConnection getBaseDataConnection() {
		return baseDataConnection;
	}

	public void setBaseDataConnection(BaseDataConnection baseDataConnection) {
		this.baseDataConnection = baseDataConnection;
	}

	public List<String> getOtherList() {
		return otherList;
	}

	public void setOtherList(List<String> otherList) {
		this.otherList = otherList;
	}

}
