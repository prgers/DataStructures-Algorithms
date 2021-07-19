package com.prgers;

import com.prgers.printer.BinaryTrees;
import com.prgers.tree.AVLTree;

/**
 * @Author prgers
 * @Date 2021/7/13 9:15 上午
 */
public class Main {

    public static void main(String[] args) {
        Integer[] data = new Integer[] {
                67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
        };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }

        BinaryTrees.println(avl);

        avl.remove(39);
        avl.remove(34);
        avl.remove(9);
        avl.remove(53);
        avl.remove(63);
        avl.remove(13);
        avl.remove(54);
        System.out.println("----------------------");
        BinaryTrees.println(avl);
    }
}
