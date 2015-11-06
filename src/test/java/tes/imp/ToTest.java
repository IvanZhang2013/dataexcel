package tes.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.check.IExcelCheck;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;
import com.zhang.ivan.imp2exp.check.normal.ExcelCheckContext;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.toimp.ToImpFactory;
import com.zhang.ivan.imp2exp.util.PoiExcelType;

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

	public static void main(String[] args) throws Exception {

		Imp exp = new Imp();
		exp.setTempNm("nested_command_template.xls");
		exp.setTempPath("F:/Program Files (x86)/workspaces-ds/App/target/test-classes/excel/");
		exp.setUploadFilePath("F:\\Program Files (x86)\\workspaces-ds\\App\\src\\test\\java\\excel\\workbookout.xls");

		ExcelAppContext appContext = new ExcelAppContext();
		IExcelCheck excelCheck = new ToTest();

		ExcelCheckContext e = new ExcelCheckContext();
		ToImpFactory.getWorkbook(PoiExcelType.EXCEL_XLS, appContext).excute(exp, e);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ImpErrorInfo> excute(DataCheckBean dataCheckBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
