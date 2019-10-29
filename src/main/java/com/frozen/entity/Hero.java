package com.frozen.entity;

import java.util.Objects;

/**
 * @program : datastructure
 * @description : 英雄实体类
 * @author : lw
 * @date : 2019-08-11 13:48
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Hero hero = (Hero) o;
        return no == hero.no &&
                Objects.equals(name, hero.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, name);
    }
}
