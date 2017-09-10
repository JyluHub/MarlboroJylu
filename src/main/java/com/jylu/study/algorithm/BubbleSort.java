package com.jylu.study.algorithm;

/**
 * 冒泡排序
 * @author Lujiayun
 *
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] intArray = { 32, 4, 56, 12, 432 };
		int temp = 0;
		for (int i = 0; i < intArray.length - 1; i++) {
			for (int j = 0; j < intArray.length - 1 - i; j++) {
				if (intArray[j] > intArray[j + 1]) {
					temp = intArray[j];
					intArray[j] = intArray[j + 1];
					intArray[j + 1] = temp;
				}
			}
		}
		for (int i : intArray) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
