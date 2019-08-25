package com.frozen.queue.impl;

import com.frozen.queue.IQueue;

public class CircleArrayQueue implements IQueue {
    /**
     * 队列数组长度
     */
    int arrLength;
    /**
     * 头指针指向队列第一位
     */
    private int head;
    /**
     * 尾指针指向队列末尾的后一位
     */
    private int tail;
    /**
     * 存放数据的数组
     */
    private int[] array;

    public CircleArrayQueue(int maxSize){
        this.array = new int[maxSize+1];
        this.arrLength = maxSize+1;
        this.head = 0;
        this.tail = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.head == this.tail;
    }

    @Override
    public boolean isFull() {
        return (this.tail+1)%this.arrLength == this.head;
    }

    @Override
    public void add(int value) {
        if(isFull()){
            throw new IllegalArgumentException("队列已满");
        }
        this.array[this.tail] = value;
        tail = (this.tail+1)%this.arrLength;
    }

    @Override
    public int remove() {
        if(isEmpty()){
            throw new RuntimeException("队列为空,无值可出");
        }
        int temp = this.array[this.head];
        this.head = (this.head+1)%this.arrLength;
        return temp;
    }

    @Override
    public int peak() {
        if(isEmpty()){
            throw new RuntimeException("队列为空,无值可出");
        }
        return this.array[this.head];
    }

    @Override
    public String showQueue() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("队列为空~~~~");
        }
        sb.append("队列已存" + this.size()+ "个值:");
        for (int i = 0; i < this.size(); i++) {
            sb.append(this.array[(i+this.head)%this.arrLength]+"    ");
        }
        return sb.toString();
    }

    @Override
    public int size() {
        return (this.tail+this.arrLength-this.head)%this.arrLength;
    }
}
