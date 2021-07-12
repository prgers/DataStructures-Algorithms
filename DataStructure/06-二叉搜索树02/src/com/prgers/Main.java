package com.prgers;

import com.prgers.printer.BinaryTrees;
import com.prgers.tree.BST;

/**
 * @Author prgers
 * @Date 2021/7/8 10:44 上午
 */
public class Main {

    public static void main(String[] args) {

        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 10, 1
        };

        BST<Integer> tree = new BST<>();
        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }

        BinaryTrees.println(tree);

//        tree.preorderTraversal();
//        tree.inorderTraversal();
//        tree.postorderTraversal();
//        tree.levelOrderTraversal();
//        System.out.println(tree.height());
//        System.out.println(tree.isComplete());
        tree.remove(4);
        BinaryTrees.println(tree);
    }


}
