package com.frozen.stack;

/**
 * @program: datastructure
 * @description: 站接口
 * @author: lw
 * @create: 2019-08-17 21:27
 **/
public interface Stack<T> {
    /**
     *  入栈
     * @param value
     */
    void push(T value);

    /**
     * 出栈
     * @return int
     */
    T pop();

    /**
     * 查看栈顶数据
     * @return int
     */
    T peek();
    /**
     * 栈是否满
     * @return boolean
     */
    boolean isFull();

    /**
     * 栈是否为空
     * @return boolean
     */
    boolean isEmpty();

    /**
     * 遍历栈
     */
    void list();
}
