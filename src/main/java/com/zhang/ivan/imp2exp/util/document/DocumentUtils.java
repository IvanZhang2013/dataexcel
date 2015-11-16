package com.zhang.ivan.imp2exp.util.document;

import java.io.File;

import com.zhang.ivan.imp2exp.common.DataExcelException;

public class DocumentUtils {

	public static boolean checkDocSize(String docPatch, long size) throws DataExcelException {
		File file = new File(docPatch);
		if (file.exists() && file.isFile()) {
			// 对文档内容进行校验如过超过规定大小则不允许进行导入
			if (size == 0) {
				// 当设定为0的时候不进行文档的大小校验内容
				return true;
			} else if (size > file.length()) {
				throw new DataExcelException("文档大小超过规定大小");
			} else {
				return true;
			}
		} else {
			throw new DataExcelException("文档不存在！");
		}
	}
}
