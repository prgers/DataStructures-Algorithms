package com.prgers.tree;
import com.prgers.printer.BinaryTreeInfo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author prgers
 * @Date 2021/7/12 11:57 上午
 */
@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {

    /**
     * 节点个数
     */
    protected int size;

    /**
     * 根节点
     */
    protected Node<E> root;

    /**
     * 创建一个节点
     */
    protected static class Node<E> {
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
     * 清空节点
     */
    public void clear() {
        root = null;
        size = 0;
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

    /**
     * 获取某个节点的前驱节点
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        //如果前驱节点在节点的左子树种
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        //从父节点中找
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        //node.parent = null
        //node = node.parent.right
        return node.parent;
    }

    /**
     * 获取某个节点的后继节点
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;

        //如果前驱节点在节点的左子树种
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        //从父节点中找
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        //node.parent = null
        //node = node.parent.left
        return node.parent;
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
