package com.frozen.queue;

public interface IQueue {
    /**
     * @return boolean判断队列是否为空
     */
    boolean isEmpty();

    boolean isFull();

    /**
     * @param value 插入队列的值
     */
    void add(int value);

    /**
     * @return 取出并返回队列首位的值
     */
    int remove();

    /**
     * @return 返回不取出队列首位的值
     */
    int peak();

    String showQueue();

    int size();
}