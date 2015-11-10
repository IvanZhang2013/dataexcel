package tes;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPDataSource;

public class DataSourceBoneCp {
	public static DataSource dataSource;

	static {
		BoneCPDataSource ds = new BoneCPDataSource();

		ds.setDriverClass("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://192.168.4.123:3306/cheng");
		ds.setUsername("root");
		ds.setPassword("");
		ds.setAcquireIncrement(1);
		ds.setAcquireRetryDelay(10000);
		ds.setIdleConnectionTestPeriod(100);
		ds.setMinConnectionsPerPartition(2);
		ds.setMaxConnectionsPerPartition(20);
		ds.setPartitionCount(2);
		dataSource = ds;
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void setDataSource(DataSource dataSource) {
		DataSourceBoneCp.dataSource = dataSource;
	}

}
