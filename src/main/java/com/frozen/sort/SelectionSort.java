package com.frozen.sort;

/**
 * @program: datastructure
 * @description: 选择排序
 * @author: lw
 * @create: 2019-09-05 18:52
 **/
public class SelectionSort {
    /**
     * @param arr 要排序的数组
     * @return : void
     * @description :  选择排序 从小到大排序
     * @author : lw
     * @date : 2019-09-05 19:06
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int cycleCount = arr.length - 1;
        int min;
        int minIndex;
        for (int i = 0; i < cycleCount; i++) {
            min = arr[i];
            minIndex = i;
            for (int j = i + 1; j < cycleCount + 1; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //System.out.println("第"+(i+1)+"次排序"+ Arrays.toString(arr));
        }

    }
}
