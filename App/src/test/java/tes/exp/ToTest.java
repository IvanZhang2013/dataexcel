package tes.exp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.zhang.ivan.imp2exp.toexp.ToExpFactory;
import com.zhang.ivan.imp2exp.util.ExcelType;

public class ToTest {
	
    private static List<Employee> generateSampleEmployeeData() throws ParseException {
        List<Employee> employees = new ArrayList<Employee>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        employees.add( new Employee("Elsa", dateFormat.parse("1970-Jul-10"), 1500, 0.15) );
        employees.add( new Employee("Oleg", dateFormat.parse("1973-Apr-30"), 2300, 0.25) );
        employees.add( new Employee("Neil", dateFormat.parse("1975-Oct-05"), 2500, 0.00) );
        employees.add( new Employee("Maria", dateFormat.parse("1978-Jan-07"), 1700, 0.15) );
        employees.add( new Employee("John", dateFormat.parse("1969-May-30"), 2800, 0.20) );
        return employees;
    }

	public static void main(String[] args) throws Exception {
		
		Exp  exp = new Exp();
		exp.setObjectName("employees");
		exp.setTempNm("nested_command_template.xls");
		exp.setTempPath("F:/Program Files (x86)/workspaces-ds/App/target/test-classes/excel/");
		exp.setEntityPath("F:/Program Files (x86)/workspaces-ds/App/target/");
		
		ToExpFactory.getToExp(ExcelType.EXCEL_XLS).excute(exp, ToTest.generateSampleEmployeeData());
		ToExpFactory.getToExp(ExcelType.EXCEL_XLSX).excute(exp, ToTest.generateSampleEmployeeData());
	}
}
