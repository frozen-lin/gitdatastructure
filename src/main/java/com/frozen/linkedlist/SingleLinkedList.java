package com.frozen.linkedlist;

import java.util.Stack;

/**
 * @program: datastructure
 * @description: 单向链表
 * @author: lw
 * @create: 2019-08-11 13:23
 **/
public class SingleLinkedList<T> {

    private SingleNode<T> head;
    /**
     * 链表长度
     */
    private int size;
    /**
     * 添加进链表的个数
     */
    private int count;

    public SingleLinkedList() {
        head = new SingleNode<>();
    }

    /**
     * @Description: 链表添加值方法
     * @Param: value 添加的值
     * @Author: lw
     * @DateTime: 2019-08-11 13:47
     */
    public void add(T value) {
        SingleNode<T> curNode = head;
        while (curNode.next != null) {
            curNode = curNode.next;
        }
        curNode.next = new SingleNode<>(value, ++this.count);
        this.size++;
    }

    /**
     * @Description: 按序号添加节点
     * @Param: value 添加的值 ,no 节点 序号
     * @return:
     * @Author: lw
     * @DateTime: 2019-08-11 14:46
     */
    public void addByNo(T value, long no) {
        SingleNode<T> curNode = head;
        while (curNode.next != null) {
            if (curNode.next.no >= no) {
                break;
            }
            curNode = curNode.next;
        }
        SingleNode<T> newNode = new SingleNode<>(value, no);
        newNode.next = curNode.next;
        curNode.next = newNode;
        this.size++;
    }

    /**
     * @Description: 移除最后一处节点, 若链表为空, 返回null
     * @return: T 删除的节点
     * @Author: lw
     * @DateTime: 2019-08-11 14:04
     */
    public T removeLast() {
        return remove(this.size - 1);
    }

    /**
     * @Description: 移除指定位置节点, index从0开始.若链表长度不够返回null
     * @Param: index 移除节点的索引
     * @return: T 删除的节点
     * @Author: lw
     * @DateTime: 2019-08-11 13:52
     */
    public T remove(int index) {
        SingleNode<T> cur = head;
        int count = 0;
        /**
         * 记录是否找到该索引
         */
        boolean flag = false;
        while (cur.next != null) {
            if (index == count) {
                flag = true;
                break;
            }
            cur = cur.next;
            count++;
        }
        if (flag) {
            return unlinkNode(cur);
        } else {
            System.out.println("未找到要删除的节点!!!");
            return null;
        }
    }

    /**
     * @Description: 根据序号删除指定节点, 移除最先添加进的节点
     * @Param: no : 删除节点的序号
     * @return: T : 移除的值
     * @Author: lw
     * @DateTime: 2019-08-11 14:19
     */
    public T remove(long no) {
        SingleNode<T> cur = head;
        /**
         * 记录是否找到该索引
         */
        boolean flag = false;
        while (cur.next != null) {
            if (cur.next.no == no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            return unlinkNode(cur);
        } else {
            System.out.println("未找到要删除的节点!!!");
            return null;
        }
    }

    /**
     * @Description: 断开传入节点的下一节点
     * @Param: cur 传入断开节点的上一节点
     * @return:
     * @Author: lw
     * @DateTime: 2019-08-11 14:18
     */
    private T unlinkNode(SingleNode<T> cur) {
        T obj = cur.next.value;
        cur.next = cur.next.next;
        this.size--;
        return obj;
    }

    /**
     * @Description: 根据节点序号修改节点的值
     * @Param: T 修改之后的值; no:要修改值得序号
     * @return:
     * @Author: lw
     * @DateTime: 2019-08-11 14:12
     */
    public boolean editByNo(T value, long no) {
        SingleNode<T> cur = head.next;
        boolean flag =false;
        while (cur != null) {
            if (cur.no == no) {
                cur.value = value;
                flag = true;
            }
            cur = cur.next;
        }
        if(!true){
            System.out.println("未找到修改的值");
        }
        return flag;
    }

    /**
     * @Description: 遍历打印链表
     * @Author: lw
     * @DateTime: 2019-08-11 14:38
     */
    public void list() {
        SingleNode<T> cur = head.next;
        if (cur == null) {
            System.out.println("链表为空!!!");
            return;
        }
        while (cur != null) {
            System.out.println("no:" + cur.no + "~~~值:" + cur.value);
            cur = cur.next;
        }
    }

    /**
     * @Description: 返回链表长度
     * @return: int 长度
     * @Author: lw
     * @DateTime: 2019-08-11 16:48
     */
    public int size() {
        return this.size;
    }

    /**
     * @Description: 获取指定索引的值 正数从0开始,负数代表倒数
     * @Param: index:要获取的值得索引
     * @return:
     * @Author: lw
     * @DateTime: 2019-08-11 17:00
     */
    public T get(int index) {
        if (index < 0) {
            index = -index - 1;
        }
        if (index >= this.size) {
            return null;
        }
        int count = 0;
        SingleNode<T> cur = head.next;
        while (cur != null) {
            if (count++ == index) {
                break;
            }
            cur = cur.next;
        }
        return cur.value;
    }

    /**
     * @Description: 反转链表1
     * @Author: lw
     * @DateTime: 2019-08-11 17:11
     */
    public void reverse1() {
        SingleNode<T> cur = head;
        SingleNode<T> tempNode = null;
        while (cur.next != null) {
            /**
             * 断开节点
             */
            SingleNode<T> temp = cur.next;
            cur.next = cur.next.next;
            /**
             * 组成新链表
             */
            temp.next = tempNode;
            tempNode = temp;
        }
        head.next = tempNode;
    }

    /**
     * @Description: 反转链表2
     * @Author: lw
     * @DateTime: 2019-08-11 17:11
     */
    public void reverse2() {
        SingleNode<T> cur = head.next;
        if (this.size == 0 || this.size == 1) {
            return;
        }
        SingleNode<T> pre = null;
        SingleNode<T> next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = pre;
    }

    /**
     * @Description: 反转链表3
     * @Author: lw
     * @DateTime: 2019-08-11 17:11
     */
    public void reverse3() {
        head.next = priReverse3(null, head.next);
    }

    private SingleNode<T> priReverse3(SingleNode<T> pre, SingleNode<T> cur) {
        if (cur == null) {
            return null;
        }
        SingleNode<T> next = cur.next;
        cur.next = pre;
        if (next == null) {
            return cur;
        }
        return priReverse3(cur, next);
    }

    /**
     * @Description: 反转链表4
     * @Author: lw
     * @DateTime: 2019-08-11 18:43
     */
    public void reverse4() {
        head.next = priReverse4(head.next);
    }

    private SingleNode<T> priReverse4(SingleNode<T> cur) {
        if (cur == null || cur.next == null) {
            return cur;
        }
        SingleNode<T> node = priReverse4(cur.next);
        cur.next.next = cur;
        cur.next = null;
        return node;
    }
    /** 
      * @Description: 逆序打印，不改变原链表
      * @Author: lw 
      * @DateTime: 2019-08-11 19:05
      */
    public void printReverse(){
        SingleNode<T> cur = head.next;
        if (cur == null) {
            System.out.println("链表为空!!!");
            return;
        }
        Stack<SingleNode<T>> stack = new Stack<>();
        while(cur!=null){
            stack.add(cur);
            cur = cur.next;
        }
        SingleNode<T> node;
        while(!stack.isEmpty()){
            node = stack.pop();
            System.out.println("no:" + node.no + "~~~值:" + node.value);
        }
    }
    /**
     * @Description: 单向链表节点子类
     * @Author: lw
     * @DateTime: 2019-08-11 13:42
     */
    private static class SingleNode<T> {
        private T value;
        private long no;
        private SingleNode<T> next;

        private SingleNode() {

        }

        private SingleNode(T value, long no) {
            this.value = value;
            this.no = no;
        }
    }
}

