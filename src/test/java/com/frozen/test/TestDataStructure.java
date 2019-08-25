package com.frozen.test;

import com.frozen.entity.Hero;
import com.frozen.josephu.Josephu;
import com.frozen.linkedlist.DoubleLinkedList;
import com.frozen.linkedlist.SingleLinkedList;
import com.frozen.stack.application.InfixCalculator;
import org.junit.Test;
import com.frozen.sparsearray.SparseArrayUtils;

import java.util.Arrays;

public class TestDataStructure {

    @Test
    public void testSparseArray() {
        int[][] simpleArr = new int[8][8];
        simpleArr[0][2] = 3;
        simpleArr[1][5] = 6;
        simpleArr[3][6] = 8;
        System.out.println("普通数组:~~~~~~~");
        System.out.println(Arrays.deepToString(simpleArr));

        int[][] sparseArray = SparseArrayUtils.toSparseArray(simpleArr);
        System.out.println("稀疏数组:~~~~~~~");
        System.out.println(Arrays.deepToString(sparseArray));
        int[][] simpleArr2 = SparseArrayUtils.toSimpleArray(sparseArray);
        System.out.println("转换普通数组:~~~~~~~");
        System.out.println(Arrays.deepToString(simpleArr2));
    }

    @Test
    public void testSingleLinkedList(){
        SingleLinkedList<Hero> linkedList = new SingleLinkedList<>();
        linkedList.list();
        Hero hero1 = new Hero( 1,"李白");
        Hero hero2 = new Hero( 2,"李太白");
        Hero hero3 = new Hero( 3,"唐玄宗");
        Hero hero4 = new Hero( 4,"李世民");
        /*
        //普通添加
        linkedList.add(hero1);
        linkedList.add(hero4);
        linkedList.add(hero2);
        linkedList.add(hero3);
        */
        //按序号添加
        linkedList.addByNo(hero1,hero1.getNo());
        linkedList.addByNo(hero4,hero4.getNo());
        linkedList.addByNo(hero2,hero2.getNo());
        linkedList.addByNo(hero3,hero3.getNo());
        linkedList.list();
        linkedList.reverse1();
        System.out.println("翻转1~~~~~~");
        linkedList.list();
        linkedList.reverse2();
        System.out.println("翻转2~~~~~~");
        linkedList.list();
        linkedList.reverse3();
        System.out.println("翻转3~~~~~~");
        linkedList.list();
        linkedList.reverse4();
        System.out.println("翻转4~~~~~~");
        linkedList.list();
        System.out.println("逆序打印~~~~");
        linkedList.printReverse();
        System.out.println("正常打印~~~~");
        linkedList.list();
        /*
        //按索引移除值
        linkedList.remove(0);
        linkedList.remove(4);
        linkedList.remove(2);
        */
        //按序号移除值
        Hero remove1 = linkedList.remove((long) hero2.getNo());
        System.out.println(remove1);
        Hero remove2 = linkedList.remove((long) hero4.getNo());
        System.out.println(remove2);
        linkedList.list();
        System.out.println("删除最后一位");
        Hero removeLast = linkedList.removeLast();
        System.out.println(removeLast);
        linkedList.list();
    }

    @Test
    public void testDoubleLinkedList(){
        DoubleLinkedList<Hero> linkedList = new DoubleLinkedList<>();
        linkedList.list();
        Hero hero1 = new Hero( 1,"李白");
        Hero hero2 = new Hero( 2,"李太白");
        Hero hero3 = new Hero( 3,"唐玄宗");
        Hero hero4 = new Hero( 4,"李世民");
        //普通添加
        linkedList.add(hero1);
        linkedList.add(hero4);
        linkedList.add(hero2);
        linkedList.add(hero3);
        /*
        //按序号添加
        linkedList.addByNo(hero1,hero1.getNo());
        linkedList.addByNo(hero4,hero4.getNo());
        linkedList.addByNo(hero2,hero2.getNo());
        linkedList.addByNo(hero3,hero3.getNo());
        */

        linkedList.list();
        //按序号移除值
        Hero remove1 = linkedList.remove((long) hero2.getNo());
        System.out.println(remove1);
        Hero remove2 = linkedList.remove((long) hero4.getNo());
        System.out.println(remove2);
        linkedList.list();
    }

    /**
     * 测试约瑟夫问题
     */
    @Test
    public void testJosephu(){
        Josephu josephu = new Josephu();
        josephu.addNode(5);
        josephu.show();
        josephu.run(1,2);
    }

    @Test
    public void testInfixCalculator(){
        InfixCalculator calculator = new InfixCalculator();
        String expression = "32*3*4+5+3/3";
        System.out.println("表达式  "+expression+"="+calculator.calculate(expression));
    }
}
