package com.zhang.ivan.imp2exp.file.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ColumnFieldInfoVO;
import com.zhang.ivan.imp2exp.bean.FileldInfoVO;
import com.zhang.ivan.imp2exp.bean.TableFieldInfoVO;
import com.zhang.ivan.imp2exp.check.normal.DataCheckBean;
import com.zhang.ivan.imp2exp.check.normal.ExcelCheckContext;
import com.zhang.ivan.imp2exp.common.DataExcelException;
import com.zhang.ivan.imp2exp.context.ExcelAppContext;
import com.zhang.ivan.imp2exp.count.CountBean;
import com.zhang.ivan.imp2exp.product.ProcBean;
import com.zhang.ivan.imp2exp.util.regex.RegexChk;

public class XMLUtils {

	public static Document xml2Document(String xPath) throws Exception {
		SAXReader reader = new SAXReader();
		File file = new File(xPath);
		if (!file.exists()) {
			throw new DataExcelException("批量导入配置文件不存在或者路径错误！");
		} else {
			return reader.read(file);
		}
	}

	@SuppressWarnings({ "unchecked", "null" })
	public static Map<String, FileldInfoVO> doc2Fileld(Document document) throws Exception {
		Map<String, FileldInfoVO> map = new HashMap<String, FileldInfoVO>();
		List<Node> list = document.selectNodes("//root/column");
		if (list != null && list.size() > 0) {
			Node node = null;
			for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
				node = iterator.next();
				String columnId = node.valueOf("@colId");
				if (columnId == null || columnId.trim().equals("")) {
					throw new DataExcelException("配置文档内容出错，column 节点Id属性必须定义");
				}
				String fileType = node.valueOf("@type");
				String col = null;
				String calssName = null;
				int num = 0;
				if (fileType != null && fileType.equals("0")) {
					TableFieldInfoVO tableFieldInfoVO = new TableFieldInfoVO();
					col = node.valueOf("@col");
					// 校验正整数必须大于0
					if (col != null && RegexChk.checkNrs(col)) {
						num = Integer.valueOf(col);
					} else {
						throw new DataExcelException("配置文档出错，column节点type类型为实列时，必须定义col的属性为正整数");
					}
					tableFieldInfoVO.setColIndex(num);
					col = node.valueOf("@defaultValue");
					if (col != null && col.trim().length() > 0) {
						tableFieldInfoVO.setDefaultValue(col.trim());
					} else {
						tableFieldInfoVO.setDefaultValue(null);
					}
					map.put(columnId.trim(), tableFieldInfoVO);
				} else if (fileType == null || fileType.equals("") || fileType.equals("1")) {
					ColumnFieldInfoVO columnFieldInfoVO = new ColumnFieldInfoVO();
					calssName = node.valueOf("@className");
					if (calssName != null && calssName.trim().length() > 0) {
						columnFieldInfoVO.setClassName(calssName);
					} else {
						throw new DataExcelException("配置文档出错，column节点type类型为虚列时，必须定义className的属性");
					}
					map.put(columnId.trim(), columnFieldInfoVO);
				} else {
					throw new DataExcelException("column节点type属性定义错误：type默认为1，即表格实列，type为0即虚列");
				}

			}
			return map;
		} else {
			throw new DataExcelException("配置文档定义错误必须存在column 节点");
		}

	}

	@SuppressWarnings({ "unchecked", "null" })
	public static Map<String, List<DataCheckBean>> doc2Check(Document document) throws Exception {
		Map<String, List<DataCheckBean>> map = new HashMap<String, List<DataCheckBean>>();
		List<Node> list = document.selectNodes("//root/check");
		if (list != null && list.size() > 0) {
			Node node = null;
			for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
				List<DataCheckBean> listCheck = null;
				node = iterator.next();
				String checkId = node.valueOf("@tableId");
				if (checkId == null || checkId.trim().equals("")) {
					throw new DataExcelException("配置文档内容出错，check 节点tableId属性必须定义");
				}
				if (map.get(checkId) == null) {
					listCheck = new ArrayList<DataCheckBean>();
				} else {
					listCheck = map.get(checkId);
				}
				DataCheckBean dataCheckBean = new DataCheckBean();
				String type = node.valueOf("@type");
				if (type != null && type.trim().length() > 0) {
					if (type.trim().equals("single") || type.trim().equals("regex")
							|| type.trim().equals("database-single") || type.trim().equals("database-related")
							|| type.trim().equals("custom")) {
						dataCheckBean.setType(type.trim());
					} else {
						throw new DataExcelException(
								"配置文档内容出错，check节点type属性必须定义为single，regex，databae-single，database-related或者custom其中之一");
					}
					String cols = node.valueOf("@colIds");
					if (cols != null && cols.trim().length() > 0) {
						String[] qt = cols.trim().split(",");
						dataCheckBean.setColIds(qt);
					} else {
						throw new DataExcelException("配置文件内容错误，check节点colIds属性中内容错误！");
					}

					if (type.trim().equals("database-single") || type.trim().equals("database-related")) {
						String sqlText = node.valueOf("@sqlText");
						if (sqlText != null && sqlText.trim().length() > 0) {
							dataCheckBean.setSqlText(sqlText);
						} else {
							throw new DataExcelException("配置文件内容错误，check节点stype属性为验证数据库的时候必须存在sqlText属性！");
						}
					}
					if (type.trim().equals("regex")) {
						String regexText = node.valueOf("@regexText");
						if (regexText != null && regexText.trim().length() > 0) {
							dataCheckBean.setRegexText(regexText);
						} else {
							throw new DataExcelException("配置文件内容错误，check节点stype属性为验证正则表达式的时候必须存在regexText属性！");
						}
					}
					if (type.trim().equals("custom")) {
						String className = node.valueOf("@className");
						if (className != null && className.trim().length() > 0) {
							dataCheckBean.setClassName(className);
						} else {
							throw new DataExcelException("配置文件内容错误，check节点stype属性为自定义的时候必须存在className属性！");
						}
					}
					String desc = node.getText();
					if (desc != null && desc.trim().length() > 0) {
						dataCheckBean.setDesc(desc);
						;
					} else {
						throw new DataExcelException("配置文件内容错误，check节点必须有验证说明！");
					}
				} else {
					throw new DataExcelException("配置文档内容出错，check节点type属性必须定义");
				}

				listCheck.add(dataCheckBean);
				map.put(checkId.trim(), listCheck);
			}
			return map;
		} else {
			return map;
		}

	}

	@SuppressWarnings({ "unchecked", "null" })
	public static Map<String, CountBean> doc2Count(Document document) throws Exception {
		Map<String, CountBean> map = new HashMap<String, CountBean>();
		List<Node> list = document.selectNodes("//root/count");
		if (list != null && list.size() > 0) {
			Node node = null;
			for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
				node = iterator.next();
				String countId = node.valueOf("@tableId");
				if (countId == null || countId.trim().equals("")) {
					throw new DataExcelException("配置文档内容出错，count 节点tableId属性必须定义");
				}
				String className = node.valueOf("@className");
				CountBean countBean = new CountBean();
				if (className != null && className.trim().length() > 0) {
					countBean.setClassName(className);
				} else {
					throw new DataExcelException("配置文档内容出错，count节点className属性必须定义");
				}
				map.put(countId.trim(), countBean);
			}
			return map;
		} else {
			return map;
		}

	}

	@SuppressWarnings({ "unchecked", "null" })
	public static Map<String, ProcBean> doc2procdure(Document document) throws Exception {

		Map<String, ProcBean> map = new HashMap<String, ProcBean>();
		List<Node> list = document.selectNodes("//root/procdure");
		if (list != null && list.size() > 0) {
			Node node = null;
			for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
				node = iterator.next();
				String procId = node.valueOf("@tableId");
				if (procId == null || procId.trim().equals("")) {
					throw new DataExcelException("配置文档内容出错，procdure 节点tableId属性必须定义");
				}
				String procText = node.getText();
				ProcBean procBean = new ProcBean();
				if (procText != null && procText.trim().length() > 0) {
					procBean.setProcText(procText);
				} else {
					throw new DataExcelException("配置文档内容出错，procdure必须定义存储过程内容");
				}
				map.put(procId.trim(), procBean);
			}
			return map;
		} else {
			return map;
		}
	}

	@SuppressWarnings({ "unchecked", "null" })
	public static Map<String, BatchImportInfoVO> doc2Table(Document document) throws Exception {

		Map<String, BatchImportInfoVO> map = new HashMap<String, BatchImportInfoVO>();
		List<Node> list = document.selectNodes("//root/table");
		if (list != null && list.size() > 0) {
			Node node = null;
			for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
				node = iterator.next();
				String tableId = node.valueOf("@tableId");
				if (tableId == null || tableId.trim().equals("")) {
					throw new DataExcelException("配置文档内容出错，table 节点tableId属性必须定义");
				}

				String insertSql = node.getText();
				BatchImportInfoVO batchImportInfoVO = new BatchImportInfoVO();
				if (insertSql != null && insertSql.trim().length() > 0) {
					batchImportInfoVO.setInsertSql(insertSql);
				} else {
					throw new DataExcelException("配置文档内容出错，table必须定义插入语句内容");
				}
				String sheet = node.valueOf("@sheet");
				if (sheet != null && sheet.trim().length() > 0) {
					String[] qt = sheet.trim().split(",");
					int[] index = new int[qt.length];
					for (int i = 0; i < qt.length; i++) {
						if (RegexChk.checkNrs(qt[i])) {
							index[i] = Integer.valueOf(qt[i]);
						} else {
							throw new DataExcelException("配置文件内容错误，table节点sheet属性中内容错误！");
						}
					}
					batchImportInfoVO.setIndex(index);
				}

				String fileStr = node.valueOf("@fileStr");
				if (fileStr != null && fileStr.trim().length() > 0) {
					batchImportInfoVO.setFileStr(fileStr.trim().split(","));
				} else {
					throw new DataExcelException("配置文件内容错误，table节点fileStr属性为必填！");
				}
				map.put(tableId.trim(), batchImportInfoVO);
			}
			return map;
		} else {
			return map;
		}

	}

	public static ExcelAppContext doc2ExcelAppContext(Document document) throws Exception {
		ExcelAppContext excelAppContext = new ExcelAppContext();
		Map<String, BatchImportInfoVO> mapTable = doc2Table(document);
		Map<String, FileldInfoVO> mapFile = doc2Fileld(document);
		Map<String, ProcBean> procMap = new HashMap<String, ProcBean>();
		excelAppContext.setFileMap(mapFile);
		Set<String> set = mapTable.keySet();
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String object = iterator.next();
			BatchImportInfoVO batchImportInfoVO = mapTable.get(object);
			ProcBean procBean = procMap.get(object);
			batchImportInfoVO.setProcBean(procBean);
		}
		excelAppContext.setMap(mapTable);
		return excelAppContext;
	}

	public static ExcelCheckContext doc2ExcelCheckContext(Document document) throws Exception {
		ExcelCheckContext excelCheckContext = new ExcelCheckContext();
		Map<String, List<DataCheckBean>> mapCheck = doc2Check(document);
		excelCheckContext.setCheckMap(mapCheck);
		return excelCheckContext;
	}

}
