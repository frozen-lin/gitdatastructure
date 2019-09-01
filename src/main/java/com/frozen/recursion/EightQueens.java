package com.frozen.recursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: datastructure
 * @description: 8皇后问题
 * @author: lw
 * @create: 2019-09-01 17:57
 **/
public class EightQueens {
    /**
     * arr:临时结果arr
     */
    private int[] arr;
    /**
     * resultList :放置数组结果List
     */
    private List<int[]> resultList;
    /**
     * checkCount:校验次数
     */
    private int checkCount = 0;
    /**
     * @description :   默认棋盘8*8
     * @author : lw
     * @date : 2019-09-01 18:05
     */
    public EightQueens() {
        arr = new int[8];
        resultList = new LinkedList<>();
    }

    /**
     * @param size
     * @description : 设置棋盘大小
     * @author : lw
     * @date : 2019-09-01 18:07
     */
    public EightQueens(int size) {
        arr = new int[size];
        resultList = new LinkedList<>();
    }

    public static void main(String[] args) {
        EightQueens queens = new EightQueens();
        queens.startGame();
        System.out.println("共"+queens.getResultList().size()+"种结果");
        System.out.println("校验次数:"+queens.checkCount);
        for (int[] result:queens.getResultList()){
            System.out.println(Arrays.toString(result));
        }
    }

    public void startGame(){
        putChess(0);
    }
    /**
     * @param n 第几个旗子
     * @return : void
     * @description :  放置第n个棋子,从0开始
     * @author : lw
     * @date : 2019-09-01 18:08
     */
    private void putChess(int n) {
        if (n >= arr.length) {
            resultList.add(arr.clone());
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[n] = i;
            if (checkChess(n)) {
                putChess(n + 1);
            }
        }
    }

    /**
     * @param n 第几个棋子,从0开始
     * @return : boolean
     * @description : 判断放置的位置是否可行
     * @author : lw
     * @date : 2019-09-01 18:10
     */
    private boolean checkChess(int n) {
        checkCount++;
        for (int i = 0; i < n; i++) {
            /**
             * arr[i]==arr[n] : 放置在同一列
             * Math.abs(arr[i]-arr[n])==Math.abs(i-n):在同一条斜线上
             * (arr[i]-arr[n])/(i-n)==+-1,即Math.abs(arr[i]-arr[n])==Math.abs(i-n)
             */
            if (arr[i] == arr[n] || Math.abs(arr[i] - arr[n]) == Math.abs(i - n)) {
                return false;
            }
        }
        return true;
    }
    /**
     * @description :   获取存放结果List
     * @return : java.util.List<int[]>
     * @author : lw
     * @date : 2019-09-01 18:28
     */
    public List<int[]> getResultList() {
        return resultList;
    }
}
