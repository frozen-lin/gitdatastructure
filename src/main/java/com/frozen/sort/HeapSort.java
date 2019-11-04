package com.frozen.sort;


/**
 * <program> datastructure </program>
 * <description> 堆排序 </description>
 *
 * @author : lw
 * @date : 2019-11-04 20:49
 **/
public class HeapSort {
    /**
     * <description> 堆排序,从小到大 </description>
     *
     * @param arr :  要排序数组
     * @return : void
     * @author : lw
     * @date : 2019-11-04 20:50
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        /*
         * 首次调整堆,从最后一个非叶子节点开始
         */
        int length = arr.length;
        for (int treeNodeIndex = length / 2 - 1; treeNodeIndex >= 0; treeNodeIndex--) {
            adjustBigHeap(arr, treeNodeIndex, length);
        }

        for (int i = 1; i < length; i++) {
            /*
             * 将每次最大的数调整至数组末尾-i
             */
            swap(arr, 0, length - i);
            adjustBigHeap(arr, 0, length - i);
        }

    }

    /**
     * <description> 将给定树节点调整为大顶堆 </description>
     *
     * @param arr             : 要调整的数组
     * @param parentNodeIndex :要调整的树节点
     * @param length          :  要调整的数组的长度长度
     * @return : void
     * @author : lw
     * @date : 2019-11-04 21:29
     */
    private static void adjustBigHeap(int[] arr, int parentNodeIndex, int length) {
        int tempValue = arr[parentNodeIndex];
        //从左节点开始
        for (int index = (parentNodeIndex + 1) * 2 - 1; index < length; index = (index + 1) * 2 - 1) {
            if (index + 1 < length && arr[index] < arr[index + 1]) {
                //调整为右节点
                index++;
            }
            if (tempValue < arr[index]) {
                arr[parentNodeIndex] = arr[index];
                parentNodeIndex = index;
            } else {
                break;
            }
        }
        arr[parentNodeIndex] = tempValue;
    }

    private static void swap(int[] arr, int swapIndex1, int swapIndex2) {
        arr[swapIndex1] = arr[swapIndex1] ^ arr[swapIndex2];
        arr[swapIndex2] = arr[swapIndex1] ^ arr[swapIndex2];
        arr[swapIndex1] = arr[swapIndex1] ^ arr[swapIndex2];
    }
}
