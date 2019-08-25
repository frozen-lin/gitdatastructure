package com.frozen.josephu;

/**
 * @program: datastructure
 * @description: 约瑟夫问题
 * @author: lw
 * @create: 2019-08-16 21:16
 **/
public class Josephu {
    /*头结点*/
    private Node head;
    /*头结点的前一个节点*/
    private Node pre;
    /*构造节点数*/
    private int nodeNum;

    public Josephu() {

    }

    /*节点添加数量*/
    public void addNode(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("加入节点数量有误");
        }
        Node node;
        for (int i = 0; i < count; i++) {
            nodeNum++;
            node = new Node(nodeNum);
            if (pre == null) {
                head = node;
            } else {
                pre.setNext(node);
            }
            pre = node;
        }
        //构成环形
        pre.setNext(head);
    }

    /*开始处理约瑟夫问题*/
    public void run(int k, int m) {
        if (k > nodeNum || k < 1 || m < 1 || head == null) {
            throw new IllegalArgumentException("参数异常");
        }
        /*将头节点设置为k的位置*/
        for (int i = 1; i < k; i++) {
            head = head.getNext();
            pre = pre.getNext();
        }
        this.run(m);
    }

    private void run(int m) {
        if (head.getNext() == head) {
            System.out.println("最后留下的节点是" + head);
            return;
        }
        for (int i = 1; i < m; i++) {
            head = head.getNext();
            pre = pre.getNext();
        }
        System.out.println("移除的节点为" + head);
        //移除头结点
        pre.setNext(head.getNext());
        head = head.getNext();
        this.run(m);
    }

    public void show() {
        if (head == null) {
            System.out.println("圈中无数据");
            return;
        }
        Node curr = head;
        System.out.println("圈中初始数据:");
        System.out.println(curr);
        while (curr.getNext() != head) {
            System.out.println(curr.getNext());
            curr = curr.getNext();
        }
        System.out.println("~~~~~~~~~~~~");
    }
}

class Node {

    private int no;
    /*男孩下一节点*/
    private Node next;

    public Node() {

    }

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +"no=" + no+"}";
    }
}
