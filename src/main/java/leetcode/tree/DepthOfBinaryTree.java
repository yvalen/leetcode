package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class DepthOfBinaryTree {
    /*
     * LEETCODE 104
     * Given a binary tree, find its maximum depth. The maximum depth is the number of 
     * nodes along the longest path from the root node down to the farthest leaf node.
     * 
     * Company: Uber, LinkedIn, Apple, Yahoo
     * Difficulty: easy
     * Similar Questions: 111(Minimum Depth of Binary Tree), 110(BalancedBinaryTree)
     * 
     * Time complexity: O(n), Space complexity: O(n)
     */
    public int maxDepth_dfs(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepth_dfs(root.left);
        int rightDepth = maxDepth_dfs(root.right);
        return Math.max(leftDepth, rightDepth)+1;
    }
    
    public int maxDepth_bfs(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode current = queue.poll();
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
        }
        return depth;
    }
    
    /*
     * LEETCODE 111
     * Given a binary tree, find its minimum depth. The minimum depth is the number of nodes 
     * along the shortest path from the root node down to the nearest leaf node.
     * 
     * Difficulty: easy
     * Similar Questions: 102(BinaryTreeLevelOrder), 104(Maximum Depth of Binary Tree)
     */
    public int minDepth_dfs(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = minDepth_dfs(root.left);
        int rightDepth = minDepth_dfs(root.right);
        return (leftDepth == 0 || rightDepth == 0) ? leftDepth+rightDepth+1 : Math.min(leftDepth, rightDepth)+1;
    }

    public int minDepth_bfs(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode current = queue.poll();
                if (current.left == null && current.right == null) {
                    return depth;
                }
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
        }
        return depth;
    }

}
