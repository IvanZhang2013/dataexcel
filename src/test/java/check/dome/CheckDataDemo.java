package check.dome;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.bean.DataCheckBean;
import com.zhang.ivan.imp2exp.check.oper.DataBaseResult;
import com.zhang.ivan.imp2exp.check.oper.IExcelCheck;
import com.zhang.ivan.imp2exp.common.DataExcelException;

/**
 * 对于需要连接数据库的请求
 * 普通的不需要连接数据库的话只需要继承
 * extends LocalBaseResult implements IExcelCheck
 * 在进行校验的话尽量不要再当前进行校验可以考虑使用存储过程校验   这个可能需要在后续中进行使用
 */
public class CheckDataDemo extends DataBaseResult implements IExcelCheck {

	@Override
	/**
	 * 传入的参数是 list 里面存放的是所有错误信息 dataCkecBean 校验信息 excelAppContext 里面存放的是应用文
	 */
	public List<ImpErrorInfo> excute(List<ImpErrorInfo> list, DataCheckBean dataCheckBean) throws Exception {
		int[] params = getColdIndex();
		int rowsize = getDataResult().getRowSize();

		if (params == null) {
			throw new DataExcelException("校验公式colIds定义错误！");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getBaseDataConnection().getDataSource().getConnection();
			ps = connection.prepareStatement(dataCheckBean.getSqlText());

			for (int i = 0; i < rowsize; i++) {
				ps.clearParameters();
				Object[] strRow = getDataResult().getRow(i);
				for (int j = 0; j < params.length; j++) {
					ps.setString(j + 1, strRow[params[j]].toString());
				}
				rs = ps.executeQuery();
				if (!(rs.next())) {
					ImpErrorInfo impErrorInfo = new ImpErrorInfo();
					impErrorInfo.setRowIndex(i);
					impErrorInfo.setErrorInfo(dataCheckBean.getDesc());
					list.add(impErrorInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return list;
	}

}
