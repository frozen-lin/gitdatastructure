package com.frozen.stack.impl;

import com.frozen.stack.Stack;

import java.util.Scanner;

/**
 * @program: datastructure
 * @description: 数组栈
 * @author: lw
 * @create: 2019-08-17 21:32
 **/
public class ArrayStack<T> implements Stack<T> {
    final private int defaultMaxSize = 8;
    private int maxSize;
    private int top = -1;
    private Object[] valArr;

    public ArrayStack() {
        this.maxSize = defaultMaxSize;
        valArr = new Object[maxSize];
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        valArr = new Object[maxSize];
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>(4);
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("~~~输入命令~~~");
            System.out.println("list:展示栈");
            System.out.println("pop:出栈");
            System.out.println("push:入栈");
            System.out.println("full:栈是否满");
            System.out.println("empty:栈是否空");
            System.out.println("exit:退出");
            String next = scanner.next();
            switch (next) {
                case "list":
                    stack.list();
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.out.println("栈为空");
                    }
                    break;
                case "push":
                    try {
                        System.out.println("输入新值");
                        stack.push(scanner.nextInt());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("栈已满");
                    } catch (Exception e) {
                        System.out.println("输入异常!");
                    }
                    break;
                case "full":
                    System.out.println(stack.isFull());
                    break;
                case "empty":
                    System.out.println(stack.isEmpty());
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }

    @Override
    public void push(T i) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("栈已满");
        }
        this.valArr[++top] = i;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("栈为空");
        }
        return (T)this.valArr[top--];
    }

    @Override
    public T peek() {
        return (T)this.valArr[top];
    }

    @Override
    public boolean isFull() {
        return this.top + 1 == this.maxSize;
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        for (int i = 0; i < top + 1; i++) {
            System.out.println(this.valArr[top - i]);
        }
    }
}
