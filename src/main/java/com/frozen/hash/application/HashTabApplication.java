package com.frozen.hash.application;

import com.frozen.entity.Hero;
import com.frozen.hash.MyEasyHashMap;

/**
 * <program> datastructure </program>
 * <description> hash表测试类 </description>
 *
 * @author : lw
 * @date : 2019-09-25 21:38
 **/
public class HashTabApplication {
    public static void main(String[] args) {
        MyEasyHashMap<Integer, Hero> easyHashMap = new MyEasyHashMap<>(3);
        System.out.println(easyHashMap.isEmpty());
        easyHashMap.put(1, new Hero(1,"张三"));
        easyHashMap.put(2, new Hero(2,"李四"));
        easyHashMap.put(3, new Hero(3,"王五"));
        easyHashMap.put(4, new Hero(4,"赵六"));
        easyHashMap.printAll();
        System.out.println(easyHashMap.get(3));
        System.out.println(easyHashMap.get(5));
        System.out.println(easyHashMap.containsKey(2));
        easyHashMap.remove(2);
        System.out.println(easyHashMap.isEmpty());
        System.out.println(easyHashMap.containsKey(2));
        System.out.println();
        easyHashMap.printAll();
        System.out.println();
        easyHashMap.clear();
        easyHashMap.printAll();
    }
}
