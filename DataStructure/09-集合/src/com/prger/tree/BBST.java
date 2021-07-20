package com.prger.tree;

import java.util.Comparator;

/**
 * @Author prgers
 * @Date 2021/7/15 10:01 上午
 * 平衡二叉搜索树
 */
public class BBST<E> extends BST<E> {

    public BBST() {
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    //左旋转
    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    //右旋转
    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    //旋转之后的处理
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        //更新parent的parent
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        }else if (grand.isRightChild()) {
            grand.parent.right = parent;
        }else {
            root = parent;
        }

        //更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        //更新grand的parent
        grand.parent = parent;

    }
}
