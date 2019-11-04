package com.frozen.tree.applicaiton;

import com.frozen.entity.Hero;
import com.frozen.tree.impl.BinaryTree;
import com.frozen.tree.impl.ThreadedBinaryTree;
import org.junit.Test;

/**
 * <program> datastructure </program>
 * <description> 树测试应用 </description>
 *
 * @author : lw
 * @date : 2019-09-28 18:54
 **/
public class TreeApplication {

    /**
     *          0
     *      1       2
     *            3   4
     *          5
     */
    public BinaryTree<Hero> buildBinaryTree() {
        Hero heroRoot = new Hero(0, "宋江");
        Hero hero1 = new Hero(1, "林冲");
        Hero hero2 = new Hero(2, "鲁智深");
        Hero hero3 = new Hero(3, "关胜");
        Hero hero4 = new Hero(4, "卢俊义");
        Hero hero5 = new Hero(5, "吴用");
        BinaryTree.TreeNode<Hero> root = new BinaryTree.TreeNode<>(heroRoot.getNo(), heroRoot);

        BinaryTree.TreeNode<Hero> treeNode1 = new BinaryTree.TreeNode<>(hero1.getNo(), hero1);
        BinaryTree.TreeNode<Hero> treeNode2 = new BinaryTree.TreeNode<>(hero2.getNo(), hero2);
        BinaryTree.TreeNode<Hero> treeNode3 = new BinaryTree.TreeNode<>(hero3.getNo(), hero3);
        BinaryTree.TreeNode<Hero> treeNode4 = new BinaryTree.TreeNode<>(hero4.getNo(), hero4);
        BinaryTree.TreeNode<Hero> treeNode5 = new BinaryTree.TreeNode<>(hero5.getNo(), hero5);
        root.setLeft(treeNode1);
        root.setRight(treeNode2);
        treeNode2.setLeft(treeNode3);
        treeNode2.setRight(treeNode4);
        treeNode3.setLeft(treeNode5);
        BinaryTree<Hero> binaryTree = new BinaryTree<>();
        binaryTree.setRoot(root);
        return binaryTree;
    }

    /**
     * <description> 树测试遍历 </description>
     *
     * @return : void
     * @author : lw
     * @date : 2019-09-28 18:57
     */
    @Test
    public void testOrder() {
        BinaryTree<Hero> binaryTree = buildBinaryTree();
        System.out.println("前序遍历");
        binaryTree.preOrder();  //0,1,2,3,5,4
        System.out.println("中序遍历");
        binaryTree.infixOrder();  //1,0,5,3,2,4
        System.out.println("后序遍历");
        binaryTree.postOrder();  //1,5,3,4,2,0
    }

    /**
     * <description> 树测试删除节点及其子树 </description>
     *
     * @return : void
     * @author : lw
     * @date : 2019-10-07 19:35
     */
    @Test
    public void testDel() {
        BinaryTree<Hero> binaryTree = buildBinaryTree();
        binaryTree.delNodeTree(3);
        binaryTree.preOrder();//0,1,2,4
    }

    @Test
    public void testSearch(){
        BinaryTree<Hero> binaryTree = buildBinaryTree();
        System.out.println("前序查找");
        System.out.println(binaryTree.preSearch(3));
        System.out.println("中序查找");
        System.out.println(binaryTree.infixSearch(3));
        System.out.println("后序查找");
        System.out.println(binaryTree.postSearch(3));
    }

    public ThreadedBinaryTree<Hero> buildThreadedBinaryTree(){
        Hero heroRoot = new Hero(0, "宋江");
        Hero hero1 = new Hero(1, "林冲");
        Hero hero2 = new Hero(2, "鲁智深");
        Hero hero3 = new Hero(3, "关胜");
        Hero hero4 = new Hero(4, "卢俊义");
        Hero hero5 = new Hero(5, "吴用");
        ThreadedBinaryTree.ThreadedTreeNode<Hero> root = new ThreadedBinaryTree.ThreadedTreeNode<>(heroRoot.getNo(), heroRoot);

        ThreadedBinaryTree.ThreadedTreeNode<Hero> treeNode1 = new ThreadedBinaryTree.ThreadedTreeNode<>(hero1.getNo(), hero1);
        ThreadedBinaryTree.ThreadedTreeNode<Hero> treeNode2 = new ThreadedBinaryTree.ThreadedTreeNode<>(hero2.getNo(), hero2);
        ThreadedBinaryTree.ThreadedTreeNode<Hero> treeNode3 = new ThreadedBinaryTree.ThreadedTreeNode<>(hero3.getNo(), hero3);
        ThreadedBinaryTree.ThreadedTreeNode<Hero> treeNode4 = new ThreadedBinaryTree.ThreadedTreeNode<>(hero4.getNo(), hero4);
        ThreadedBinaryTree.ThreadedTreeNode<Hero> treeNode5 = new ThreadedBinaryTree.ThreadedTreeNode<>(hero5.getNo(), hero5);
        root.setLeft(treeNode1);
        root.setRight(treeNode2);
        treeNode2.setLeft(treeNode3);
        treeNode2.setRight(treeNode4);
        treeNode3.setLeft(treeNode5);
        ThreadedBinaryTree<Hero> binaryTree = new ThreadedBinaryTree<>();
        binaryTree.setRoot(root);
        return binaryTree;
    }

    @Test
    public void testThreadedOrder(){
        ThreadedBinaryTree binaryTree = buildThreadedBinaryTree();
        System.out.println("--- 中序线索化遍历 ---");
        binaryTree.infixOrder();
        System.out.println("--- 前序线索化遍历 ---");
        binaryTree.preOrder();
        System.out.println("--- 后序线索化遍历 ---");
        binaryTree.postOrder();
    }
}
