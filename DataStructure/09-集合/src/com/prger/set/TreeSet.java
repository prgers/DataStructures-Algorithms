package com.prger.set;

import com.prger.tree.BinaryTree;
import com.prger.tree.RBTree;

/**
 * @Author prgers
 * @Date 2021/7/20 4:36 下午
 */
public class TreeSet<E> implements Set<E> {

    private RBTree<E> tree = new RBTree<>();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) return;
        tree.inorderTraversal(new BinaryTree.Visitor<E>() {
            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
