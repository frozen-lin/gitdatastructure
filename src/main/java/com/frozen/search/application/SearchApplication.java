package com.frozen.search.application;

import com.frozen.search.BinarySearch;
import com.frozen.search.FibonacciSearch;
import com.frozen.search.InterpolationSearch;
import com.frozen.search.SeqSearch;

/**
 * <program> datastructure </program>
 * <description> 查找应用类 </description>
 *
 * @author : lw
 * @date : 2019-09-15 21:46
 **/
public class SearchApplication {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 512, 3, 21, 2};
        System.out.println(SeqSearch.search(arr, 3));
        System.out.println(SeqSearch.searchAll(arr, 22));

        int[] arr1 = {1, 2, 3, 4, 5, 6,7,8, 9, 10,12,12,13,15,16,18,22,23};
        System.out.println("~~~二分查找~~~");
        System.out.println(BinarySearch.search(arr1, 10));
        System.out.println(BinarySearch.searchAll(arr1, 12));
        System.out.println("~~~插值查找~~~");
        System.out.println(InterpolationSearch.search(arr1, 10));
        System.out.println(InterpolationSearch.searchAll(arr1, 12));
        System.out.println("~~~斐波那契查找~~~");
        System.out.println(FibonacciSearch.search(arr1, 10));
    }
}
