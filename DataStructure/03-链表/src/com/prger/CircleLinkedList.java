package com.prger;

public class CircleLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;
        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (prev == null) {
                sb.append("null");
            }else {
                sb.append(prev.element);
            }

            sb.append("_").append(element).append("_");
            if (next == null) {
                sb.append("null");
            }else {
                sb.append(next.element);
            }

            return sb.toString();
        }
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == size) {
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, first);
            if (oldLast == null) { //这是链表添加的第一个元素
                first = last;
                first.next = first;
                first.prev = first;
            }else {
                oldLast.next = last;
                first.prev = last;
            }
        }else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> current = new Node<>(prev, element, next);
            next.prev = current;
            prev.next = current;

            if (next == first) {
                first = current;
            }
        }
        size++;
        
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> node = node(index);

        if (size == 1) {
            first = null;
            last = null;
        }else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            next.prev = prev;
            prev.next = next;


            if (node == first) {
                first = next;
            }

            if (node == last) {
                last = prev;
            }
        }

        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {

        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element == node.element) return i;
                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    //获取index位置的节点
    private Node<E> node(int index) {
        rangeCheck(index);

        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {

            if (i != 0) {
                sb.append(",");
            }
            sb.append(node);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
