package com.frozen.stack.impl;

import com.frozen.stack.Stack;

import java.util.Scanner;

/**
 * @program: datastructure
 * @description: 链表栈
 * @author: lw
 * @create: 2019-08-18 19:49
 **/
public class LinkedStack<T> implements Stack<T> {
    /*栈顶*/
    private Node<T> top;

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            this.top = newNode;
        } else {
            newNode.setNext(top);
            top = newNode;
        }
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("栈为空!");
        }
        T value = top.getValue();
        top = top.getNext();
        return value;
    }

    @Override
    public T peek() {
        return top.getValue();
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void list() {
        if(isEmpty()){
            System.out.println("栈为空");
            return;
        }
        Node curr = top;
        while (curr != null) {
            System.out.println(curr.getValue());
            curr = curr.getNext();
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedStack<>();
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println();
            System.out.println("~~~输入命令~~~");
            System.out.println("list:展示栈");
            System.out.println("pop:出栈");
            System.out.println("push:入栈");
            System.out.println("empty:栈是否空");
            System.out.println("exit:退出");
            String next = scanner.next();
            switch (next){
                case "list":
                    stack.list();
                    break;
                case "pop":
                    try{
                        System.out.println(stack.pop());
                    }catch (Exception e){
                        System.out.println("栈为空 ");
                    }
                    break;
                case "push":
                    try{
                        System.out.println("输入新值");
                        stack.push(scanner.nextInt());
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("栈已满");
                    }catch (Exception e){
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
}

class Node<T> {
    private T value;
    private Node<T> next;

    public Node() {

    }

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
