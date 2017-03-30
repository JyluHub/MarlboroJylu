package com.jylu.utils;

/**
 * ClassName: SortUtil <br/>
 * Description: 排序工具类 <br/>
 * Date: 17-3-23 下午8:56 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class SortUtil {

    private SortUtil(){}

    /**
     * 冒泡排序
     * @param args
     */
    public static void buubleSort(int[] args) {
        int out, in;
        for (out = args.length - 1; out > 1; out--) {
            for (in = 0; in < out; in++) {
                if (args[in] > args[out]) {
                    swap(args, in, out);
                }
            }
        }
    }

    /**
     * 插入排序
     * @param args
     */
    public static void insertSort(int[] args) {
        int out, in;
        for (out = 1; out < args.length; out++) {
            int tmp = args[out];
            in = out;
            while (in > 0 && args[in - 1] >= tmp) {
                args[in] = args[in - 1];
                --in;
            }
            args[in] = tmp;
        }
    }

    /**
     * 选择排序
     * @param args
     */
    public static void selectSort(int[] args) {
        int min, out, in;
        for (out = 0; out < args.length - 1; out++) {
            min = out;
            for (in = out + 1; in < args.length; in++) {
                if (args[in] < args[min]) {
                    min = in;
                }
            }
            swap(args, out, min);
        }
    }

    /**
     *  交换数组中两个元素的位置
     * @param args
     * @param i
     * @param j
     */
    private static void swap(int[] args, int i, int j) {
        int tmp = args[i];
        args[i] = args[j];
        args[j] = tmp;
    }
}