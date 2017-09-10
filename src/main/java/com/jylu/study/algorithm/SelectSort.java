package com.jylu.study.algorithm;

/**
 * @author Lujiayun
 *
 */
public class SelectSort {

	public static void main(String[] args) {
		int[] intArray = { 32, 4, 56, 12, 432 };
		int min = 0;
		int temp = 0;
		for (int i = 0; i < intArray.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < intArray.length; j++) {
				if (intArray[j] < intArray[min]) {
					min = j;
				}
			}
			temp = intArray[i];
			intArray[i] = intArray[min];
			intArray[min] = temp;
		}
		for (int i : intArray) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
