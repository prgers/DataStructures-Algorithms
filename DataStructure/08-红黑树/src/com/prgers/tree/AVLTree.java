package com.prgers.tree;

import java.util.Comparator;

/**
 * @Author prgers
 * @Date 2021/7/13 9:15 上午
 * AVL树
 */
public class AVLTree<E> extends BBST<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        //计算平衡因子, 左子树的高度减去右子树的高度
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        //更新高度
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        //获取子节点中高度最高的节点
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            //自定义逻辑,如果相等,返回跟父节点相同方向的节点
            return isLeftChild() ? left : right;
        }
    }

    //判断是否平衡 每个节点的平衡因子必须是1, 0 , -1 绝对值要小于等于1
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    //更新高度
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>)node).updateHeight();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * 添加节点,可能会使其所有祖父节点失衡
     * 父节点,非祖父节点的平衡不变
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            //添加元素后,只要节点平衡,高度加1
            if (isBalanced(node)) {
                //更新高度
                updateHeight(node);
            }else { //失去平衡

                //恢复平衡
                rebalance(node);
                break;
            }
        }
    }

    /**
     * 删除节点可能会导致父节点或者祖父节点失衡,其他节点不失衡
     */
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                //更新高度
                updateHeight(node);
            }else { //失去平衡

                //恢复平衡
                rebalance(node);
            }
        }
    }

    /**
     * 恢复平衡
     * 找到高度最低的那个不平衡节点,将其恢复平衡
     * 这样的话整棵树都平衡
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        if (parent.isLeftChild()) { //L
            if (node.isLeftChild()) { //LL
                rotateRight(grand);
            }else { //LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else { //R
            if (node.isRightChild()) { //RR
                rotateLeft(grand);
            }else { //RL
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);

        //更新高度
        updateHeight(grand);
        updateHeight(parent);
    }
}
