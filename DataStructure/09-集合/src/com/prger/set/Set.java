package com.prger.set;

/**
 * @Author prgers
 * @Date 2021/7/20 4:02 下午
 */
public interface Set<E> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(E element);
    void add(E element);
    void remove(E element);
    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E> {
        boolean stop;
        public abstract boolean visit(E element);
    }
}
