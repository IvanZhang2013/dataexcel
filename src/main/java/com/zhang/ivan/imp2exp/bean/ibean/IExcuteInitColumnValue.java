package com.zhang.ivan.imp2exp.bean.ibean;

import com.zhang.ivan.imp2exp.util.DyadicArray;

/**
 * 用于数据的生成
 */
public interface IExcuteInitColumnValue {

	/**
	 * 根据传入的数值进行数据的初始化初始化完成后返回传入的二维数组
	 */
	public DyadicArray<String> excute();

}
