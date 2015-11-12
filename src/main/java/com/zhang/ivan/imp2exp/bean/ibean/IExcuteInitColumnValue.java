package com.zhang.ivan.imp2exp.bean.ibean;

import com.zhang.ivan.imp2exp.BaseDataConnection;
import com.zhang.ivan.imp2exp.util.DyadicArray;

/**
 * 用于数据的生成
 * */
public interface IExcuteInitColumnValue {
	
	public void  excute(DyadicArray<String> dataResult ,BaseDataConnection baseDataConnection);
	
}
