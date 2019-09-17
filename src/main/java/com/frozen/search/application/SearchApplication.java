package com.frozen.search.application;

import com.frozen.search.BinarySearch;
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

        int[] arr1 = {1, 3, 3, 4, 5, 7, 9, 512};
        System.out.println(BinarySearch.search(arr1, 22));
        System.out.println(BinarySearch.searchAll(arr1, 3));
    }
}
