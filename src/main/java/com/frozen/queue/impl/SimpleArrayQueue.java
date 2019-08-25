package com.frozen.queue.impl;

import com.frozen.queue.IQueue;

/**
 * 普通数组实现队列
 */
public class SimpleArrayQueue implements IQueue {
    /**
     * 队列最大容量
     */
    private int maxSize;
    /**
     * 首指针,指向队列首索引
     */
    private int head;
    /**
     * 尾指针,指向队列尾部索引+1
     */
    private int tail;
    /**
     * 队列存放值的数组
     */
    private int[] array;

    public SimpleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.head = 0;
        this.tail = 0;
        this.array = new int[maxSize];
    }

    /**
     * @return boolean判断队列是否为空
     */
    @Override
    public boolean isEmpty() {
        return this.head == this.tail;
    }

    @Override
    public boolean isFull() {
        return this.tail == this.maxSize;
    }

    /**
     * @param value 插入队列的值
     */
    @Override
    public void add(int value) {
        if (isFull()) {
            throw new IllegalArgumentException("队列已满");
        }
        this.array[this.tail++] = value;
    }

    /**
     * @return 取出并返回队列首位的值
     */
    @Override
    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,无值可出");
        }
        int val = this.array[this.head];
        for (int i = 0; i < this.tail - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        tail--;
        return val;
    }

    /**
     * @return 返回不取出队列首位的值
     */
    @Override
    public int peak() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,无值可出");
        }
        return this.array[this.head];
    }

    /**
     * @return 返回队列展示字符串
     */
    @Override
    public String showQueue() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("队列为空~~~~");
        }
        sb.append("队列已存" + this.tail + "个值:");
        for (int i = this.head; i < this.tail; i++) {
            sb.append(this.array[i]+"    ");
        }
        return sb.toString();
    }

    @Override
    public int size() {
        return this.tail;
    }
}
