package com.prger.tree;

import java.util.Comparator;

/**
 * @Author prgers
 * @Date 2021/7/15 10:15 上午
 */
public class RBTree<E> extends BBST<E>{

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }

    /**
     * 给节点染色
     */
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    /**
     * 染成红色
     */
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    /**
     * 染成黑色
     */
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    /**
     * 判断节点是什么颜色
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    /**
     * 是否是黑色
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 是否是红色
     */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        /**
         * 父节点为null,表示node节点是根节点
         * 根据红黑树的性质,将其染成黑色
         */
        if (parent == null) {
            black(node);
            return;
        }

        /**
         * 如果父节点的颜色是黑色,符合红黑树的性质,父节点为黑色
         * 也符合4阶B树的性质,因此不做任何处理
         */
        if (isBlack(parent)) return;

        //叔父节点
        Node<E> uncle = parent.sibling();
        //祖父节点
        Node<E> grand = red(parent.parent);

        /**
         * 叔父节点是红色, 【B树节点上溢】
         * 将祖父节点向上合并,染成红色,当做新节点添加处理
         * parent节点染成黑色
         * uncle节点染成黑色
         */
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            red(grand);
            //将祖父节点当做新节点来进行添加
            afterAdd(grand);
        }

        /**
         * 叔父节点不是红色,就要分情况进行旋转
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

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        /**
         * 如果删除的节点是红色
         * 直接删除,不做任何处理
         */
        if (isRed(node)) return;

        /**
         * 删除的节点是黑色,且用来取代它的节点是红色
         * 将替换的节点染成黑色
         */
        if (isRed(replacement)) {
            black(replacement);
            return;
        }

        Node<E> parent = node.parent;
        //删除的是根节点
        if (parent == null) return;

        /**
         * 删除的子节点是黑色,会导致B树节点【下溢】
         * 判断删除的node是左还是右
         */
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.left : parent.right;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateLeft(parent);
				// 更换兄弟
				sibling = parent.right;
			}

			// 兄弟节点必然是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent, null);
				}
			} else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
				// 兄弟节点的左边是黑色，兄弟要先旋转
				if (isBlack(sibling.right)) {
					rotateRight(sibling);
					sibling = parent.right;
				}

				color(sibling, colorOf(parent));
				black(sibling.right);
				black(parent);
				rotateLeft(parent);
			}
        }else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateRight(parent);
				// 更换兄弟
				sibling = parent.left;
			}

			// 兄弟节点必然是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent, null);
				}
			} else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
				// 兄弟节点的左边是黑色，兄弟要先旋转
				if (isBlack(sibling.left)) {
					rotateLeft(sibling);
					sibling = parent.left;
				}

				color(sibling, colorOf(parent));
				black(sibling.left);
				black(parent);
				rotateRight(parent);
			}
        }
    }
}
