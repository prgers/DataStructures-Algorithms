package com.prgers;

public class ArrayList {

    /**
     * 元素的数量
     */
    private int size;

    /**
     * 所有的元素
     */
    private int[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capacity) {
//        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        capacity = Math.max(DEFAULT_CAPACITY, capacity);
        elements = new int[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清楚所有元素
     */
    public void clear() {
        size = 0;
    }

    /**
     * 元素数量
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
     * 是否包含某个元素
     */
    public boolean contains(int element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到最后面
     */
    public void add(int element) {
        add(element, size);
    }

    /**
     * 返回index位置对应的元素
     */
    public int get(int index) {
        return 0;
    }

    /**
     * 设置index位置的元素
     * @return 原来的元素
     */
    public int set(int index, int element) {
        return 0;
    }

    /**
     * 往index位置添加元素
     */
    public void add(int index, int element) {
        rangeCheckAdd(index);
        for (int i = size; i > index; i++) {

        }
    }

    /**
     * 删除index位置的元素
     * @return 删除的元素
     */
    public int remove(int index) {
        return 0;
    }

    /**
     * 查看元素位置
     */
    public int indexOf(int element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i]) return i;
        }
        return ELEMENT_NOT_FOUND;
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(size);
        }
    }

    private void rangeCheckAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}
