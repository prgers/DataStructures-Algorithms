package com.prgers;

import com.prgers.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * @Author prgers
 * @Date 2021/7/8 10:46 上午
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {

    /**
     * 节点个数
     */
    private int size;

    /**
     * 根节点
     */
    private Node<E> root;

    /**
     * 比较器
     * 元素的比较方案设计
     * 1. 允许外接传入一个Comparator 自定义比较方案
     * 2. 如果没有传入Comparator, 则认定元素实现了Comparator接口
     */
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
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

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 创建一个节点
     */
    private static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    /**
     * 元素的数量
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {

        return size == 0;
    }

    /**
     * 添加元素
     */
    public void add(E element) {
        elementNotNullCheck(element);

        //添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
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

        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else{
            parent.left = newNode;
        }
        size++;
    }

    /**
     * 删除元素
     */
    public void remove(E element) {

    }

    /**
     * 是否包含某元素
     */
    public boolean contains(E element) {
        return false;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).element;
    }

}
