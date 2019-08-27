package com.frozen.recursion;

import java.util.Arrays;

/**
 * @program: datastructure
 * @description: 递归应用 走迷宫
 * @author: lw
 * @create: 2019-08-27 19:05
 **/
public class Labyrinth {
    private int[][] map = new int[10][10];

    public static void main(String[] args) {
        Labyrinth labyrinth = new Labyrinth();
        /**
         * 0 0 0 0 0 0 0 0 0 0  y
         * 0 1 0 1 0 1 0 0 0 0
         * 1 0 0 1 0 1 0 0 0 0
         * 0 1 1 1 1 0 0 0 0 0
         * 0 0 0 0 0 0 0 0 0 0
         * 0 0 0 0 0 0 0 0 0 0
         * 0 0 0 0 0 0 0 0 0 0
         * 0 0 0 0 0 0 0 0 0 0
         * 0 0 0 0 0 0 0 0 0 0
         * 0 0 0 0 0 0 0 0 0 0
         *
         * x
         */
        //设置地图
        labyrinth.map[1][1] = 1;
        labyrinth.map[1][3] = 1;
        labyrinth.map[1][5] = 1;
        labyrinth.map[2][0] = 1;
        labyrinth.map[2][3] = 1;
        labyrinth.map[2][5] = 1;
        labyrinth.map[3][1] = 1;
        labyrinth.map[3][2] = 1;
        labyrinth.map[3][3] = 1;
        labyrinth.map[3][4] = 1;
        for (int i = 0; i < labyrinth.map.length; i++) {
            System.out.println(Arrays.toString(labyrinth.map[i]));
        }
        System.out.println("迷宫是否走通:"+labyrinth.explorer(1, 0));
        for (int i = 0; i < labyrinth.map.length; i++) {
            System.out.println(Arrays.toString(labyrinth.map[i]));
        }
    }

    /**
     * 迷宫地图数组 0代表没走过,1代表障碍,2代表走过,3代表走不通.终点9,9
     * @param x   所在位置x坐标
     * @param y   所在位置y坐标
     * @return : boolean 是否能走通
     * @Description : 探索地图 下 右 上 左 的顺序探索
     * @Author : lw
     * @DateTime : 2019-08-27 19:08
     */
    public boolean explorer(int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            //在地图外,返回false
            return false;
        }
        if (x == 9 && y == 9) {
            //已在终点,返回true
            map[x][y] = 2;
            return true;
        }
        if (map[x][y] == 0) {
            //先将此处坐标设为走过
            map[x][y] = 2;
            //下
            if (explorer(x + 1, y)) {
                return true;
            }
            //右
            if (explorer(x, y + 1)) {
                return true;
            }
            //上
            if (explorer(x - 1, y)) {
                return true;
            }
            //左
            if (explorer(x, y - 1)) {
                return true;
            }
            //若都走不通返回false,将值设为3
            map[x][y] = 3;
            return false;
        } else {
            return false;
        }
    }
}
