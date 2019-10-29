package com.frozen.hash;

import java.util.Map;
import java.util.Objects;

/**
 * <program> datastructure </program>
 * <description> (不进行扩展)简易hash表 </description>
 *
 * @author : lw
 * @date : 2019-09-25 18:57
 **/
public class MyEasyHashMap<K, V> {
    /**
     * hash表数组默认大小
     */
    private static final int DEFAULT_ARRAY_SIZE = 8;
    /**
     * hash表数组大小
     */
    private int arraySize;

    /**
     * hash表中存储对象个数
     */
    private int size = 0;

    /**
     * hash表数组
     */
    private Node<K, V>[] table;

    @SuppressWarnings({"rawtypes", "unchecked"})
    public MyEasyHashMap() {
        this.arraySize = DEFAULT_ARRAY_SIZE;
        this.table = (Node<K, V>[]) new Node[this.arraySize];
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public MyEasyHashMap(int arraySize) {
        if (arraySize <= 0) {
            throw new IllegalArgumentException("hash表数组大小设置有误!");
        }
        this.arraySize = arraySize;
        this.table = (Node<K, V>[]) new Node[this.arraySize];
    }


    public int size() {
        return this.size;
    }


    public boolean isEmpty() {
        return this.size == 0;
    }


    public boolean containsKey(Object key) {
        return getNode(hash(key),key) != null;
    }


    public V get(Object key) {
        Node<K, V> node = this.getNode(hash(key),key);
        if (Objects.isNull(node)) {
            return null;
        } else {
            return node.getValue();
        }
    }


    public V put(K key, V value) {
        return put(hash(key), key, value);
    }


    public V remove(Object key) {
        return remove(hash(key), key);
    }


    public void clear() {
        for (int i = 0; i < arraySize; i++) {
            table[i] = null;
        }
        this.size = 0;
    }
    /**
     * <description> 打印所有链表的数据 </description>
     * @return : void
     * @author : lw
     * @date : 2019-09-25 21:57
     */
    public void printAll(){
        for (int i = 0; i < this.arraySize; i++) {
            print(i);
        }
    }
    /**
     * <description> 根据索引打印单条链表的数据 </description>
     * @param index :
     * @return : void
     * @author : lw
     * @date : 2019-09-25 21:30
     */
    private void print(int index){
        System.out.println("第"+(index+1)+"条链表");
        Node<K, V> currNode = this.table[index];
        if (Objects.isNull(currNode)){
            System.out.println("无数据");
        }else{
            while (!Objects.isNull(currNode)){
                System.out.println("---"+currNode.getKey()+":"+currNode.getValue());
                currNode = currNode.next;
            }
        }
    }
    /**
     * <description> 根据hashcode重新计算hash值 </description>
     *
     * @param key :
     * @return : int
     * @author : lw
     * @date : 2019-09-25 19:42
     */
    private int hash(Object key) {
        if (key == null) {
            return 0;
        } else {
            int h = key.hashCode();
            return h ^ h >>> 16;
        }
    }

    /**
     * <description> 根据hash值计算存放hash表的位置 </description>
     *
     * @param hash :
     * @return : int
     * @author : lw
     * @date : 2019-09-25 20:15
     */
    private int calculateLocation(int hash) {
        return hash % this.arraySize;
    }

    /**
     * <description> 根据KEY值,查找Node,找不到返回null </description>
     *
     * @param hash :
     * @param key :
     * @return : com.frozen.hash.MyEasyHashMap.Node<K,V>
     * @author : lw
     * @date : 2019-09-25 19:50
     */
    private Node<K, V> getNode(int hash,Object key) {
        int index = calculateLocation(hash);
        Node<K, V> currNode = this.table[index];
        while (!Objects.isNull(currNode)) {
            if (hash == currNode.hash && Objects.equals(currNode.getKey(), key)) {
                return currNode;
            }
            currNode = currNode.next;
        }
        return null;
    }

    /**
     * <description>  </description>
     *
     * @param hash  : key的hash值
     * @param key   :
     * @param value :
     * @return : V 返回旧值
     * @author : lw
     * @date : 2019-09-25 20:20
     */
    private V put(int hash, K key, V value) {
        int index = calculateLocation(hash);
        Node<K, V> currNode = table[index];
        while (!Objects.isNull(currNode)) {
            if (hash == currNode.hash && Objects.equals(key, currNode.getKey())) {
                return currNode.setValue(value);
            }
            currNode = currNode.next;
        }
        table[index] = new Node<>(key, value, hash, table[index]);
        this.size++;
        return null;
    }
    /**
     * <description>  </description>
     * @param hash :
     * @param key :
     * @return : V
     * @author : lw
     * @date : 2019-09-25 20:29
     */
    private V remove(int hash, Object key) {
        int index = calculateLocation(hash);
        Node<K, V> currNode = table[index];
        Node<K, V> preNode = null;
        while (!Objects.isNull(currNode)) {
            if (hash == currNode.hash && Objects.equals(key, currNode.getKey())) {
                if (Objects.isNull(preNode)) {
                    table[index] = currNode.next;
                } else {
                    preNode.next = currNode.next;
                }
                this.size--;
                return currNode.getValue();
            }
            preNode = currNode;
            currNode = currNode.next;
        }
        return null;
    }

    /**
     * 键值对实体类
     *
     * @param <K> key类型
     * @param <V> value类型
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        final K key;
        V value;
        /**
         * 该Node的hash值
         */
        final int hash;
        /**
         * 存储下一节点对象
         */
        Node<K, V> next;

        Node(K key, V value, int hash, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.key) ^ Objects.hashCode(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Map.Entry) {
                Map.Entry<?, ?> o = (Map.Entry<?, ?>) obj;
                return Objects.equals(this.key, o.getKey()) && Objects.equals(this.value, o.getValue());
            }
            return false;
        }

        @Override
        public String toString() {
            return "{" + key + "=" + value + "}";
        }
    }
}


