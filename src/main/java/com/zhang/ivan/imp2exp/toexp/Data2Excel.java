package com.zhang.ivan.imp2exp.toexp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.jxls.common.Context;
import org.jxls.common.JxlsException;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhang.ivan.imp2exp.bean.ExpExcelTemplate;
import com.zhang.ivan.imp2exp.util.ExcelType;

/**
 * 生成xlsx文件的工具类
 */
public class Data2Excel extends AbstractData2Excel implements ToExp {

	public static Logger logger = LoggerFactory.getLogger(Data2Excel.class);

	/**
	 * 根据excel模版和数据生成xlsx文件
	 * 
	 * @throws Exception
	 */
	public void excute(ExpExcelTemplate exp, Object obj) throws Exception {
		boolean status = false;

		status = exp.checkBean();
		if (!status) {
			throw new JxlsException("导出文件类验证出现错误");
		}

		if (logger.isInfoEnabled()) {
			logger.info("检查文件模版是否存在！");
		}
		File tempfile = new File(exp.getTempPath());
		status = tempfile.exists();
		if (!status) {
			if (logger.isDebugEnabled()) {
				logger.debug("导出文件模版不存在！==>" + exp.getTempPath());
			}
			throw new FileNotFoundException("导出文件模版不存在！");
		}
		File entityfile = new File(exp.getEntityPath());
		if (logger.isInfoEnabled()) {
			logger.info("检查生成文件路径是否存在！");
		}
		status = entityfile.exists();
		if (!status) {
			if (logger.isDebugEnabled()) {
				logger.debug("生成文件路径不存在！==>" + exp.getEntityPath());
			}
			throw new FileNotFoundException("生成文件路径不存在！");
		}

		if (logger.isInfoEnabled()) {
			logger.info("根据模版生成对应文件！");
		}

		String timeString = Long.toString(System.currentTimeMillis());

		FileInputStream fin = new FileInputStream(exp.getTempPath() + exp.getTempNm());
		FileOutputStream fout = new FileOutputStream(
				exp.getEntityPath() + File.separator + exp.getTempNm() + timeString + this.getExcelType());
		Context context = new Context();
		context.putVar(exp.getObjectName(), obj);
		JxlsHelper.getInstance().processTemplate(fin, fout, context);
		exp.setExcelPatch(exp.getEntityPath() + File.separator + exp.getTempNm() + timeString + this.getExcelType());
	}

	@Override
	public void setExcelType(ExcelType em) throws Exception {
		this.setExcelType(em.toString());
	}
}
