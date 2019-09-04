package com.frozen.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @program: datastructure
 * @description: 冒泡排序
 * @author: lw
 * @create: 2019-09-04 13:57
 **/
public class BubbleSort {
    /**
     * @param arr 要排序的数组
     * @return : void
     * @description :  从小到大排序
     * @author : lw
     * @date : 2019-09-04 14:33
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int length = arr.length - 1;
        //第二轮循环中遍历的次数
        int num = arr.length - 1;
        //数组最后交换的索引,用于优化,之后未交换的数组索引已经有序
        int swapIndex = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < num; j++) {
                if (arr[j] > arr[j + 1]) {
                    //交换
                    arr[j] = arr[j]^arr[j+1];
                    arr[j+1] = arr[j]^arr[j+1];
                    arr[j] = arr[j]^arr[j+1];
                    //将最后交换的索引置为j
                    swapIndex = j;
                }
            }
            //System.out.println("第"+(i+1)+"次排序"+ Arrays.toString(arr));
            if (swapIndex == 0) {
                break;
            }
            num = swapIndex;
            swapIndex = 0;
        }
    }
}
