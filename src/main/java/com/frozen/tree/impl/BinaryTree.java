package com.frozen.tree.impl;

import com.frozen.tree.IBinaryTree;
import com.frozen.tree.ITreeNode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * <program> datastructure </program>
 * <description> 二叉树 </description>
 *
 * @author : lw
 * @date : 2019-09-28 18:24
 **/
@Getter
@Setter
public class BinaryTree<T> implements IBinaryTree<T> {
    public static final String ROOT_IS_NULL_MSG = "根节点为null无法遍历";
    /**
     * 二叉树根节点
     */
    private TreeNode<T> root;

    /**
     * <description> 前序遍历 </description>
     *
     * @return : void
     * @author : lw
     * @date : 2019-09-28 18:48
     */
    @Override
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println(ROOT_IS_NULL_MSG);
        }
    }

    /**
     * <description> 中序遍历 </description>
     *
     * @return : void
     * @author : lw
     * @date : 2019-09-28 18:49
     */
    @Override
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println(ROOT_IS_NULL_MSG);
        }
    }

    /**
     * <description> 后序遍历 </description>
     *
     * @return : void
     * @author : lw
     * @date : 2019-09-28 18:50
     */
    @Override
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println(ROOT_IS_NULL_MSG);
        }
    }

    /**
     * <description> 使用前序遍历进行删除给定序号的节点及子树 </description>
     *
     * @param delNo :  删除的节点no
     * @return : com.frozen.tree.impl.BinaryTree.TreeNode<T>
     * @author : lw
     * @date : 2019-10-07 19:07
     */
    @Override
    public TreeNode<T> delNodeTree(int delNo) {
        if (this.root == null) {
            return null;
        }
        if (this.root.getNo() == delNo) {
            TreeNode<T> delNodeTree = this.root;
            this.root = null;
            return delNodeTree;
        }
        return this.root.delNodeTree(delNo);
    }

    @Override
    public TreeNode<T> preSearch(int searchNo){
        if (Objects.isNull(this.root)){
            return null;
        }
        return this.root.preSearch(searchNo);
    }

    @Override
    public TreeNode<T> infixSearch(int searchNo){
        if (Objects.isNull(this.root)){
            return null;
        }
        return this.root.infixSearch(searchNo);
    }

    @Override
    public TreeNode<T> postSearch(int searchNo){
        if (Objects.isNull(this.root)){
            return null;
        }
        return this.root.postSearch(searchNo);
    }

    @Data
    public static class TreeNode<T> implements ITreeNode<T> {
        private int no;
        private T obj;
        private TreeNode<T> left;
        private TreeNode<T> right;

        public TreeNode() {

        }

        public TreeNode(int no, T obj) {
            this.no = no;
            this.obj = obj;
        }

        /**
         * <description> 前序遍历 </description>
         *
         * @return : void
         * @author : lw
         * @date : 2019-09-28 18:35
         */
        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        /**
         * <description> 中序遍历 </description>
         *
         * @return :  void
         * @author : lw
         * @date : 2019-09-28 18:41
         */
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        /**
         * <description> 后序遍历 </description>
         *
         * @return : void
         * @author : lw
         * @date : 2019-09-28 18:44
         */
        public void postOrder() {
            if (this.left != null) {
                this.left.postOrder();
            }
            if (this.right != null) {
                this.right.postOrder();
            }
            System.out.println(this);
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "obj=" + obj +
                    ", no=" + no +
                    '}';
        }

        /**
         * <description> 删除子树节点 </description>
         *
         * @param delNo :
         * @return : com.frozen.tree.impl.BinaryTree.TreeNode<T>
         * @author : lw
         * @date : 2019-10-07 19:41
         */
        public TreeNode<T> delNodeTree(int delNo) {
            TreeNode<T> delNodeTree = null;
            //判断左节点是否为要删除的节点
            if (!Objects.isNull(this.getLeft()) && this.getLeft().getNo() == delNo) {
                delNodeTree = this.getLeft();
                this.left = null;
                return delNodeTree;
            }
            //判断右节点是否为要删除的节点
            if (!Objects.isNull(this.getRight()) && this.getRight().getNo() == delNo) {
                delNodeTree = this.getRight();
                this.right = null;
                return delNodeTree;
            }
            //左节点递归检索删除节点
            if (!Objects.isNull(this.getLeft())) {
                delNodeTree = this.getLeft().delNodeTree(delNo);
                if (delNodeTree != null) {
                    return delNodeTree;
                }
            }
            //右节点递归检索删除节点
            if (!Objects.isNull(this.getRight())) {
                delNodeTree = this.getRight().delNodeTree(delNo);
            }
            return delNodeTree;
        }
        /**
         * <description> 前序查找节点 </description>
         * @param searchNo :
         * @return : com.frozen.tree.impl.BinaryTree.TreeNode<T>
         * @author : lw
         * @date : 2019-10-07 18:41
         */
        public TreeNode<T> preSearch(int searchNo) {
            System.out.println("~~~~查找~~~~");
            if (this.getNo() == searchNo) {
                return this;
            }
            TreeNode<T> searchTreeNode = null;
            if (!Objects.isNull(this.left)) {
                searchTreeNode = this.left.preSearch(searchNo);
                if (!Objects.isNull(searchTreeNode)) {
                    return searchTreeNode;
                }
            }

            if (!Objects.isNull(this.right)) {
                searchTreeNode = this.right.preSearch(searchNo);
            }
            return searchTreeNode;
        }

        /**
         * <description> 中序查找节点 </description>
         * @param searchNo :
         * @return : com.frozen.tree.impl.BinaryTree.TreeNode<T>
         * @author : lw
         * @date : 2019-10-27 18:42
         */
        public TreeNode<T> infixSearch(int searchNo) {
            TreeNode<T> searchTreeNode = null;
            //左树查找
            if (!Objects.isNull(this.left)) {
                searchTreeNode = this.left.infixSearch(searchNo);
                if (!Objects.isNull(searchTreeNode)) {
                    return searchTreeNode;
                }
            }
            System.out.println("~~~~查找~~~~");
            if (this.getNo() == searchNo) {
                return this;
            }

            //右树查找
            if (!Objects.isNull(this.right)) {
                searchTreeNode = this.right.infixSearch(searchNo);
            }
            return searchTreeNode;
        }

        /**
         * <description> 后续查找节点 </description>
         * @param searchNo :
         * @return : com.frozen.tree.impl.BinaryTree.TreeNode<T>
         * @author : lw
         * @date : 2019-10-07 18:42
         */
        public TreeNode<T> postSearch(int searchNo) {
            TreeNode<T> searchTreeNode;
            //左树查找
            if (!Objects.isNull(this.left)) {
                searchTreeNode = this.left.postSearch(searchNo);
                if (!Objects.isNull(searchTreeNode)) {
                    return searchTreeNode;
                }
            }

            //右树查找
            if (!Objects.isNull(this.right)) {
                searchTreeNode = this.right.postSearch(searchNo);
                if (!Objects.isNull(searchTreeNode)) {
                    return searchTreeNode;
                }
            }
            System.out.println("~~~~查找~~~~");
            if (this.getNo() == searchNo) {
                return this;
            }
            return null;
        }
    }


}

