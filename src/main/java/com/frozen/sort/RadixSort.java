package com.frozen.sort;

/**
 * <program> datastructure </program>
 * <description> 基数排序 </description>
 *
 * @author : lw
 * @date : 2019-09-15 09:22
 **/
public class RadixSort {
    /**
     * <description> 基数排序 </description>
     * @param arr  : 要排序的数组
     * @return : void
     * @author : lw
     * @date : 2019-09-15 9:55
     */
    public static void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }
        int length = arr.length;
        //找到数组中最大的数
        int max = arr[0];
        for (int i = 1; i < length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int bucketLength = 10;
        //最大数字的位数
        int digitsNumber = (max + "").length();
        //额外建立10个与原数组一样大的数组辅助排序
        int[][] bucket = new int[bucketLength][length];
        //建立1个数组记录10个桶塞入数据的个数
        int[] bucketElementCount = new int[bucketLength];
        //放入桶的索引
        int index;
        //放入原数组的索引
        int arrIndex;
        for (int count = 0, divisor = 1; count < digitsNumber; count++, divisor *= bucketLength) {
            for (int i = 0; i < length; i++) {
                //根据位数的位置 找到该数应放入桶的索引
                index = (arr[i] / divisor) % bucketLength;
                bucket[index][bucketElementCount[index]++] = arr[i];
            }
            arrIndex = 0;
            for (int i = 0; i < bucketLength; i++) {
                for (int j = 0; j < bucketElementCount[i]; j++) {
                    arr[arrIndex++] = bucket[i][j];
                }
                //处理完成后将桶中元素个数置为0
                bucketElementCount[i] = 0;
            }
        }
    }
}
