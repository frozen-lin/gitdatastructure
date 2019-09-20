package com.frozen.search;

import java.util.ArrayList;
import java.util.List;

/**
 * <program> datastructure </program>
 * <description> 插值查找 </description>
 *
 * @author : lw
 * @date : 2019-09-19 19:02
 **/
public class InterpolationSearch {
    /**
     * <description> 插值查找,在给定有序(从小到到排列)数组查找值,找到返回其下标索引(只寻找一个),找不到返回-1 </description>
     *
     * @param arr       : 有序数组 从小到大排列
     * @param findValue :
     * @return : int
     * @author : lw
     * @date : 2019-09-19 19:04
     */
    public static int search(int[] arr, int findValue) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return search(arr, findValue, 0, arr.length - 1);
    }

    /**
     * <description> 插值查找,在给定有序(从小到到排列)数组查找值,找到返回值下标索引的List,找不到返回空List </description>
     *
     * @param arr       :
     * @param findValue :
     * @return : java.util.List<java.lang.Integer>
     * @author : lw
     * @date : 2019-09-19 9:21
     */
    public static List<Integer> searchAll(int[] arr, int findValue) {
        List<Integer> indexList = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            return indexList;
        }
        int searchIndex = search(arr, findValue, 0, arr.length - 1);
        if (searchIndex != -1) {
            indexList.add(searchIndex);
            int addIndex = searchIndex - 1;
            //向左查找
            while (addIndex >= 0 && arr[addIndex] == findValue) {
                indexList.add(addIndex);
                addIndex--;
            }
            addIndex = searchIndex + 1;
            while (addIndex < arr.length && arr[addIndex] == findValue) {
                indexList.add(addIndex);
                addIndex++;
            }
        }
        return indexList;
    }

    /**
     * <description> 插值查找子方法 </description>
     *
     * @param arr       :
     * @param left      :有序数组查找开始起始位置(包含)
     * @param right     :有序数组查找结束位置(包含)
     * @param findValue :
     * @return : int
     * @author : lw
     * @date : 2019-09-19 19:06
     */
    private static int search(int[] arr, int findValue, int left, int right) {
        System.out.println("~~插值查找次数~~");
        //当左边起始位置>右边起始位置|| 要查找的值>最大值或<最小值,返回-1未找到
        if (left > right || findValue < arr[left] || findValue > arr[right]) {
            return -1;
        }
        int searchIndex = left + (findValue - arr[left]) * (right - left) / (arr[right] - arr[left]);
        int searchValue = arr[searchIndex];
        if (findValue > searchValue) {
            return search(arr, searchIndex + 1, right, findValue);
        } else if (findValue < searchValue) {
            return search(arr, left, searchIndex - 1, findValue);
        } else {
            return searchIndex;
        }
    }
}
