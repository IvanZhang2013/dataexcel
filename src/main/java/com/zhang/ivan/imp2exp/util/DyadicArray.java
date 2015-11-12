package com.zhang.ivan.imp2exp.util;

import java.util.Arrays;

public class DyadicArray<E> {

	private static final Object[][] EMPTY_ELEMENTDATA = {};
	private static final Object[][] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
	private static final int DEFAULT_CAPACITY = 10;
	transient Object[][] elementData;

	private int rowIndex;
	private int columnIndex;

	public DyadicArray(int rowSize, int columnSize) {
		if (columnSize > 0 && rowSize > 0) {
			this.elementData = new Object[rowSize][columnSize];
			this.rowIndex = rowSize;
			this.columnIndex = columnSize;
		} else if (rowSize == 0 || columnSize == 0) {
			this.elementData = EMPTY_ELEMENTDATA;
			this.rowIndex = 0;
			this.columnIndex = 0;
		} else {
			throw new IllegalArgumentException("Illegal Capacity: " + rowSize + ':' + columnSize);
		}
	}

	public DyadicArray() {
		this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
		this.rowIndex = 0;
		this.columnIndex = 0;
	}

	public int getRowSize() {
		return rowIndex;
	}

	public int getColumnSize() {
		return columnIndex;

	}

	private void ensureCapacityInternal(int minRowSize, int minColumnSize) {
		if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
			minRowSize = Math.max(DEFAULT_CAPACITY, minRowSize);
		}
		if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
			minColumnSize = Math.max(DEFAULT_CAPACITY, minColumnSize);
		}

		ensureExplicitCapacity(minRowSize, minColumnSize);
	}

	private void ensureExplicitCapacity(int minRowSize, int minColumnSize) {
		if (minRowSize - elementData.length > 0 && elementData.length == 0) {
			grow(minRowSize, minColumnSize);
		} else if (minRowSize - elementData.length > 0 || minColumnSize - elementData[0].length > 0) {
			grow(minRowSize, minColumnSize);
		}
	}

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	private void grow(int minRowSize, int minColumnSize) {
		int oldRowSize = elementData.length;
		int oldColumnSize = 0;
		if (elementData.length == 0) {
			oldColumnSize = DEFAULT_CAPACITY;
		} else {
			oldColumnSize = elementData[0].length;
		}
		int newRowSize = oldRowSize + (oldRowSize >> 1);
		int newColumnSize = oldColumnSize + (oldColumnSize >> 1);

		if (newRowSize - minRowSize < 0) {
			newRowSize = minRowSize;
		}

		if (newColumnSize - minColumnSize < 0) {
			newColumnSize = minColumnSize;
		}

		if (newRowSize - MAX_ARRAY_SIZE > 0) {
			newRowSize = hugeCapacity(minRowSize);
		}

		if (newColumnSize - MAX_ARRAY_SIZE > 0) {
			newColumnSize = hugeCapacity(minColumnSize);
		}

		elementData = Arrays.copyOf(elementData, newRowSize);

		int i = 0;

		for (i = 0; i < newRowSize; i++) {
			if (elementData[i] == null) {
				elementData[i] = new Object[newColumnSize];
			} else {
				elementData[i] = Arrays.copyOf(elementData[i], newColumnSize);
			}
		}

	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0)
			throw new OutOfMemoryError();
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	public boolean isEmpty() {
		return rowIndex == 0 || columnIndex == 0;
	}

	public E get(int rowSize, int columnSize) {
		rangeCheck(rowSize, columnSize);
		return elementData(rowSize, columnSize);
	}

	/**
	 * 检查数组是否越界
	 */
	private void rangeCheck(int rowSize, int columnSize) {
		if (rowIndex <= rowSize || columnIndex <= columnSize)
			throw new IndexOutOfBoundsException("数组越界==>ROWSIZE=" + rowSize + ":COLUMNSIZE=" + columnSize);
	}

	@SuppressWarnings("unchecked")
	E elementData(int rowSize, int columnSize) {
		if (elementData.length == 0) {
			return null;
		} else if (elementData[rowSize].length == 0) {
			return null;
		} else {
			return (E) elementData[rowSize][columnSize];
		}
	}

	public E set(int rowSize, int columnSize, E element) {
		rangeCheck(rowSize, columnSize);
		E oldValue = elementData(rowSize, columnSize);
		elementData[rowSize][columnSize] = element;
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	public E[] getRow(int rowIndexValue) throws Exception {
		if (elementData == null) {
			throw new Exception("数组为空！");
		} else {
			return (E[]) elementData[rowIndexValue];
		}
	}

	@SuppressWarnings({ "unchecked", "null" })
	public E[] getColumn(int columnIndexValue) throws Exception {
		E[] columnArray = null;

		if (elementData == null) {
			throw new Exception("数组为空！");
		}
		for (int i = 0; i < rowIndex; i++) {
			if (elementData[i] == null) {
				throw new Exception("选择列数组不完整！");
			} else {
				columnArray[i] = (E) elementData[i][columnIndexValue];
			}
		}
		return columnArray;

	}

	public void resetInit(int rowSize, int columnSize) throws Exception {
		if (rowSize > 0 && rowSize > 0) {
			ensureCapacityInternal(rowSize, columnSize);
			rowIndex = rowSize;
			columnIndex = columnSize;
		} else {
			throw new Exception("不能初始化负数或者0的二维数组！");
		}

	}

}
