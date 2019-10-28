package com.frozen.tree;

public interface IBinaryTree<T> {
    void preOrder();

    void infixOrder();

    void postOrder();

    ITreeNode<T> delNodeTree(int delNo);

    ITreeNode<T> preSearch(int searchNo);

    ITreeNode<T> infixSearch(int searchNo);

    ITreeNode<T> postSearch(int searchNo);
}


