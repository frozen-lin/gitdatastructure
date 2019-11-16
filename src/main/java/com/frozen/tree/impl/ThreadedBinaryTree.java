package com.frozen.tree.impl;

import com.frozen.tree.IBinaryTree;
import com.frozen.tree.ITreeNode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.transaction.TransactionRequiredException;
import java.util.Objects;

/**
 * <program> datastructure </program>
 * <description> 线索化二叉树 </description>
 *
 * @author : lw
 * @date : 2019-10-08 19:56
 **/
@Getter
@Setter
public class ThreadedBinaryTree<T> implements IBinaryTree {
    public static final String ROOT_IS_NULL_MSG = "根节点为null无法遍历";

    /**
     * 树节点
     */
    private static final int TREE_NODE_TYPE = 0;
    /**
     * 线索化节点
     */
    private static final int THREADED_NODE_TYPE = 1;

    /**
     * 未线索化
     */
    private static final int NO_THREADED = 0;
    /**
     * 前序线索化
     */
    private static final int PRE_THREADED_TYPE = 1;
    /**
     * 中序线索化
     */
    private static final int INFIX_THREADED_TYPE = 2;
    /**
     * 后序线索化
     */
    private static final int POST_THREADED_TYPE = 3;
    private int threadedType = NO_THREADED;
    /**
     * 二叉树根节点
     */
    private ThreadedTreeNode<T> root;
    private ThreadedTreeNode<T> preNode;

    @Override
    public void preOrder() {
        this.preThreaded();
        if (Objects.isNull(this.root)) {
            System.out.println(ThreadedBinaryTree.ROOT_IS_NULL_MSG);
        } else {
            ThreadedTreeNode<T> currNode = this.root;
            while (!Objects.isNull(currNode.right)) {
                System.out.println(currNode);
                while (currNode.rightType == THREADED_NODE_TYPE) {
                    currNode = currNode.right;
                    System.out.println(currNode);
                }
                if (!Objects.isNull(currNode.left) && currNode.leftType == TREE_NODE_TYPE) {
                    currNode = currNode.left;
                }
            }
        }

    }

    /**
     * <description> 中序遍历 </description>
     *
     * @return : void
     * @author : lw
     * @date : 2019-10-28 19:48
     */
    @Override
    public void infixOrder() {
        this.infixThreaded();
        if (Objects.isNull(this.root)) {
            System.out.println(ThreadedBinaryTree.ROOT_IS_NULL_MSG);
        } else {
            ThreadedTreeNode<T> currNode = this.root;
            while (!Objects.isNull(currNode)) {
                while (currNode.leftType == TREE_NODE_TYPE && !Objects.isNull(currNode.left)) {
                    currNode = currNode.left;
                }
                System.out.println(currNode);
                while (currNode.rightType == THREADED_NODE_TYPE) {
                    currNode = currNode.right;
                    System.out.println(currNode);
                }
                currNode = currNode.right;
            }
        }
    }

    @Override
    public void postOrder() {
        this.postThreaded();
        if (Objects.isNull(this.root)) {
            System.out.println(ThreadedBinaryTree.ROOT_IS_NULL_MSG);
        } else {
            postOrder(this.root);
        }
    }

    public void postOrder(ThreadedTreeNode<T> treeNode){
        if (!Objects.isNull(treeNode)){
            if (treeNode.leftType== TREE_NODE_TYPE){
                postOrder(treeNode.left);
            }
            if (treeNode.rightType==TREE_NODE_TYPE){
                postOrder(treeNode.right);
            }
            System.out.println(treeNode);
        }
    }
    @Override
    public ThreadedTreeNode<T> delNodeTree(int delNo) {
        return null;
    }

    @Override
    public ThreadedTreeNode<T> preSearch(int searchNo) {
        return null;
    }

    @Override
    public ThreadedTreeNode<T> infixSearch(int searchNo) {
        return null;
    }

    @Override
    public ThreadedTreeNode<T> postSearch(int searchNo) {
        return null;
    }

    public void preThreaded() {
        if (this.threadedType != PRE_THREADED_TYPE) {
            this.clearThreaded();
            this.threadedType = PRE_THREADED_TYPE;
            //清空前序节点
            this.preNode = null;
            preThreaded(this.root);
        }

    }

    /**
     * <description> 前序线索化 </description>
     *
     * @param node :
     * @return : void
     * @author : lw
     * @date : 2019-10-28 19:48
     */
    private void preThreaded(ThreadedTreeNode<T> node) {
        if (!Objects.isNull(node)) {
            ThreadedTreeNode<T> left = node.left;
            ThreadedTreeNode<T> right = node.right;
            if (Objects.isNull(left)) {
                node.left = this.preNode;
                node.leftType = THREADED_NODE_TYPE;
            }
            if (!Objects.isNull(this.preNode)) {
                if (Objects.isNull(this.preNode.right)) {
                    this.preNode.right = node;
                    this.preNode.rightType = THREADED_NODE_TYPE;
                }
            }
            this.preNode = node;
            this.preThreaded(left);
            this.preThreaded(right);
        }
    }

    public void infixThreaded() {
        if (this.threadedType != INFIX_THREADED_TYPE) {
            this.clearThreaded();
            this.threadedType = INFIX_THREADED_TYPE;
            //清空前序节点
            this.preNode = null;
            infixThreaded(this.root);
        }
    }

    /**
     * <description> 中序线索化 </description>
     *
     * @param node :
     * @return : void
     * @author : lw
     * @date : 2019-10-28 19:49
     */
    private void infixThreaded(ThreadedTreeNode<T> node) {
        if (!Objects.isNull(node)) {
            infixThreaded(node.left);
            if (Objects.isNull(node.left)) {
                //将左节点为空或左节点为线索化节点 设置前序节点
                node.leftType = THREADED_NODE_TYPE;
                node.left = this.preNode;
            } else {
                if (Objects.isNull(node.left.right)) {
                    //将普通左树节点的空右子节点或线索化右子节点设置为当前节点
                    node.left.right = node;
                    node.left.rightType = THREADED_NODE_TYPE;
                }
            }
            //将当前节点设置为前序节点
            this.preNode = node;
            //右节点线索化
            infixThreaded(node.right);
        }
    }

    public void postThreaded(){
        if (this.threadedType != POST_THREADED_TYPE) {
            this.clearThreaded();
            this.threadedType = POST_THREADED_TYPE;
            //清空前序节点
            this.preNode = null;
            postThreaded(this.root);
        }
    }

    /**
     * <description> 后序线索化 </description>
     * @param treeNode :
     * @return : void
     * @author : lw
     * @date : 2019-10-29 21:43
     */
    public void postThreaded(ThreadedTreeNode<T> treeNode){
        if (!Objects.isNull(treeNode)){
            ThreadedTreeNode<T> left = treeNode.left;
            ThreadedTreeNode<T> right = treeNode.right;
            postThreaded(left);
            postThreaded(right);
            if (Objects.isNull(left)&&!Objects.isNull(this.preNode)){
                treeNode.left = this.preNode;
                treeNode.leftType = THREADED_NODE_TYPE;
            }
            if (!Objects.isNull(this.preNode)&&Objects.isNull(this.preNode.right)){
                this.preNode.right = treeNode;
                this.preNode.rightType = THREADED_NODE_TYPE;
            }
            this.preNode = treeNode;
        }
    }
    /**
     * <description> 去除线索化 </description>
     *
     * @return : void
     * @author : lw
     * @date : 2019-10-28 21:36
     */
    public void clearThreaded() {
        clearThreaded(this.root);
    }

    private void clearThreaded(ThreadedTreeNode<T> treeNode) {
        if (Objects.isNull(treeNode)) {
            return;
        }
        if (treeNode.leftType == THREADED_NODE_TYPE) {
            treeNode.leftType = TREE_NODE_TYPE;
            treeNode.left = null;
        }
        if (treeNode.rightType == THREADED_NODE_TYPE) {
            treeNode.rightType = TREE_NODE_TYPE;
            treeNode.right = null;
        }
        clearThreaded(treeNode.left);
        clearThreaded(treeNode.right);
    }




    @Data
    public static class ThreadedTreeNode<T> implements ITreeNode<T> {
        private int no;
        private T obj;
        private ThreadedTreeNode<T> left;
        private int leftType = TREE_NODE_TYPE;
        private ThreadedTreeNode<T> right;
        private int rightType = TREE_NODE_TYPE;

        public ThreadedTreeNode() {

        }

        public ThreadedTreeNode(int no, T obj) {
            this.no = no;
            this.obj = obj;
        }

        @Override
        public String toString() {
            return "ThreadedTreeNode{" +
                    "no=" + no +
                    ", obj=" + obj +
                    '}';
        }


    }
}
