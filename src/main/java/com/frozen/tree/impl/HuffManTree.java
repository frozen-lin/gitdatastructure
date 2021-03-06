package com.frozen.tree.impl;


import com.frozen.entity.WeightValObj;

import java.util.*;

/**
 * <program> datastructure </program>
 * <description> 赫夫曼树 </description>
 *
 * @author : lw
 * @date : 2019-11-05 20:09
 **/
public class HuffManTree<T> extends BinaryTree<T> {
    public static <E> HuffManTree<E> buildHuffmanTree(List<WeightValObj<E>> objList) {
        if (objList == null || objList.isEmpty()) {
            return null;
        }
        List<TreeNode<E>> nodeList = new ArrayList<>();
        for (WeightValObj<E> obj:objList) {
            nodeList.add(new TreeNode<>(obj.getWeight(), obj.getObj()));
        }
        TreeNode<E> top;
        TreeNode<E> left;
        TreeNode<E> right;
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            left = nodeList.remove(0);
            right = nodeList.remove(0);
            int sum = left.getNo() + right.getNo();
            top = new TreeNode<>(sum, null);
            top.setLeft(left);
            top.setRight(right);
            nodeList.add(top);
        }
        HuffManTree<E> huffmanTree = new HuffManTree<>();
        huffmanTree.setRoot(nodeList.get(0));
        return huffmanTree;
    }

}
