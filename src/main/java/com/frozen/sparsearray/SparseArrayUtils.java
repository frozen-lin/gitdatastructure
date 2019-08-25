package com.frozen.sparsearray;

import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏数组 sparseArray
 */
public class SparseArrayUtils {

    /**
     * 转换稀疏数组
     * @param simpleArray :普通数组
     * @return sparseArray
     */
    public static int[][] toSparseArray(int[][] simpleArray){
        if(simpleArray==null){
            return null;
        }
        /*
         * 获取稀疏数组有值的个数
         */
        List<Item> itemList = new ArrayList<>();
        int sum=1;
        for (int i = 0; i < simpleArray.length; i++) {
            for (int j = 0; j < simpleArray[i].length; j++) {
                int value = simpleArray[i][j];
                if(value!=0){
                    Item item = new Item();
                    item.setItem(simpleArray[i][j]);
                    item.setRowIndex(i);
                    item.setLineIndex(j);
                    itemList.add(item);
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum][3];
        sparseArray[0][0] = simpleArray.length;
        sparseArray[0][1] = simpleArray[0].length;
        sparseArray[0][2] = sum;
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            sparseArray[i+1][0] = item.getRowIndex();
            sparseArray[i+1][1] = item.getLineIndex();
            sparseArray[i+1][2] = item.getValue();
        }
        return sparseArray;
    }

    /**
     * 转换普通数组
     * @param sparseArray:稀疏数组
     * @return 返回普通数组
     */
    public static int[][] toSimpleArray(int[][]  sparseArray){
        if(sparseArray ==null||sparseArray.length==0){
            throw new RuntimeException("传入数组为空!");
        }
        if(sparseArray[0].length!=3||sparseArray.length!=sparseArray[0][2]){
            throw new RuntimeException("传入数组格式不是稀疏数组");
        }

        int[][] simpleArray =  new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray[0][2]; i++) {
            simpleArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return simpleArray;
    }

    private static class Item{
        private int rowIndex;
        private int lineIndex;
        private int value;

        private int getLineIndex() {
            return lineIndex;
        }

        private void setLineIndex(int lineIndex) {
            this.lineIndex = lineIndex;
        }

        private int getValue() {
            return value;
        }

        private void setItem(int value) {
            this.value = value;
        }

        private int getRowIndex() {
            return rowIndex;
        }

        private void setRowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
        }
    }
}
