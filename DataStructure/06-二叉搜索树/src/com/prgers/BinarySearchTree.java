package com.prgers;

import com.prgers.printer.BinaryTreeInfo;

import java.util.*;

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

    /**
     * 处理传入的值不能null
     */
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

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
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
        elementNotNullCheck(element);
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

    /**
     * 前序遍历
     * 访问顺序
     * 根节点, 前序遍历左子树,前序遍历右子树
     */
    public void preorderTraversal() {
        preorderTraversal2(root);
    }

    //递归遍历
    private void preorderTraversal(Node<E> node) {
        if (node == null) return;
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    //非递归遍历
    private void preorderTraversal2(Node<E> node) {
        if (node == null) return;
        Stack<Node<E>> stack = new Stack<>();
        while (node != null) {
            System.out.println(node.element);
            if (node.right != null) {
                stack.push(node.right);
            }
            node = node.left;
            if (node == null) {
                if (stack.isEmpty()) return;
                node = stack.pop();
            }
        }
    }

    /**
     * 中序遍历
     * 访问顺序
     * 中序遍历左子树,根节点,中序遍历右子树
     */

    public void inorderTraversal() {
        inorderTraversal2(root);
    }

    //递归遍历
    private void inorderTraversal(Node<E> node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    //非递归遍历
    private void inorderTraversal2(Node<E> node) {
        if (node == null) return;
        Stack<Node<E>> stack = new Stack<>();
        Deque<Integer> deque = new LinkedList<>();
        while (node != null) {
            stack.push(node);
            node = node.left;
            while (node == null) {
                if (stack.isEmpty()) return;
                node = stack.pop();
                System.out.println(node.element);
                node = node.right;
            }
        }
    }

    /**
     * 后序遍历
     * 后序遍历左子树,后序遍历右子树,根节点
     */
    public void postorderTraversal() {
        postorderTraversal2(root);
    }

    //递归遍历
    private void postorderTraversal(Node<E> node) {
        if (node == null) return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    //非递归遍历
    private void postorderTraversal2(Node<E> node){
        if (node == null) return;
        Stack<Node<E>> stack = new Stack<>();
        Node<E> prevNode = null;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (node.right == null || node.right == prevNode) {
                System.out.println(node.element);
                prevNode = node;
                node = null;
            }else {
                stack.push(node);
                node = node.right;
            }
        }
    }

    /**
     * 层序遍历
     */
    public void levelOrderTraversal() {
        if (root == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 获取树的高度
     */
    public int height() {
        return height2(root);
    }

    //递归方式
    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    //非递归方式
    private int height2(Node<E> node) {

        //树的高度
        int height = 0;

        //每层节点的数量
        int levelSize = 1;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {

            Node<E> poll = queue.poll();

            levelSize--;
            if (poll.left != null) {
                queue.offer(poll.left);
            }

            if (poll.right != null) {
                queue.offer(poll.right);
            }

            if (levelSize == 0) {
                height++;
                levelSize = queue.size();
            }
        }


        return height;
    }

    /**
     * 判断二叉树是否为完全二叉树
     */
    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {

            Node<E> node = queue.poll();

            if (leaf && !node.isLeaf()) return false;

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            }else {
                leaf = true;
            }

        }


        return true;
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
