package com.frozen.search;

import java.util.ArrayList;
import java.util.List;

/**
 * <program> datastructure </program>
 * <description> 线性查找 </description>
 *
 * @author : lw
 * @date : 2019-09-15 21:43
 **/
public class SeqSearch {
    /**
     * <description> 在给定数组查找值,找到返回其下标索引(只寻找一个),找不到返回-1. </description>
     * @param arr : 查找的数组
     * @param findValue : 要查找的值
     * @return : int
     * @author : lw
     * @date : 2019-09-15 21:51
     */
    public static int search(int[] arr,int findValue){
        if (arr==null||arr.length==0){
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (findValue==arr[i]){
                return i;
            }
        }
        return -1;
    }
    /**
     * <description> 在给定数组查找值,找到返回值下标索引的List,找不到返回空List </description>
     * @param arr : 查找的数组
     * @param findValue : 要查找的值
     * @return : java.util.List<java.lang.Integer>
     * @author : lw
     * @date : 2019-09-15 21:55
     */
    public static List<Integer> searchAll(int[] arr, int findValue){
        List<Integer> indexList = new ArrayList<>();
        if (arr==null||arr.length==0){
            return indexList;
        }
        for (int i = 0; i < arr.length; i++) {
            if (findValue==arr[i]){
                indexList.add(i);
            }
        }
        return indexList;
    }
}
