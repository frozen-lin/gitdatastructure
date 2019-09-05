package com.frozen.sort.application;

import com.frozen.sort.BubbleSort;
import com.frozen.sort.InsertionSort;
import com.frozen.sort.SelectionSort;

import java.util.Arrays;
import java.util.Random;

/**
 * @program: datastructure
 * @description: 排序应用类
 * @author: lw
 * @create: 2019-09-04 20:54
 **/
public class SortApplication {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        int length = arr.length;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(length);
        }
        long start = System.currentTimeMillis();
        //冒泡排序
        //BubbleSort.sort(arr);
        //选择排序
        //SelectionSort.sort(arr);
        //插入排序
        InsertionSort.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间"+(end-start)+"毫秒");
        System.out.println(Arrays.toString(arr));
    }
}
