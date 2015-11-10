package tes;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPDataSource;

public class DataSourceBoneCp {
	public static DataSource dataSource;

	static {
		BoneCPDataSource ds = new BoneCPDataSource();

		ds.setDriverClass("oracle.jdbc.driver.OracleDriver");
		ds.setJdbcUrl(
				"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.4.129)(PORT = 1521)))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = newhrdev)))");
		ds.setUsername("newhr");
		ds.setPassword("newhr");
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
