package com.frozen.queue;

import com.frozen.queue.impl.CircleArrayQueue;

import java.util.Scanner;

/**
 * 测试队列方法
 */
public class Application {
    public static void main(String[] args){
        IQueue queue = new CircleArrayQueue(5);
        //IQueue com.frozen.queue = new SimpleArrayQueue(5);
        System.out.println(queue.showQueue());
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        sb.append("a:插入数据\n");
        sb.append("r:出队列\n");
        sb.append("s:展示队列\n");
        sb.append("p:展示队列首位数据\n");
        sb.append("stop:停止\n");
        while (true) {
            System.out.println(sb.toString());
            String str = scanner.next();
            switch (str) {
                case "a":
                    System.out.println("请输入插入的数值:");
                    int i = scanner.nextInt();
                    queue.add(i);
                    break;
                case "r":
                    System.out.println(queue.remove());
                    break;
                case "s":
                    System.out.println(queue.showQueue());
                    break;
                case "p":
                    System.out.println(queue.peak());
                    break;
                case "stop":
                    System.out.println("退出");
                    scanner.close();
                    return;
                default:
                    System.out.println("输入有误请重新输入");
            }
        }
    }
}
