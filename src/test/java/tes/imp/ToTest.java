package tes.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.zhang.ivan.imp2exp.BaseDataConnection;
import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ColumnFieldInfoVO;
import com.zhang.ivan.imp2exp.bean.ExcelConfig;
import com.zhang.ivan.imp2exp.bean.ImpErrorInfo;
import com.zhang.ivan.imp2exp.bean.TableFieldInfoVO;
import com.zhang.ivan.imp2exp.check.IExcelCheck;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;
import com.zhang.ivan.imp2exp.check.normal.ExcelCheckContext;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.toimp.ToImpFactory;
import com.zhang.ivan.imp2exp.util.PoiExcelType;

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

	public static void main(String[] args) throws Exception {

		Imp exp = new Imp();
		exp.setTempNm("workbook.xls");
		exp.setTempPath("F:\\GitHub\\Eclipse_GitHub\\dataexcel\\workbook.xls");
		exp.setUploadFilePath("F:\\GitHub\\Eclipse_GitHub\\dataexcel\\workbook.xls");
		ExcelAppContext appContext = new ExcelAppContext();
		BatchImportInfoVO batchImportInfoVO = new BatchImportInfoVO();

		TableFieldInfoVO tableFieldInfoVO1 = new TableFieldInfoVO();

		tableFieldInfoVO1.setDefaultValue("000");
		tableFieldInfoVO1.setFieldsDesc("测试内容");
		tableFieldInfoVO1.setFieldsName("NAMESPACE");

		TableFieldInfoVO tableFieldInfoVO2 = new TableFieldInfoVO();

		tableFieldInfoVO2.setDefaultValue("000");
		tableFieldInfoVO2.setFieldsDesc("测试内容");
		tableFieldInfoVO2.setFieldsName("NAMEPASS");

		TableFieldInfoVO tableFieldInfoVO3 = new TableFieldInfoVO();

		tableFieldInfoVO3.setDefaultValue("000");
		tableFieldInfoVO3.setFieldsDesc("测试内容");
		tableFieldInfoVO3.setFieldsName("SPACE");

		TableFieldInfoVO tableFieldInfoVO4 = new TableFieldInfoVO();

		tableFieldInfoVO4.setDefaultValue("000");
		tableFieldInfoVO4.setFieldsDesc("测试内容");
		tableFieldInfoVO4.setFieldsName("PASS");

		TableFieldInfoVO[] tableFieldInfoVOs = new TableFieldInfoVO[4];

		tableFieldInfoVOs[0] = tableFieldInfoVO1;
		tableFieldInfoVOs[1] = tableFieldInfoVO2;
		tableFieldInfoVOs[2] = tableFieldInfoVO3;
		tableFieldInfoVOs[3] = tableFieldInfoVO4;

		batchImportInfoVO.setFieldInfo(tableFieldInfoVOs);
		ColumnFieldInfoVO[] ps = new ColumnFieldInfoVO[1];

		ColumnFieldInfoVO columnFieldInfoVO = new ColumnFieldInfoVO();
		columnFieldInfoVO.setClassName("test");
		columnFieldInfoVO.setColumnName("test");
		columnFieldInfoVO.setFieldsDesc("数据结构类");
		ps[0] = columnFieldInfoVO;
		batchImportInfoVO.setOtherfieldInfo(ps);
		batchImportInfoVO.setTabledesc("TEST测试");
		batchImportInfoVO.setTableName("IVAN_EXCEL");

		appContext.setBatchImportInfoVO(batchImportInfoVO);

		ExcelConfig excelConfig = new ExcelConfig();
		excelConfig.setAll(true);
		excelConfig.setIsStartSheet(1);
		excelConfig.setOneSheet(false);
		excelConfig.setRow(10000);
		excelConfig.setSizeKB(5000);

		appContext.setExcelConfig(excelConfig);

		BaseDataConnection baseDataConnection = new BaseDataConnection();
		baseDataConnection.setDataSource(DataSourceBoneCp.getDataSource());
		appContext.setBaseDataConnection(baseDataConnection);

		ExcelCheckContext e = new ExcelCheckContext();

		DataCheckBean dataCheckBean = new DataCheckBean();

		dataCheckBean.setCheckClass("com.zhang.ivan.imp2exp.check.SingleDataCheck");
		int[] s = new int[2];
		s[0] = 1;
		s[1] = 2;
		dataCheckBean.setCheckColumn(s);
		dataCheckBean.setEdsc("输入错误");
		dataCheckBean.setParamvalue(null);

		List list = new ArrayList<>();
		list.add(dataCheckBean);
		e.setCheckbeanlist(list);

		ToImpFactory.getWorkbook(PoiExcelType.EXCEL_XLS, appContext).excute(exp, e);
	}

	@Override
	public void init() {

	}

	@Override
	public List<ImpErrorInfo> excute(DataCheckBean dataCheckBean) throws Exception {
		return null;
	}
}
