package com.zhang.ivan.imp2exp.file.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.zhang.ivan.imp2exp.bean.BatchImportInfoVO;
import com.zhang.ivan.imp2exp.bean.ColumnFieldInfoVO;
import com.zhang.ivan.imp2exp.bean.FileldInfoVO;
import com.zhang.ivan.imp2exp.bean.TableFieldInfoVO;
import com.zhang.ivan.imp2exp.common.DataExcelException;
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

	public static Map<String, BatchImportInfoVO> doc2Table(Document document) throws Exception {

		Element root = document.getRootElement();
		System.out.println(document.selectNodes("//root/column"));
		List<Node> list = root.selectNodes("//table");

		for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
			Element object = (Element) iterator.next();
			System.out.println(object.getName() + ">>>>" + object.attribute("status"));
		}

		for (Iterator it = root.elementIterator(); it.hasNext();) {
			Element element = (Element) it.next();
			System.out.println(element.getName());
		}

		return null;

	}

	@SuppressWarnings({ "unchecked", "null" })
	public static Map<String, FileldInfoVO> doc2Produrce(Document document) throws Exception {
		Map<String, FileldInfoVO> map = new HashMap<String, FileldInfoVO>();
		List<Node> list = document.selectNodes("//root/column");
		if (list != null && list.size() > 0) {
			Node node = null;
			for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
				String columnId = node.valueOf("@id");
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
					map.put(columnId, tableFieldInfoVO);
				} else if (fileType == null || fileType.equals("") || fileType.equals("1")) {
					ColumnFieldInfoVO columnFieldInfoVO = new ColumnFieldInfoVO();
					calssName = node.valueOf("@className");
					if (calssName != null && calssName.trim().length() > 0) {
						columnFieldInfoVO.setClassName(calssName);
					} else {
						throw new DataExcelException("配置文档出错，column节点type类型为虚列时，必须定义className的属性");
					}
					map.put(columnId, columnFieldInfoVO);
				} else {
					throw new DataExcelException("column节点type属性定义错误：type默认为1，即表格实列，type为0即虚列");
				}

			}
			return map;
		} else {
			throw new DataExcelException("配置文档定义错误必须存在column 节点");
		}

	}

	public static Map<String, BatchImportInfoVO> doc2File(Document document) throws Exception {

		Element root = document.getRootElement();
		System.out.println(document.selectNodes("//root/column"));
		List<Node> list = root.selectNodes("//table");

		for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
			Element object = (Element) iterator.next();
			System.out.println(object.getName() + ">>>>" + object.attribute("status"));
		}

		for (Iterator it = root.elementIterator(); it.hasNext();) {
			Element element = (Element) it.next();
			System.out.println(element.getName());
		}

		return null;

	}

	public static Map<String, BatchImportInfoVO> doc2Check(Document document) throws Exception {

		Element root = document.getRootElement();
		System.out.println(document.selectNodes("//root/column"));
		List<Node> list = root.selectNodes("//table");

		for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
			Element object = (Element) iterator.next();
			System.out.println(object.getName() + ">>>>" + object.attribute("status"));
		}

		for (Iterator it = root.elementIterator(); it.hasNext();) {
			Element element = (Element) it.next();
			System.out.println(element.getName());
		}

		return null;

	}

}
