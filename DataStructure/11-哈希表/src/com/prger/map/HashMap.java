package com.prger.map;

import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    private Node<K, V>[] table;
    private static final int DEFAULT_CAPACITY = 1 << 4;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) return;
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        //算出key的在数组中的位置
        int index = index(key);
        //取出index位置的红黑树根节点，因为在桶数组中存放的就是红黑树的根节点
        Node<K, V> root = table[index];
        if (root == null) {
            root = new Node<>(key, value, null);
            table[index] = root;
            size ++;
            afterPut(root);
            return null;
        }
        
        //添加的不是根节点
        //添加的不是第一个节点
        //找到父节点
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;

        do {
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
        }while (node != null);

        Node<K, V> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else{
            parent.left = newNode;
        }
        size++;

        //新添加节点之后的处理
        afterPut(newNode);
        
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }


    private int compare(K k1, K k2, int h1, int h2) {

        //比较哈希值
        int result = h1 - h2;
        if (result != 0) return result;

        //比较equals
        if (Objects.equals(k1, k2)) return 0;

        //哈希值相等，但是不equals
        if (k1 != null && k2 != null
                && k1.getClass() == k2.getClass()
                && k1 instanceof Comparable) {
            //同一种类型并且具备可比较性
            if (k2 instanceof Comparable) {
                return ((Comparable)  k1).compareTo(k2);
            }
        }

    }

    protected void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;

        /*
         父节点为null,表示node节点是根节点
         根据红黑树的性质,将其染成黑色
         */
        if (parent == null) {
            black(node);
            return;
        }

        /*
         如果父节点的颜色是黑色,符合红黑树的性质,父节点为黑色
         也符合4阶B树的性质,因此不做任何处理
         */
        if (isBlack(parent)) return;

        //叔父节点
        Node<K, V> uncle = parent.sibling();
        //祖父节点
        Node<K, V> grand = red(parent.parent);

        /*
         叔父节点是红色, 【B树节点上溢】
         将祖父节点向上合并,染成红色,当做新节点添加处理
         parent节点染成黑色
         uncle节点染成黑色
         */
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            red(grand);
            //将祖父节点当做新节点来进行添加
            afterPut(grand);
        }

        /*
         叔父节点不是红色,就要分情况进行旋转
         */
        if (parent.isLeftChild()) { //L
            if (node.isLeftChild()) { //LL
                black(parent);
            }else { //LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else { //R
            if (node.isLeftChild()) { //RL
                black(node);
                rotateRight(parent);
            }else { //RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    //左旋转
    protected void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    //右旋转
    protected void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    //旋转之后的处理
    protected void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        //更新parent的parent
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        }else if (grand.isRightChild()) {
            grand.parent.right = parent;
        }else {
            table[index(grand)] = parent;
        }

        //更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        //更新grand的parent
        grand.parent = parent;

    }


    /**
     * 根据key生成对应的索引(在桶数组中的位置)
     */
    private int index(K key) {
        if (key == null) return 0;
        /*
         这个hashCode()方法不清楚生成的hash值均不均匀，因此有了以下操作
         将hash值无符号右移，让hash值的高16位与低16位进行异或，这样生成的hash值会相对均匀一些
         */
        int hash = key.hashCode();
        return (hash ^(hash >>> 16)) & (table.length - 1);
    }

    private int index(Node<K, V> node) {
        return (node.hash ^(node.hash >>> 16)) & (table.length - 1);
    }

    private static class Node<K, V> {
        int hash;
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.hash = key == null ? 0 : key.hashCode();
            this.value = value;
            this.parent = parent;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }

        @Override
        public String toString() {
            return "Node_" + key + "_" + value;
        }
    }

    /**
     * 给节点染色
     */
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    /**
     * 染成红色
     */
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    /**
     * 染成黑色
     */
    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    /**
     * 判断节点是什么颜色
     */
    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    /**
     * 是否是黑色
     */
    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 是否是红色
     */
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }
}
