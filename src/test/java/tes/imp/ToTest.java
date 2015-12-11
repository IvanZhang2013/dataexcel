package tes.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ColumnFieldInfoVO;
import com.zhang.ivan.imp2exp.bean.ExcelConfig;
import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.bean.TableFieldInfoVO;
import com.zhang.ivan.imp2exp.check.bean.DataCheckBean;
import com.zhang.ivan.imp2exp.check.bean.ExcelCheckContext;
import com.zhang.ivan.imp2exp.check.bean.ExcelResult;
import com.zhang.ivan.imp2exp.check.oper.IExcelCheck;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.file.xml.XMLUtils;
import com.zhang.ivan.imp2exp.toimp.ToImpFactory;
import com.zhang.ivan.imp2exp.util.PoiExcelType;
import com.zhang.ivan.imp2exp.util.database.BaseDataConnection;

import tes.DataSourceBoneCp;
import tes.exp.Employee;

public class ToTest implements IExcelCheck {

	@SuppressWarnings("unused")
	private static BatchImportInfoVO generateSampleEmployeeData() throws ParseException {

		List<Employee> employees = new ArrayList<Employee>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
		employees.add(new Employee("Elsa", dateFormat.parse("1970-Jul-10"), 1500, 0.15));
		employees.add(new Employee("Oleg", dateFormat.parse("1973-Apr-30"), 2300, 0.25));
		employees.add(new Employee("Neil", dateFormat.parse("1975-Oct-05"), 2500, 0.00));
		employees.add(new Employee("Maria", dateFormat.parse("1978-Jan-07"), 1700, 0.15));
		employees.add(new Employee("John", dateFormat.parse("1969-May-30"), 2800, 0.20));
		return (BatchImportInfoVO) employees;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		long f = System.currentTimeMillis();
		Imp exp = new Imp();
		exp.setTempNm("workbook.xls");
		exp.setTempPath("F:\\GitHub\\Eclipse_GitHub\\dataexcel\\workbook.xls");
		exp.setUploadFilePath("F:\\GitHub\\Eclipse_GitHub\\dataexcel\\workbook.xls");
		ExcelConfig config  = new ExcelConfig();
		config.setxPatch("F:\\GitHub\\Eclipse_GitHub\\dataexcel\\src\\main\\resource\\xml\\text.xml");
		ExcelAppContext appContext=XMLUtils.doc2ExcelAppContext(XMLUtils.xml2Document(config.getxPatch()));
		BaseDataConnection baseDataConnection  = new BaseDataConnection();
		baseDataConnection.setDataSource(new DataSourceBoneCp().getDataSource());
		appContext.setBaseDataConnection(baseDataConnection);
		ExcelCheckContext e = XMLUtils.doc2ExcelCheckContext(XMLUtils.xml2Document(config.getxPatch()));

		@SuppressWarnings("unused")
		ExcelResult d = ToImpFactory.getWorkbook(PoiExcelType.EXCEL_XLS, appContext).excute(exp, e);
		long s = System.currentTimeMillis();
		System.out.println(s-f);
	}

	@Override
	public List<ImpErrorInfo> excute(List<ImpErrorInfo> list, DataCheckBean dataCheckBean) throws Exception {
		return null;
	}
}
