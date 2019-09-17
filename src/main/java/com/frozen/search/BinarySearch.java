package com.frozen.search;


import java.util.ArrayList;
import java.util.List;

/**
 * <program> datastructure </program>
 * <description> 二分查找 </description>
 *
 * @author : lw
 * @date : 2019-09-16 10:24
 **/
public class BinarySearch {

    /**
     * <description>在给定有序(从小到到排列)数组查找值,找到返回其下标索引(只寻找一个),找不到返回-1.</description>
     *
     * @param arr       : 有序数组 按从小到大排列
     * @param findValue : 要查找的数值
     * @return : int
     * @author : lw
     * @date : 2019-09-16 19:29
     */
    public static int search(int[] arr, int findValue) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return search(arr, findValue, 0, arr.length - 1);
    }

    /**
     * <description> 二分查找子方法 </description>
     *
     * @param arr       : 有序数组 按从小到大排列
     * @param findValue : 要查找的数值
     * @param left      : 查找的起始位置(包含)
     * @param right     : 查找的结束位置(包含)
     * @return : int
     * @author : lw
     * @date : 2019-09-16 19:53
     */
    private static int search(int[] arr, int findValue, int left, int right) {
        if (left > right) {
            return -1;
        }
        int midIndex = (left + right) / 2;
        int midValue = arr[midIndex];
        if (midValue == findValue) {
            return midIndex;
        } else if (midValue > findValue) {
            return search(arr, findValue, 0, midIndex - 1);
        } else {
            return search(arr, findValue, midIndex + 1, right);
        }
    }

    /**
     * <description> 在给定有序(从小到到排列)数组查找值,找到返回值下标索引的List,找不到返回空List </description>
     *
     * @param arr       :有序数组 按从小到大排列
     * @param findValue :要查找的数值
     * @return : java.util.List<java.lang.Integer>
     * @author : lw
     * @date : 2019-09-16 19:55
     */
    public static List<Integer> searchAll(int[] arr, int findValue) {
        List<Integer> indexList = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            return indexList;
        }
        int index = search(arr, findValue, 0, arr.length - 1);
        if (index != -1) {
            indexList.add(index);
            int i = index - 1;
            //向左查找
            while (i >= 0 && arr[i] == findValue) {
                indexList.add(i);
                i--;
            }
            //将i重新赋值为index+1
            i = index + 1;
            //向右查找
            while (i < arr.length && arr[i] == findValue) {
                indexList.add(i);
                i++;
            }
        }
        return indexList;
    }
}
