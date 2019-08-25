package com.frozen.entity;

/**
 * @program: datastructure
 * @description: 英雄实体类
 * @author: lw
 * @create: 2019-08-11 13:48
 **/
public class Hero {

    private int no;
    private String name;

    public Hero() {

    }

    public Hero(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
