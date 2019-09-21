package com.frozen.search;

/**
 * <program> datastructure </program>
 * <description> 斐波那契查找算法 </description>
 *
 * @author : lw
 * @date : 2019-09-21 12:11
 **/
public class FibonacciSearch {
    private static int[] fib;

    //计算斐波那契数列
    static {
        int length = 20;
        fib = new int[length];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
    }

    /**
     * <description>  </description>
     *
     * @param arr       :有序数组 从小到大排列
     * @param findValue : 查找的值
     * @return : void
     * @author : lw
     * @date : 2019-09-21 12:15
     */
    public static int search(int[] arr, int findValue) {
        if (arr == null || arr.length < 1|| findValue < arr[0] || findValue > arr[arr.length-1]) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int k = 0;
        while (arr.length > (fib[k] - 1)) {
            k++;
        }
        while (left <= right) {
            int searchIndex = left + fib[k - 1] - 1;
            int searchValue = arr[Math.min(searchIndex, arr.length - 1)];
            //判断searchValue大小
            if (findValue > searchValue) {
                //右侧剩下fib[k-2]个,接下来可继续分成fib[k-1 -2]
                left = searchIndex + 1;
                k = k - 2;

            } else if (findValue < searchValue) {
                //左侧剩下fib[k-1]个接下来可继续分成fib[k-1 -1]
                right = searchIndex - 1;
                k--;
            } else {
                return Math.min(searchIndex, arr.length - 1);
            }
        }
        return -1;
    }
}
