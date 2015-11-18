package com.zhang.ivan.imp2exp.util.database;

import javax.sql.DataSource;

public class BaseDataConnection {
	
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
