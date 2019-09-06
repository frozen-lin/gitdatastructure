package com.frozen.sort;

/**
 * @program: datastructure
 * @description: 希尔排序
 * @author: lw
 * @create: 2019-09-06 16:51
 **/
public class ShellSort {
    /**
     * @param arr
     * @return : void
     * @description :  希尔排序
     * @author : lw
     * @date : 2019-09-06 22:24
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int length = arr.length;
        int step = length >> 1;
        int insertValue;
        int insertIndex;
        //int count = 0;
        while (step >= 1) {
            for (int i = step; i < length; i++) {
                insertValue = arr[i];
                insertIndex = i;
                for (int j = i - step; j >= 0 && arr[j] > insertValue; j = j - step) {
                    arr[insertIndex] = arr[j];
                    insertIndex = j;
                }
                if (insertIndex != i) {
                    arr[insertIndex] = insertValue;
                }
            }
            //count++;
            //System.out.println("第"+count+"次排序"+ Arrays.toString(arr));
            step = step >> 1;
        }
    }
}
