package com.prgers;

import com.prgers.list.LinkedList;
import com.prgers.list.List;

/**
 * @Author prgers
 * @Date 2021/7/5 9:48 下午
 */
public class Queue<E> {

    private List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public void clear() {
        list.clear();
    }
    public void enQueue(E element) {
        list.add(element);
    }

    public E deQueue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }

}
