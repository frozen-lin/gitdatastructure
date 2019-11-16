package com.frozen.entity;

import lombok.Data;

/**
 * <program> datastructure </program>
 * <description> 键值对对象 </description>
 *
 * @author : lw
 * @date : 2019-11-08 09:53
 **/
@Data
public class WeightValObj <T>{
    private Integer weight;
    private T obj;
    public WeightValObj(){

    }

    public WeightValObj(Integer weight,T obj){
        this.weight = weight;
        this.obj = obj;
    }

}
