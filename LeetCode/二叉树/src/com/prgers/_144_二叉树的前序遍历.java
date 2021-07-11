package com.prgers;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author prgers
 * @Date 2021/7/11 9:49 下午
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class _144_二叉树的前序遍历 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    public void preorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }
}
