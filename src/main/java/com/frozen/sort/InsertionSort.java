package com.frozen.sort;

import java.util.Arrays;

/**
 * @program: datastructure
 * @description: 插入排序
 * @author: lw
 * @create: 2019-09-05 19:30
 **/
public class InsertionSort {
    /**
     * @param arr 要排序的数组
     * @return : void
     * @description :插入排序 从小到大排序
     * @author : lw
     * @date : 2019-09-05 19:31
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int length = arr.length;
        int insertIndex;
        int insertValue;
        for (int i = 1; i < length; i++) {
            insertIndex = i;
            insertValue = arr[i];
            for (int j = i - 1; j >= 0 && arr[j] > insertValue; j--) {
                arr[insertIndex] = arr[j];
                insertIndex = j;
            }
            if (insertIndex != i) {
                arr[insertIndex] = insertValue;
            }
            //System.out.println("第" + i + "次排序后" + Arrays.toString(arr));
        }
    }
}
