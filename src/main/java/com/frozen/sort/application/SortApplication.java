package com.frozen.sort.application;

import com.frozen.sort.*;

import java.util.Arrays;
import java.util.Random;

/**
 * <program> datastructure </program>
 * <description> 排序测试类 </description>
 * @author : lw
 * @date : 2019-09-04 20:54
 **/
public class SortApplication {
    public static void main(String[] args) {
        int[] arr = new int[800000];
        int length = arr.length;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(length*10);
        }
        //System.out.println("排序前1:"+Arrays.toString(arr));
        long start = System.currentTimeMillis();
        //冒泡排序
        //BubbleSort.sort(arr);
        //选择排序
        //SelectionSort.sort(arr);
        //插入排序
        //InsertionSort.sort(arr);
        //希尔排序
        //ShellSort.sort(arr);
        //快排
        //QuickSort.sort(arr);
        //归并
        //MergeSort.sort(arr);
        //基数
        //RadixSort.sort(arr);
        //堆排序
        HeapSort.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间"+(end-start)+"毫秒");
        //System.out.println(Arrays.toString(arr));
    }
}
