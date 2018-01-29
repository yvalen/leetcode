package leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class BinaryTreeLevelOrder {
    /*
     * LEETCODE 102
     * Given a binary tree, return the level order traversal of its nodes' values. 
     * (ie, from left to right, level by level).
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *   15   7
     * return its level order traversal as:
     * [
     *  [3],
     *  [9,20],
     *  [15,7]
     * ]
     * 
     * Company: Facebook, Microsoft, Amazon, Bloomberg, LinkedIn, Apple
     * Difficulty: medium
     * Similar Questions: 103(Binary Tree Level Order Traversal II), 
     * 107(Binary Tree Zigzag Level Order Traversal), 111(Minimum Depth of Binary Tree)
     * 314(BinaryTreeVerticalOrderTraversal), 637(AverageOfLevels)
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode current = queue.poll();
                level.add(current.val);
                if (current.left != null)
                    queue.offer(current.left);
                if (current.right != null)
                    queue.offer(current.right);
            }
            result.add(level);
        }
        return result;
    }

    /*
     * LEETCODE 107 
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
     * (ie, from left to right, level by level from leaf to root). 
     * For example, given binary tree {3,9,20,#,#,15,7},
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its bottom-up level order traversal as:
     * [
     *  [15,7],
     *  [9,20],
     *  [3]
     * ]
     * 
     * Difficulty: easy 
     * Similar Questions: 102(Binary Tree Level Order Traversal), 637(AverageOfLevels)
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode current = queue.poll();
                level.add(current.val);
                if (current.left != null)
                    queue.offer(current.left);
                if (current.right != null)
                    queue.offer(current.right);
            }
            result.add(0, level);
        }
        return result;
    }

    /*
     * LEETCODE 103 
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. 
     * (ie, from left to right, then right to left for the next level and alternate between). 
     * For example: Given binary tree [3,9,20,null,null,15,7], 
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its bottom-up level order traversal as:
     * [
     *  [3],
     *  [20, 9],
     *  [15, 7]
     * ]
     * 
     * Company: Microsoft, Bloomberg, LinkedIn 
     * Difficulty: medium 
     * Similar Questions: 102(Binary Tree Level Order Traversal)
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            while (size-- > 0) {
                TreeNode current = queue.poll();
                if (leftToRight) {
                    level.addLast(current.val);
                }
                else {
                    level.addFirst(current.val);
                }
                if (current.left != null)
                    queue.offer(current.left);
                if (current.right != null)
                    queue.offer(current.right);
            }
            result.add(level);
            leftToRight = !leftToRight;
        }

        return result;
    }

}
