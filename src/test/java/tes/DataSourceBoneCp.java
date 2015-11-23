package tes;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPDataSource;

public class DataSourceBoneCp {
	public static DataSource dataSource;

	static {
		BoneCPDataSource ds = new BoneCPDataSource();

		ds.setDriverClass("oracle.jdbc.driver.OracleDriver");
		ds.setJdbcUrl(
				"jdbc:oracle:thin:@//192.168.4.129:1521/newhrdev");
		ds.setUsername("newhr");
		ds.setPassword("newhr");
		dataSource = ds;
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void setDataSource(DataSource dataSource) {
		DataSourceBoneCp.dataSource = dataSource;
	}
	
	public static void main(String[] args) throws SQLException {
		DataSourceBoneCp cp = new DataSourceBoneCp();
		System.out.println(cp.getDataSource().getConnection());
	}

}
