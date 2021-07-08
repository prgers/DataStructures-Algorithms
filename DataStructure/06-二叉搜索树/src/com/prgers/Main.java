package com.prgers;

import com.prgers.printer.BinaryTrees;

/**
 * @Author prgers
 * @Date 2021/7/8 10:44 上午
 */
public class Main {

    public static void main(String[] args) {

        Integer data[] = new Integer[] {
            7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }

        BinaryTrees.println(tree);

        System.out.println(tree.contains(11));
    }
}
