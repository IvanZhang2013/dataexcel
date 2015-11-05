package com.zhang.ivan.imp2exp.util;

public class Test {
	public static void main(String[] args) throws Exception {
		DyadicArray<Object> dyadicArray = new DyadicArray<Object>(7, 8);
		dyadicArray.set(5, 6, "SSSS");
		System.out.println(dyadicArray.get(5, 6));
		System.out.println(dyadicArray.getColumn(6));
//		System.out.println(dyadicArray.getRow(5));
		
		//Object [] obj = {"s",null,4,5,6,7,8,9,11};
		//System.arraycopy(obj, 1, obj, 2 ,
	//			1);
	//	System.out.println(obj.toString());
	//	System.out.println(dyadicArray.set(5, 6, "SSSS"));
		// 循环数组对象
	//	System.out.println(dyadicArray.getRowSize());
	//	System.out.println(dyadicArray.getColumnSize());
	//	for (int i = 0; i < dyadicArray.getRowSize(); i++) {
	//		for (int j = 0; j < dyadicArray.getColumnSize(); j++) {
	//			
	//			System.out.println(i + "X" + j + "----->" + dyadicArray.get(i, j));
	//			
	//		}
	//	}  
	}

}
