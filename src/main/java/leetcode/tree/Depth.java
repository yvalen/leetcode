package leetcode.tree;

public class Depth {
	/*
	 * LEETCODE 111
	 * Given a binary tree, find its minimum depth. The minimum depth is the number of nodes 
	 * along the shortest path from the root node down to the nearest leaf node.
	 * 
	 * Difficulty: easy
	 * Similar Questions: 104(Maximum Depth of Binary Tree)
	 */
	public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right +1 : Math.min(left,  right) + 1;
    }
	
	/*
	 * LEETCODE 104
	 * Given a binary tree, find its maximum depth. The maximum depth is the number of nodes 
	 * along the longest path from the root node down to the farthest leaf node.
	 * 
	 * Company: Uber, LinkedIn, Apple, Yahoo
	 * Difficulty: easy
	 * Similar Questions: 111(Minimum Depth of Binary Tree), 110(BalancedBinaryTree)
	 * 
	 */
	public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
