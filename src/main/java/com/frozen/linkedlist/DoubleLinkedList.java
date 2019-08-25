package com.frozen.linkedlist;

/**
 * @program: datastructure
 * @description: 双向链表
 * @author: lw
 * @create: 2019-08-11 20:38
 **/
public class DoubleLinkedList<T> {
    private DoubleNode<T> head;
    private int count;
    private int size;

    public DoubleLinkedList() {
        head = new DoubleNode<>();
    }

    /**
     * @Description: 双向节点添加值
     * @Param: T :要添加的值
     * @return:
     * @Author: lw
     * @DateTime: 2019-08-11 20:44
     */
    public void add(T value) {
        DoubleNode<T> cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        DoubleNode<T> newNode = new DoubleNode<>(value, ++count);
        cur.next = newNode;
        newNode.pre = cur;
        size++;
    }

    /**
     * @Description: 双向节点添加值
     * @Param: T :要添加的值
     * @return:
     * @Author: lw
     * @DateTime: 2019-08-11 20:44
     */
    public void addByNo(T value,long no) {
        DoubleNode<T> cur = head;
        while (cur.next != null) {
            if (cur.next.no >= no) {
                break;
            }
            cur = cur.next;
        }
        DoubleNode<T> newNode = new DoubleNode<>(value, no);
        DoubleNode<T> next = cur.next;
        cur.next = newNode;
        newNode.pre = cur;
        newNode.next = next;
        if(next!=null){
            next.pre = newNode;
        }
        size++;
    }
    /**
     * @Description: 移除节点
     * @Param: no :节点编号
     * @return: T : 移除的节点
     * @Author: lw
     * @DateTime: 2019-08-11 21:01
     */
    public T remove(long no) {
        DoubleNode<T> cur = head.next;
        boolean flag = false;
        while (cur != null) {
            if (cur.no == no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if(flag){
            cur.pre.next = cur.next;
            if(cur.next!=null){
                cur.next.pre = cur.pre;
            }return cur.value;
        }else{
            return null;
        }
    }
    /**
     * @Description: 修改指定编号的值
     * @Param:  T :修改后的值，no；修改值得编号
     * @return: boolean:返回是否修改成功
     * @Author: lw
     * @DateTime: 2019-08-11 21:18
     */
    public boolean editByNo(T value, long no){
        DoubleNode<T> cur = head.next;
        boolean flag = false;
        while (cur != null) {
            if (cur.no == no) {
                flag = true;
                cur.value = value;
            }
            cur = cur.next;
        }
        if(!flag){
            System.out.println("未找到要修改的值");
        }
        return flag;

    }
    /** 
      * @Description: 遍历打印节点
      * @Author: lw 
      * @DateTime: 2019-08-11 21:09
      */
    public void list(){
        DoubleNode<T> cur = head.next;
        if (cur==null){
            System.out.println("链表为空！！！");
            return;
        }
        while (cur!=null){
            System.out.println("no: "+cur.no+"~~~value:"+cur.value);
            cur= cur.next;
        }
    }
    /**
     * @Description: 双向链表节点子类
     * @Author: lw
     * @DateTime: 2019-08-11 13:42
     */
    private static class DoubleNode<T> {
        private T value;
        private long no;
        private DoubleNode<T> next;
        private DoubleNode<T> pre;

        private DoubleNode() {

        }

        private DoubleNode(T value, long no) {
            this.value = value;
            this.no = no;
        }
    }
}
