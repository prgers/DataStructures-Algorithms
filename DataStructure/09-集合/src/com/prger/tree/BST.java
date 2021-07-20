package com.prger.tree;

import java.util.Comparator;

/**
 * @Author prgers
 * @Date 2021/7/8 10:46 上午
 * 二叉搜索树
 */
public class BST<E> extends BinaryTree<E> {

    /**
     * 比较器
     * 元素的比较方案设计
     * 1. 允许外接传入一个Comparator 自定义比较方案
     * 2. 如果没有传入Comparator, 则认定元素实现了Comparator接口
     */
    private final Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 返回值等于0, 表示e1等于e2
     * 返回值大于0, 表示e1大于e2
     * 返回值小于0, 表示e1小于e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    /**
     * 处理传入的值不能null
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 添加元素
     */
    public void add(E element) {
        elementNotNullCheck(element);

        //添加第一个节点
        if (root == null) {
            root = createNode(element, null);
            size++;

            //新添加节点之后的处理
            afterAdd(root);
            return;
        }

        //添加的不是第一个节点
        //找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;

        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) { //表示element在node节点的右边
                node = node.right;
            } else if (cmp < 0) { //表示element在node节点的左边
                node = node.left;
            } else { //表示element 等于 node.element, 这里直接处理为替换值
                node.element = element;
                return;
            }
        }

        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else{
            parent.left = newNode;
        }
        size++;

        //新添加节点之后的处理
        afterAdd(newNode);
    }

    /**
     * 添加节点之后的调整
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) { }

    /**
     * 删除节点之后的调整
     * @param node 被删除的节点
     * @param replacement 替换节点
     */
    protected void afterRemove(Node<E> node, Node<E> replacement) {}

    /**
     * 删除元素
     */
    public void remove(E element) {
        elementNotNullCheck(element);

        remove(node(element));
    }

    private void remove(Node<E> node) {

        if (node == null) return;

        size--;
        //先删除度为2的节点
        if (node.hasTwoChildren()) {

            //找到前驱节点/后继节点
            Node<E> p= predecessor(node);

            //用前驱节点的值覆盖要删除节点的值
            node.element = p.element;

            //删除节点
            node = p;
        }

        //删除node节点(node的度必然是1或者0)
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { //度为1

            //更改parent
            replacement.parent = node.parent;
            if (node.parent == null) { //度为1且是根节点
                root = replacement;
            }else if (node == node.parent.left) {
                node.parent.left = replacement;
            }else {
                node.parent.right = replacement;
            }

            //删除节点之后的调整
            afterRemove(node, replacement);
        } else if (node.parent == null) { //node是叶子节点且是根节点
            root = null;
            //删除节点之后的调整
            afterRemove(node, null);
        } else { //node是叶子节点,但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            }else {
                node.parent.right = null;
            }
            //删除节点之后的调整
            afterRemove(node, null);
        }
    }

    /**
     * 是否包含某元素
     */
    public boolean contains(E element) {
        return node(element) != null;
    }

    /**
     * 根据节点的值获取节点
     */
    private Node<E> node(E element) {
        elementNotNullCheck(element);
        Node<E> node = root;

        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return node;
    }
}
