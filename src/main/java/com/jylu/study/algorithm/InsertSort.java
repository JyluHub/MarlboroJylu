package com.jylu.study.algorithm;

/**
 * 插入排序
 * @author Lujiayun
 *
 */
public class InsertSort {

	public static void main(String[] args) {
		int[] intArray = { 32, 4, 56, 12, 432 };
		int temp = 0;
		for (int i = 1; i < intArray.length; i++) {
			temp = intArray[i];
			int j = 0;
			for (j = i; j > 0 && intArray[j - 1] >= temp; j--) {
				intArray[j] = intArray[j - 1];
			}
			intArray[j] = temp;
		}
		for (int i : intArray) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
