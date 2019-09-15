package com.frozen.sort;

/**
 * <program> datastructure </program>
 * <description> 快排 </description>
 *
 * @author : lw
 * @date : 2019-09-14 19:19
 **/
public class QuickSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        quickSort(arr, left, right);
    }
    /**
     * <description>  </description>
     * @param : [arr 要排序的数组, left 排序数组的起始位置, right排序数组的结束位置]
     * @return : void
     * @author : lw
     * @date : 2019-09-14 21:11
     */
    private static void quickSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }
        //取中间的数为比较数
        int midValue = arr[(left + right) / 2];
        //左边查找开始索引
        int i = left;
        //右边查找开始索引
        int j = right;
        while ( i < j ) {
            //从左边找到大于等于中间数的数
            while (arr[i] < midValue) {
                i++;
            }
            //从右边找到小于等于中间数的数
            while (arr[j] > midValue) {
                j--;
            }
            if (i != j) {
                arr[i] = arr[i] ^ arr[j];
                arr[j] = arr[i] ^ arr[j];
                arr[i] = arr[i] ^ arr[j];
                if (arr[i]==arr[j]){
                    j--;
                }
            }
        }
        quickSort(arr, left, i);
        quickSort(arr, i+1, right);
    }

}
