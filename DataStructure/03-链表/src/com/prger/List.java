package com.prger;

public interface List<E> {

    public static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 清楚所有元素
     */
    void clear();

    /**
     * 元素数量
     */
    int size();

    /**
     * 是否为空
     */
    boolean isEmpty();

    /**
     * 是否包含某个元素
     */
    boolean contains(E element);

    /**
     * 添加元素到最后面
     */
    void add(E element);

    /**
     * 返回index位置对应的元素
     */
    E get(int index);

    /**
     * 设置index位置的元素
     * @return 原来的元素
     */
    E set(int index, E element);

    /**
     * 往index位置添加元素
     */
    void add(int index, E element);

    /**
     * 删除index位置的元素
     * @return 删除的元素
     */
    E remove(int index);

    /**
     * 查看元素位置
     */
    int indexOf(E element);
}
