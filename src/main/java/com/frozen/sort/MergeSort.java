package com.frozen.sort;

import java.util.Arrays;

/**
 * <program> datastructure </program>
 * <description> 归并排序 </description>
 *
 * @author : lw
 * @date : 2019-09-14 21:09
 **/
public class MergeSort {
    public static void sort(int[] arr) {
        int[] tempArr = new int[arr.length];
        spilt(arr, 0, arr.length-1, tempArr);
    }
    /**
     * <description> 拆分方法 </description>
     * @param arr : 要拆分的数组
     * @param left : 拆分起始索引(包含)
     * @param right : 拆分结束索引(包含)
     * @param tempArr : 临时数组
     * @return : void
     * @author : lw
     * @date : 2019-09-14 21:50
     */
    private static void spilt(int[] arr, int left, int right, int[] tempArr) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        spilt(arr, left, mid, tempArr);
        spilt(arr, mid + 1, right, tempArr);
        merge(arr, left, mid, right, tempArr);
    }

    /**
     * <description> 合并方法 </description>
     * @param arr : 原数组
     * @param left : 左边有序数组的起始位置(包含)
     * @param mid : 左边有序数组的结束位置(包含)
     * @param right : 右边有序数组的结束位置(包含)
     * @param tempArr : 临时数组
     * @return : void
     * @author : lw
     * @date : 2019-09-14 21:54
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] tempArr) {
        int tempIndex = left;
        //填充临时数组
        for (int i = left, j = mid+1; i <= mid || j <= right; ) {
            if (i > mid) {
                tempArr[tempIndex++] = arr[j++];
                continue;
            }
            if (j > right) {
                tempArr[tempIndex++] = arr[i++];
                continue;
            }
            if (arr[i] <= arr[j]) {
                tempArr[tempIndex++] = arr[i++];
            } else {
                tempArr[tempIndex++] = arr[j++];
            }
        }
        //回填至原数组
        System.arraycopy(tempArr,left,arr,left, right+1-left);
    }
}
