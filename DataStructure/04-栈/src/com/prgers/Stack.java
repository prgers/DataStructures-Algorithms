package com.prgers;

import com.prgers.list.ArrayList;
import com.prgers.list.List;

/**
 * @Author prgers
 * @Date 2021/7/3 10:38 下午
 */
public class Stack<E> {

    private List<E> list = new ArrayList<>();

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(element);
    }

    public E pop() {
        return list.remove(size() - 1);
    }

    public E top() {
        return list.get(size() - 1);
    }
}
