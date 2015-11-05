package com.zhang.ivan.imp2exp.bean;

/**
 * 模版内容抽象类
 */
public abstract class ImpExcelTemplate {

	/**
	 * 模版名称--下载的文件名称
	 */
	private String tempNm;
	/**
	 * 模版存放的路径
	 */
	private String tempPath;
	/**
	 * 模版对应的表名称
	 */
	private String tableNm;
	/**
	 * 上传文件路径
	 * */
	private String uploadFilePath;

	public String getTempNm() {
		return tempNm;
	}

	public void setTempNm(String tempNm) {
		this.tempNm = tempNm;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public String getTableNm() {
		return tableNm;
	}

	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

}
